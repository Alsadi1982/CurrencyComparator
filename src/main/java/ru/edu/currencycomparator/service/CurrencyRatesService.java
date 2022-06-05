package ru.edu.currencycomparator.service;


public interface CurrencyRatesService {

    Double getPreviousRate(String baseCurrency);
    Double getPresentRate(String baseCurrency);

}
