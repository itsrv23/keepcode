package ru.itsrv23.keepcode_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itsrv23.keepcode_test.model.onlinesim.Country;
import ru.itsrv23.keepcode_test.service.PriceList;

import java.util.List;

@RestController
public class OnlineSimPriceListController {

    private final PriceList priceList;

    public OnlineSimPriceListController(PriceList priceList) {
        this.priceList = priceList;
    }
    /**
     * Задание 2. Для парсинга использую selenium,  chromedriver.exe в корне проекта
     * Скачать версию можно тут: https://chromedriver.storage.googleapis.com/index.html
     * Версия должна совпадать с установленным Chrome
     * http://127.0.0.1:8080/priceByCountry?update=true - для обновления устаревших данных
     * */
    @GetMapping("/priceByCountry")
    public List<Country> getPrice(@RequestParam(name = "update", required = false) Boolean forceUpdate){
        if(forceUpdate == null){
            return priceList.getPriceList(false);
        }
        return priceList.getPriceList(forceUpdate);
    }
}
