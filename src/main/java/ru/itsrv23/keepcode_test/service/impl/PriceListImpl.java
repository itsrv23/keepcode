package ru.itsrv23.keepcode_test.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itsrv23.keepcode_test.model.onlinesim.Country;
import ru.itsrv23.keepcode_test.repository.CountryRepository;
import ru.itsrv23.keepcode_test.service.PriceList;
import ru.itsrv23.keepcode_test.utils.Parser;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PriceListImpl implements PriceList {

    private final static Logger logger = LoggerFactory.getLogger(PriceListImpl.class);

    @Autowired
    private CountryRepository repository;

    @Value("${addr.onlinesim.price}")
    private String URL_PRISE_LIST;
    @Value("${addr.onlinesim.price.countries}")
    private String URL_BY_COUNTRY;


    @Override
    public List<Country> getPriceList(boolean forceUpdate) {
        OffsetDateTime actual = repository.getDateTimeActual();
        if (actual != null && !forceUpdate) {
            return repository.getCountries();
        }
        findAllCountries();
        fillPrice();
        return repository.getCountries();
    }

    private void findAllCountries() {
        String page = getPage(URL_PRISE_LIST);
        if (page == null) {
            logger.error("Error pageSource is null!");
            return;
        }
        Document parse = Jsoup.parse(page);
        Elements elements = parse.getElementsByClass("col-md-2 col-xs-6 country-block no-padding");

        for (Element element : elements) {
            Element child = element.child(0);
            if (child.id().startsWith("country-")) {
                Country country = new Country();
                Integer countryId = Parser.parseCountryId(child.id());
                // Для тестов, что бы не парсить все страны, можно ограничиться первыми.
//                if(countryId > 8){
//                    return;
//                }
                country.setCountryId(countryId);
                country.setName(child.getElementsByClass("country-name").text());
                repository.getCountries().add(country);
                logger.info("URL: {}{}", URL_BY_COUNTRY, countryId);
            }
        }
        logger.info("repository.getCountries()= {}", repository.getCountries());
    }

    private void fillPrice() {
        List<Country> countries = repository.getCountries();
        for (Country country : countries) {
            Integer countryId = country.getCountryId();
            String page = getPage(URL_BY_COUNTRY + countryId);
            logger.debug("Заполняем прайс для страны: {}", country.getName());

            Document parseByCountry = Jsoup.parse(page);
            Elements elementsByCountry = parseByCountry.getElementsByClass("col-md-2 col-xs-6 country-block no-padding");
            for (Element elementByCountry : elementsByCountry) {
                Element elementName = elementByCountry.getElementsByClass("price-name").first();
                Element elementPrice = elementByCountry.getElementsByClass("price-text").first();
                if (elementName != null && elementPrice != null) {
                    logger.debug("По стране: {},{},{}", country.getName(), elementName.text(), elementPrice.text());
                    country.getPriceMap().put(elementName.text(), elementPrice.text().replace("р", ""));
                }
            }
        }
        repository.setDateTimeActual(OffsetDateTime.now());
        logger.info("Закончили заполнять прайс \n {}", repository.getDateTimeActual());
        logger.info("Result \n {}", countries);
    }

    private String getPage(String url) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        String pageSource;
        try {
            driver.get(url);
            pageSource = driver.getPageSource();
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
        return pageSource;
    }
}
