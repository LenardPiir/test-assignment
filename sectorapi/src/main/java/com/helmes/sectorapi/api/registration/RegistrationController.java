package com.helmes.sectorapi.api.registration;

import com.helmes.sectorapi.domain.sector.api.Sector;
import com.helmes.sectorapi.domain.sector.api.SectorService;
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
    public @ResponseBody Registration saveCustomer(@Validated @RequestBody Registration registration) {
        return registrationService.saveCustomer(registration);
    }

    @GetMapping("/customer")
    public @ResponseBody Registration getRegisteredCustomer() {
        return registrationService.getRegisteredCustomer();
    }
}
