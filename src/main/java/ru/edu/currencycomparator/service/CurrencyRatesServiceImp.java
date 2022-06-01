package ru.edu.currencycomparator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.currencycomparator.client.CurrencyFeignClient;
import ru.edu.currencycomparator.model.CurrencyRates;

@Service
public class CurrencyRatesServiceImp implements CurrencyRatesService{

    @Autowired
    private CurrencyFeignClient currencyFeignClient;

    @Override
    public CurrencyRates getYesterdayRate(String data, String appID) {
        return currencyFeignClient.getYesterdayRate(data, appID);
    }

    @Override
    public CurrencyRates getTodayRate(String appID) {
        return currencyFeignClient.getTodayRate(appID);
    }
}
