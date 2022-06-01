package ru.edu.currencycomparator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.currencycomparator.model.CurrencyRates;
import ru.edu.currencycomparator.service.CurrencyRatesServiceImp;

@RestController
@RequestMapping("/")
public class CurrencyRatesController {

    @Value("${openexchangerates.org.app_id}")
    private String appID;

    @Autowired
    private CurrencyRatesServiceImp currencyRatesServiceImp;


    @GetMapping
    public CurrencyRates index (){
        return currencyRatesServiceImp.getTodayRate(appID);
    }


}
