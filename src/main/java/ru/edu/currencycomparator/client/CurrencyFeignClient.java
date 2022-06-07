package ru.edu.currencycomparator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.edu.currencycomparator.model.CurrencyRates;

/**
 * Feign Client для получения информации о курсе валют относительно USD с сайта openexchangerates.org
 */
@FeignClient(name="CurrencyFeignClient", url = "${openexchangerates.org.url}")
public interface CurrencyFeignClient {

    /**
     * Отправляет GET запрос для получения информации о курсе валют за определенную дату
     * @param date дата за которую нужно получить курс валют
     * @param appID идентификационный ключ пользователя, генерируется на сайте openexchangerates.org при регистрации
     * @return возвращает заполненную модель CurrencyRate
     */
    @GetMapping("/historical/{date}.json")
    CurrencyRates getYesterdayRate (@PathVariable String date, @RequestParam("app_id") String appID);

    /**
     * Отправляет GET запрос для получения информации об актуальных курсах валют
     * @param appID идентификационный ключ пользователя, генерируется на сайте openexchangerates.org при регистрации
     * @return возвращает заполненную модель CurrencyRate
     */
    @GetMapping("/latest.json")
    CurrencyRates getTodayRate (@RequestParam("app_id") String appID);


}
