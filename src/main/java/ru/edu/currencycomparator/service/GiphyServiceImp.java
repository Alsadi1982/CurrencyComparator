package ru.edu.currencycomparator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.edu.currencycomparator.client.GiphyFeignClient;


/**
 * Сервис для работы с запросами с giphy.com
 */
@Service
public class GiphyServiceImp implements GiphyService{

    @Autowired
    private GiphyFeignClient giphyFeignClient;

    @Value("${giphy.com.api_key}")
    private String apiKey;

    /**
     * Возвращает URL рандомной гифки
     * @param tag
     * @return
     */
    @Override
    public String getGif(String tag){

        ResponseEntity<String> response = giphyFeignClient.getRandomGiphyGif(apiKey, tag);
        String result = "";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root =mapper.readTree(response.getBody());
            result= root.path("data").get("images").get("original").get("url").asText();
            System.out.println(result);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(response);
        return result;
    }
}
