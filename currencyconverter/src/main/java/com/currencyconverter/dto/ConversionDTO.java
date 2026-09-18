package com.currencyconverter.dto;



public class ConversionDTO {
    public static record convertCurrency(
        String baseCurrency,
        String targetCurrency,
        Double amount
    ){}


    public static record conversionResponse(
        String baseCurrency,
        String quoteCurrency,
        Integer convertedAmount,
        Double baseAmount,
        Double conversionRate
    ){}
}