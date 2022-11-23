package com.currencyrate.cbr.controller;

import com.currencyrate.cbr.api.CurrencyRatesService;
import com.currencyrate.cbr.model.CurrencyRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/rates")
@RequiredArgsConstructor
@Slf4j
public class CurrencyRatesController {

    private final CurrencyRatesService ratesService;

    @GetMapping("/currency/{currency}")
    public Mono<CurrencyRate> getCurrencyRate(
            @PathVariable(value = "currency", required = false) String currency,
            @RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date
    ) {
        log.info("Received request with currency: {} and date: {}", currency, date);
        return ratesService.getCurrencyRate(currency, date);
    }

    @GetMapping("/currencies")
    public Flux<CurrencyRate> getCurrencies(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        log.info("Received request with date: {}", date);
        return ratesService.getCurrencyRates(date);
    }

    @GetMapping
    public String hello() {
        return "hello world";
    }

}
