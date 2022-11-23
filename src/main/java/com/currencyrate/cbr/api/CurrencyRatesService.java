package com.currencyrate.cbr.api;

import com.currencyrate.cbr.model.CurrencyRate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface CurrencyRatesService {

    Mono<CurrencyRate> getCurrencyRate(String currency, LocalDate date);

    Flux<CurrencyRate> getCurrencyRates(LocalDate date);

}
