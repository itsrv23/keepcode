package ru.itsrv23.keepcode_test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itsrv23.keepcode_test.model.freecountry.Country;
import ru.itsrv23.keepcode_test.model.freecountry.CustomPhones;
import ru.itsrv23.keepcode_test.model.freecountry.Phone;
import ru.itsrv23.keepcode_test.model.freecountry.dto.FreeCountryListDto;
import ru.itsrv23.keepcode_test.model.freecountry.dto.PhoneFreeCountyDto;
import ru.itsrv23.keepcode_test.model.freecountry.dto.ResponseCustomPhonesDto;
import ru.itsrv23.keepcode_test.service.FreeCountryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FreeCountryServiceImpl implements FreeCountryService {

    private final static Logger logger = LoggerFactory.getLogger(FreeCountryServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${addr.free.country}")
    private String URL_COUNTRY;

    @Value("${addr.free.country.phones}")
    private String URL_COUNTRY_PHONES;

    @Override
    public ResponseCustomPhonesDto getPhones() {
        FreeCountryListDto response = restTemplate.getForObject(URL_COUNTRY, FreeCountryListDto.class);
        logger.debug("Response {}, {}", URL_COUNTRY, response);
        List<Country> countries = new ArrayList<>(response.getCountries());

        Map<String, List<CustomPhones>> collect = countries.stream()
                .parallel()
                .map(this::getCustomPhones
                ).collect(Collectors.groupingBy(CustomPhones::getCountry));

        ResponseCustomPhonesDto phonesDto = new ResponseCustomPhonesDto(collect);
        logger.debug("ResponseCustomPhonesDto: {}", phonesDto);
        return phonesDto;
    }


    private CustomPhones getCustomPhones(Country country) {
        String countryText = country.getCountryText();
        ResponseEntity<PhoneFreeCountyDto> exchange = getExchangeCountryPhones(country);
        if (exchange != null && exchange.getStatusCode().is2xxSuccessful()) {
            logger.debug("Response {}={}, {}", URL_COUNTRY_PHONES, country.getCountry(), exchange);
            PhoneFreeCountyDto body = exchange.getBody();
            if (body != null) {
                List<String> listFullNumbers = body.getNumbers().stream()
                        .map(Phone::getFullNumber)
                        .collect(Collectors.toList());
                // По ТЗ можем логировать полученные результаты в консоль
                logger.info("County: {}, phones: {}", countryText, listFullNumbers);
                return new CustomPhones(countryText, listFullNumbers);
            }
        }
        return new CustomPhones(countryText, Collections.emptyList());
    }

    private ResponseEntity<PhoneFreeCountyDto> getExchangeCountryPhones(Country country) {
        try {
            return restTemplate.exchange(URL_COUNTRY_PHONES,
                    HttpMethod.GET,
                    new HttpEntity<>(HttpEntity.EMPTY),
                    PhoneFreeCountyDto.class,
                    country.getCountry());
        } catch (RuntimeException e) {
            //При частных обращениях получаем 503 Service Temporarily Unavailable
            //Тут нужно уточнить у заказчика, на сколько ему важна скорость, или 100% выборка
            //Если да, то возвращать ошибку, если по какой-то стране нет ответа
            //Или переходить в однопоточный режим, но там гарантии доступности сервиса тоже нет
            //Или запускать по крону, кешировать данные и отдавать из кеша
            logger.info("Не получили инфу по стране: {}, {}", country.getCountryText(), e.getMessage());
        }
        return null;
    }

}
