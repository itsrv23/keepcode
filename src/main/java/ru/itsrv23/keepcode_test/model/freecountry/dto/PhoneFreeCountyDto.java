package ru.itsrv23.keepcode_test.model.freecountry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.itsrv23.keepcode_test.model.freecountry.Phone;

import java.util.List;

public class PhoneFreeCountyDto {
    @JsonProperty("response")
    private Integer response;
    @JsonProperty("numbers")
    private List<Phone> numbers;

    public PhoneFreeCountyDto() {
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public List<Phone> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Phone> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "ResponsePhoneFreeCounty{" +
                "response=" + response +
                ", numbers=" + numbers +
                '}';
    }
}
