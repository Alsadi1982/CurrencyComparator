package ru.edu.currencycomparator.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.edu.currencycomparator.client.CurrencyFeignClient;
import ru.edu.currencycomparator.model.CurrencyRates;

public interface CurrencyRatesService {

    CurrencyRates getYesterdayRate (String data, String appID);
    CurrencyRates getTodayRate (String appID);
}
