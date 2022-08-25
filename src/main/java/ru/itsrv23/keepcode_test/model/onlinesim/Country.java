package ru.itsrv23.keepcode_test.model.onlinesim;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Country {
    @JsonIgnore
    private Integer countryId;
    private String name;
    private Map<String, String> priceMap =  new HashMap<>();


    public Country() {
    }

    public Country(Integer countryId, String name, Map<String, String> priceMap) {
        this.countryId = countryId;
        this.name = name;
        this.priceMap = priceMap;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, String> priceMap) {
        this.priceMap = priceMap;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryId, country.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                ", price=" + priceMap +
                '}';
    }
}
