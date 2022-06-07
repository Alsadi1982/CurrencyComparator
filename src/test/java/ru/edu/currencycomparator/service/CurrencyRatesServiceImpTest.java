package ru.edu.currencycomparator.service;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.edu.currencycomparator.client.CurrencyFeignClient;
import ru.edu.currencycomparator.model.CurrencyRates;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * Класс для тестирования сервиса CurrencyRateServiceImp
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyRatesServiceImpTest {

    @MockBean
    private CurrencyFeignClient currencyFeignClient;

    @Autowired
    private CurrencyRatesServiceImp currencyRatesServiceImp;

    private CurrencyRates currencyRates;

    @BeforeEach
    public void before(){
        this.currencyRates = new CurrencyRates();
        Map <String, Double> rateMap = new HashMap<>();
        rateMap.put("RUB", 63.0000034);
        currencyRates.setRates(rateMap);
        currencyRates.setTimestamp(LocalDate.now().minusDays(1).toEpochDay());
    }

    /**
     * Проверяет успешное выполнение метода CurrencyRatesServiceImp#getPreviousRate(String baseCurrency)
     */
    @Test
    public void getPreviousRate_Success_Test(){

        Mockito.when(currencyFeignClient.getYesterdayRate(anyString(), anyString())).thenReturn(this.currencyRates);
        Double expected = 63.0000034;
        Double actual = currencyRatesServiceImp.getPreviousRate("RUB");
        Assertions.assertEquals(expected, actual);

        Mockito.verify(currencyFeignClient, Mockito.times(1)).getYesterdayRate(anyString(), anyString());

    }

    /**
     * Проверяет неуспешное выполнение метода CurrencyRatesServiceImp#getPreviousRate(String baseCurrency)
     * например когда пользователь ошибочно или умышленно вбил наименование валюты которой не существует
     */
    @Test
    public void getPreviousRate_Fail_Test(){

        Mockito.when(currencyFeignClient.getYesterdayRate(anyString(), anyString())).thenReturn(this.currencyRates);
        Assertions.assertThrows(IllegalArgumentException.class, ()->currencyRatesServiceImp.getPreviousRate("WWW"));

    }

    /**
     * Проверяет успешное выполнение метода CurrencyRatesServiceImp#getPresentRate(String baseCurrency)
     */
    @Test
    public void getPresentRate_Success_Test(){

        Mockito.when(currencyFeignClient.getTodayRate(anyString())).thenReturn(this.currencyRates);
        Double expected = 63.0000034;
        Double actual = currencyRatesServiceImp.getPresentRate("RUB");
        Assertions.assertEquals(expected, actual);

        Mockito.verify(currencyFeignClient, Mockito.times(1)).getTodayRate(anyString());
    }

    /**
     * Проверяет неуспешное выполнение метода CurrencyRatesServiceImp#getPresentRate(String baseCurrency)
     * например когда пользователь ошибочно или умышленно вбил наименование валюты которой не существует
     */
    @Test
    public void getPresentRate_Fail_Test(){

        Mockito.when(currencyFeignClient.getTodayRate(anyString())).thenReturn(this.currencyRates);
        Assertions.assertThrows(IllegalArgumentException.class, ()->currencyRatesServiceImp.getPresentRate("WWW"));

    }
}
