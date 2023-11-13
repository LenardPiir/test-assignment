package com.helmes.sectorapi.api.registration;

import com.helmes.sectorapi.domain.customer.api.CustomerService;
import com.helmes.sectorapi.domain.customersector.api.CustomerSector;
import com.helmes.sectorapi.domain.customersector.api.CustomerSectorService;
import com.helmes.sectorapi.domain.sector.api.Sector;
import com.helmes.sectorapi.domain.sector.api.SectorService;
import com.helmes.sectorapi.domain.session.api.Session;
import com.helmes.sectorapi.domain.session.api.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sector-api")
@RequiredArgsConstructor
public class RegistrationController {
    private final SectorService sectorService;
    private final SessionService sessionService;
    private final CustomerSectorService customerSectorService;
    private final CustomerService customerService;

    @GetMapping("/sectors")
    public List<Sector> getSectorsByLevel() {
        return sectorService.getAllByLevel(1);
    }

    @PostMapping("/sectors")
    public @ResponseBody List<CustomerSector> saveSectors(@RequestBody Registration registration) {
        Session session = sessionService.getSession();

        customerService.saveCustomer(session.id(), registration.getName(), registration.isAgreeToTerms());

        return customerSectorService.saveCustomerSectors(session.id(), registration.getSectors());
    }
}
