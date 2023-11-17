package com.helmes.sectorapi.registration.internal;

import com.helmes.sectorapi.registration.api.RegistrationService;
import com.helmes.sectorapi.sector.api.Sector;
import com.helmes.sectorapi.sector.api.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sector-api")
@RequiredArgsConstructor
public class RegistrationController {
    private final SectorService sectorService;
    private final RegistrationService registrationService;

    @GetMapping("/sectors")
    public List<Sector> getSectorsByLevel() {
        return sectorService.getAllByLevel(1);
    }

    @PostMapping("/customer")
    public @ResponseBody RegistrationForm saveCustomer(@Validated @RequestBody RegistrationForm registrationForm) {
        return registrationService.saveCustomer(registrationForm);
    }

    @GetMapping("/customer")
    public @ResponseBody RegistrationForm getRegisteredCustomer() {
        return registrationService.getRegisteredCustomer();
    }
}
