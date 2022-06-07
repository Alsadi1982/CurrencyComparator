package ru.edu.currencycomparator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.edu.currencycomparator.client.CurrencyFeignClient;
import ru.edu.currencycomparator.model.CurrencyRates;

import java.time.LocalDate;

/**
 * Сервис для работы с запросами openexchangerates.org
 */
@Service
public class CurrencyRatesServiceImp implements CurrencyRatesService{

    @Value("${openexchangerates.org.app_id}")
    private String appID;

    private final CurrencyFeignClient currencyFeignClient;

    @Autowired
    public CurrencyRatesServiceImp(CurrencyFeignClient currencyFeignClient) {
        this.currencyFeignClient = currencyFeignClient;
    }


    /**
     * Возвращает значение курса валюты за предыдущий день
     * @param baseCurrency наименование волюты курс которой нас интересует, задается в файле application.properties
     * @return значение курса валюты
     */
    @Override
    public Double getPreviousRate(String baseCurrency) {
        String yesterdayDate = this.getYesterdayDate();
        CurrencyRates previousRate = currencyFeignClient.getYesterdayRate(yesterdayDate, appID);
        if (!previousRate.getRates().containsKey(baseCurrency)){
            throw new IllegalArgumentException("Currency " + baseCurrency + " doesn't exist!");
        }
        return previousRate.getRates().get(baseCurrency);
    }


    /**
     * Возвращает актуалный курс валюты
     * @param baseCurrency наименование волюты курс которой нас интересует, задается в файле application.properties
     * @return значение курса валюты
     */
    @Override
    public Double getPresentRate(String baseCurrency) {
        CurrencyRates presentRate = currencyFeignClient.getTodayRate(appID);
        if (!presentRate.getRates().containsKey(baseCurrency)) {
            throw new IllegalArgumentException("Currency " + baseCurrency + " doesn't exist!");
        }
        return presentRate.getRates().get(baseCurrency);
    }

    /**
     * Вспомогательная функция для рассчета даты предыдущего дня
     * @return дата предыдущего дня в формате YYYY-MM-DD
     */
    private String getYesterdayDate() {

        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);
        System.out.println(yesterday);

        return yesterday.toString();
    }

}
