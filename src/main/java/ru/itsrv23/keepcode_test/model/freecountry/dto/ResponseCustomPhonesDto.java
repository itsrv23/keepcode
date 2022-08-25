package ru.itsrv23.keepcode_test.model.freecountry.dto;

import ru.itsrv23.keepcode_test.model.freecountry.CustomPhones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseCustomPhonesDto {
    private Map<String, List<CustomPhones>> mapPhonesByCountry = new HashMap<>();

    public ResponseCustomPhonesDto(Map<String, List<CustomPhones>> mapPhonesByCountry) {
        this.mapPhonesByCountry = mapPhonesByCountry;
    }

    public Map<String, List<CustomPhones>> getMapPhonesByCountry() {
        return mapPhonesByCountry;
    }

    public void setMapPhonesByCountry(Map<String, List<CustomPhones>> mapPhonesByCountry) {
        this.mapPhonesByCountry = mapPhonesByCountry;
    }

    @Override
    public String toString() {
        return "ResponseCustomPhonesDto{" +
                "mapPhonesByCountry=" + mapPhonesByCountry +
                '}';
    }
}
