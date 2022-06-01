package ru.edu.currencycomparator.model;

import lombok.Data;
import java.util.Map;

/**
 * Model for working with exchange rates
 */
@Data
public class CurrencyRates {

    private String disclaimer;
    private String license;
    private String timestamp;
    private String base;
    private Map<String, Double> rates;

}
