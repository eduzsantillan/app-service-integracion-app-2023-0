package com.isil.appservice.country;


import lombok.Data;


import java.util.Map;

@Data
public class CountryResponse {
    private Map<String,InnerCountryResponse> data;
}

@Data
class InnerCountryResponse {
    private String country;
    private String region;
}
