package com.currencyrate.cbr.exceptions;

public class CurrencyRateNotFoundException extends RuntimeException{

    public CurrencyRateNotFoundException(String message) {
        super(message);
    }
}
