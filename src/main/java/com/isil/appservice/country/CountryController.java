package com.isil.appservice.country;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/v1/countries")
public class CountryController {


    @GetMapping(value = {"/",""})
    public ResponseEntity<List<CountryResponse>> getAllCountries(){
        RestTemplate restTemplate = new RestTemplate();

        CountryResponse countries = restTemplate.getForObject("https://api.first.org/data/v1/countries",
                CountryResponse.class);

        if(countries!=null){
            return ResponseEntity.ok(List.of(countries)) ;
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

}
