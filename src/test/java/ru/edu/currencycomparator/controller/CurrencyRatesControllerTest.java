package ru.edu.currencycomparator.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import ru.edu.currencycomparator.service.CurrencyRatesServiceImp;
import ru.edu.currencycomparator.service.GiphyServiceImp;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Класс для тестирования контроллера CurrencyRatesController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyRatesControllerTest {

    @MockBean
    private CurrencyRatesServiceImp currencyRatesServiceImp;

    @MockBean
    private GiphyServiceImp giphyServiceImp;

    @Mock
    private Model model;

    @Autowired
    private CurrencyRatesController currencyRatesController;

    /**
     * Позитивный сценарий выполнения метода CurrencyRatesController№штвуч()
     * должен вернуть путь к html файлу welcome
     */
    @Test
    public void index_Success_Test(){

        Mockito.when(currencyRatesServiceImp.getPreviousRate(anyString())).thenReturn(4.0);
        Mockito.when(currencyRatesServiceImp.getPresentRate(anyString())).thenReturn(5.0);
        Mockito.when(giphyServiceImp.getGif(anyString())).thenReturn(anyString());

        String actual = currencyRatesController.index(model);
        Assertions.assertEquals("welcome", actual);

        verify(currencyRatesServiceImp, times(1)).getPreviousRate(anyString());
        verify(currencyRatesServiceImp, times(1)).getPresentRate(anyString());
        verify(giphyServiceImp, times(2)).getGif(anyString());
        verify(model, times(1)).addAttribute(any(), any());
        Assertions.assertDoesNotThrow(() -> currencyRatesController.index(model));
    }

    /**
     * Негативный сценарий выполнения метода CurrencyRatesController№штвуч()
     * должен вернуть путь к html файлу error
     */
    @Test
    public void index_Fail_Test(){

        Mockito.when(currencyRatesServiceImp.getPreviousRate(anyString())).thenThrow(IllegalArgumentException.class);
        Mockito.when(currencyRatesServiceImp.getPresentRate(anyString())).thenReturn(5.0);
        Mockito.when(giphyServiceImp.getGif(anyString())).thenReturn(anyString());

        String actual = currencyRatesController.index(model);
        Assertions.assertEquals("error", actual);

        verify(currencyRatesServiceImp, times(1)).getPreviousRate(anyString());
        verify(currencyRatesServiceImp, times(0)).getPresentRate(anyString());
        verify(giphyServiceImp, times(1)).getGif(anyString());
        verify(model, times(1)).addAttribute(any(), any());
    }
}
