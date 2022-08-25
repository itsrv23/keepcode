package ru.itsrv23.keepcode_test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itsrv23.keepcode_test.model.freecountry.dto.ResponseCustomPhonesDto;
import ru.itsrv23.keepcode_test.service.FreeCountryService;

@RestController
public class FreeCountryController {

    private final FreeCountryService freeCountryService;

    public FreeCountryController(FreeCountryService freeCountryService) {
        this.freeCountryService = freeCountryService;
    }


    @GetMapping("/getFreePhonesByCountry")
    public ResponseEntity<ResponseCustomPhonesDto> getFreeCountry() {
        ResponseCustomPhonesDto phones = freeCountryService.getPhones();
        if (phones != null) {
            return ResponseEntity.ok(phones);
        }
        return ResponseEntity.noContent().build();
    }
}
