package ru.edu.currencycomparator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.edu.currencycomparator.service.CurrencyRatesServiceImp;
import ru.edu.currencycomparator.service.GiphyServiceImp;

/**
 * Контроллер
 */
@Controller
@RequestMapping("/")
public class CurrencyRatesController {

    @Value("${openexchangerates.org.base_currency}")
    private String buseCurrency;

    @Value("${giphy.com.rich_tag}")
    private String richTag;

    @Value("${giphy.com.broken_tag}")
    private String brokenTag;

    @Value("${giphy.com.equal_tag}")
    private String equalTag;

    @Value("${giphy.com.error_tag}")
    private String errorTag;

    private final CurrencyRatesServiceImp currencyRatesServiceImp;
    private final GiphyServiceImp giphyServiceImp;

    @Autowired
    public CurrencyRatesController(CurrencyRatesServiceImp currencyRatesServiceImp, GiphyServiceImp giphyServiceImp) {
        this.currencyRatesServiceImp = currencyRatesServiceImp;
        this.giphyServiceImp = giphyServiceImp;
    }


    /**
     *  Отрисовывает страничку с гифкой в браузере
     * @param model
     * @return
     */
    @GetMapping
    public String index (Model model){
        String gifUrl = giphyServiceImp.getGif(errorTag);

        Double prevRate = currencyRatesServiceImp.getPreviousRate(buseCurrency);
        Double presentRate = currencyRatesServiceImp.getPresentRate(buseCurrency);

        if (prevRate > presentRate){
            gifUrl = giphyServiceImp.getGif(brokenTag);
        } else if ( prevRate < presentRate) {
            gifUrl = giphyServiceImp.getGif(richTag);
        }else if( prevRate.equals(presentRate)){
            gifUrl = giphyServiceImp.getGif(equalTag);
        }

        model.addAttribute("rate", prevRate);
        model.addAttribute("gifUrl", gifUrl);
        return "welcome";
    }


}
