package ru.edu.currencycomparator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.edu.currencycomparator.client.GiphyFeignClient;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * Класс для тестирования сервиса GiphyServiceImp
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GiphyServiceImpTest {

    @MockBean
    private GiphyFeignClient giphyFeignClient;

    @Autowired
    private GiphyServiceImp giphyServiceImp;

    @Value("${responseEntity.forTest}")
    private String testStr;

    /**
     * Проверяет успешное выполнение метода GiphyServiceImp#getGif(String tag)
     */
    @Test
    public void getGif_Test(){

        ResponseEntity<String> entity = new ResponseEntity<>(testStr, HttpStatus.OK);
        Mockito.when(giphyFeignClient.getRandomGiphyGif(anyString(), anyString())).thenReturn(entity);

        String expected = "hello.gif";
        Assertions.assertEquals(expected, giphyServiceImp.getGif("randomTag"));

        Mockito.verify(giphyFeignClient, Mockito.times(1)).getRandomGiphyGif(anyString(), anyString());
        Assertions.assertDoesNotThrow(() -> giphyServiceImp.getGif("randomTag"));
    }
}
