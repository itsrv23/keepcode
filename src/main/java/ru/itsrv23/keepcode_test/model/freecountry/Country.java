package ru.itsrv23.keepcode_test.model.freecountry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    @JsonProperty("country")
    private Integer country;
    @JsonProperty("country_text")
    private String countryText;


    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getCountryText() {
        return countryText;
    }

    public void setCountryText(String countryText) {
        this.countryText = countryText;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country=" + country +
                ", countryText='" + countryText + '\'' +
                '}';
    }
}
