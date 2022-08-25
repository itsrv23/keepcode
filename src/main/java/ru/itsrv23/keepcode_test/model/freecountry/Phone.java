package ru.itsrv23.keepcode_test.model.freecountry;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Phone {
    @JsonProperty("number")
    private String number;
    @JsonProperty("country")
    private Integer country;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("data_humans")
    private String dataHumans;
    @JsonProperty("full_number")
    private String fullNumber;
    @JsonProperty("country_text")
    private String countryText;
    @JsonProperty("maxdate")
    private String maxdate;
    @JsonProperty("status")
    private String status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("country")
    public Integer getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(Integer country) {
        this.country = country;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("data_humans")
    public String getDataHumans() {
        return dataHumans;
    }

    @JsonProperty("data_humans")
    public void setDataHumans(String dataHumans) {
        this.dataHumans = dataHumans;
    }

    @JsonProperty("full_number")
    public String getFullNumber() {
        return fullNumber;
    }

    @JsonProperty("full_number")
    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }

    @JsonProperty("country_text")
    public String getCountryText() {
        return countryText;
    }

    @JsonProperty("country_text")
    public void setCountryText(String countryText) {
        this.countryText = countryText;
    }

    @JsonProperty("maxdate")
    public String getMaxdate() {
        return maxdate;
    }

    @JsonProperty("maxdate")
    public void setMaxdate(String maxdate) {
        this.maxdate = maxdate;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                ", country=" + country +
                ", updatedAt='" + updatedAt + '\'' +
                ", dataHumans='" + dataHumans + '\'' +
                ", fullNumber='" + fullNumber + '\'' +
                ", countryText='" + countryText + '\'' +
                ", maxdate='" + maxdate + '\'' +
                ", status='" + status + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
