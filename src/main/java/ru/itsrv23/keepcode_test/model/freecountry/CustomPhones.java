package ru.itsrv23.keepcode_test.model.freecountry;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CustomPhones {
    @JsonIgnore
    private String country;
    private List<String> phones;

    public CustomPhones(String country, List<String> phones) {
        this.country = country;
        this.phones = phones;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "CustomPhones{" +
                "country='" + country + '\'' +
                ", phones=" + phones +
                '}';
    }
}
