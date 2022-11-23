package com.currencyrate.cbr.service;

import com.currencyrate.cbr.api.CurrencyRatesService;
import com.currencyrate.cbr.exceptions.CurrencyRateNotFoundException;
import com.currencyrate.cbr.model.CurrencyRate;
import com.currencyrate.cbr.model.ValCurs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyRatesServiceImpl implements CurrencyRatesService {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private final CbrService cbrService;

    @Override
    public Mono<CurrencyRate> getCurrencyRate(String currency, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        ValCurs valCurs = cbrService.getRates(date.format(formatter));
        CurrencyRate rate = valCurs.getValute().stream().filter(valute -> valute.getCharCode().equals(currency)).findAny()
                .orElseThrow(() -> new CurrencyRateNotFoundException(String.format("Currency %s with data %s not found", currency, date)));
        return Mono.just(rate).log();
    }

    @Override
    public Flux<CurrencyRate> getCurrencyRates(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return Mono.just(cbrService.getRates(date.format(formatter)).getValute())
                .flatMapMany(Flux::fromIterable).log();
    }
}
