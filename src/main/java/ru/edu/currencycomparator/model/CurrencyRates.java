package ru.edu.currencycomparator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Model for working with exchange rates
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRates {

    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, Double> rates;

}
