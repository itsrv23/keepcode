package ru.itsrv23.keepcode_test.repository;

import org.springframework.stereotype.Repository;
import ru.itsrv23.keepcode_test.model.onlinesim.Country;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CountryRepository {
    private OffsetDateTime dateTimeActual;
    private List<Country> countries = new ArrayList<>();

    public CountryRepository() {
    }

    public OffsetDateTime getDateTimeActual() {
        return dateTimeActual;
    }

    public void setDateTimeActual(OffsetDateTime dateTimeActual) {
        this.dateTimeActual = dateTimeActual;
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
        CountryRepository that = (CountryRepository) o;
        return Objects.equals(dateTimeActual, that.dateTimeActual) && Objects.equals(countries, that.countries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTimeActual, countries);
    }

    @Override
    public String toString() {
        return "CountryRepository{" +
                "dateTimeActual=" + dateTimeActual +
                ", countries=" + countries +
                '}';
    }
}
