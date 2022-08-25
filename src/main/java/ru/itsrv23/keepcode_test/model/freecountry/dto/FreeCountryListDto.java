package ru.itsrv23.keepcode_test.model.freecountry.dto;

import ru.itsrv23.keepcode_test.model.freecountry.Country;

import java.util.List;
import java.util.Objects;

public class FreeCountryListDto {
    private Integer response;
    private List<Country> countries;

    public FreeCountryListDto() {
    }

    public FreeCountryListDto(Integer response, List<Country> countries) {
        this.response = response;
        this.countries = countries;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeCountryListDto freeCountryListDto1 = (FreeCountryListDto) o;
        return Objects.equals(response, freeCountryListDto1.response) && Objects.equals(countries, freeCountryListDto1.countries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response, countries);
    }

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                ", countries=" + countries +
                '}';
    }
}
