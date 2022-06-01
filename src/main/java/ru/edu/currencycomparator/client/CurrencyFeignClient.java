package ru.edu.currencycomparator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.edu.currencycomparator.model.CurrencyRates;

@FeignClient(name="CurrencyFeignClient", url = "${openexchangerates.org.url}")
public interface CurrencyFeignClient {

    @GetMapping("/historical/{date}.json")
    CurrencyRates getYesterdayRate (@PathVariable String date, @RequestParam("app_id") String appID);

    @GetMapping("/latest.json")
    CurrencyRates getTodayRate (@RequestParam("app_id") String appID);


}
