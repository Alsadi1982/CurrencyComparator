package ru.edu.currencycomparator.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Feign Client для получения рандомной гифки с сайта giphy.com
 */
@FeignClient(name = "GiphyFeignClient", url = "${giphy.com.url}")
public interface GiphyFeignClient {

    @GetMapping("/random")
    ResponseEntity<String> getRandomGiphyGif (@RequestParam String apiKey, @RequestParam String tag);
}
