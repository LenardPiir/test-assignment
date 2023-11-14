package com.helmes.sectorapi.api.registration;

import com.helmes.sectorapi.domain.customer.api.Customer;
import com.helmes.sectorapi.domain.customer.api.CustomerService;
import com.helmes.sectorapi.domain.customersector.api.CustomerSectorService;
import com.helmes.sectorapi.domain.sector.api.SectorService;
import com.helmes.sectorapi.domain.session.api.Session;
import com.helmes.sectorapi.domain.session.api.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final SectorService sectorService;
    private final CustomerSectorService customerSectorService;
    private final CustomerService customerService;
    private final SessionService sessionService;

    @Override
    public Registration saveCustomer(Registration registration) {
        Session session = sessionService.getSession();

        customerSectorService.saveCustomerSectors(session.id(), registration.getSectors());

        customerService.saveCustomer(session.id(), registration.getName(), registration.isAgreeToTerms());

        Registration registrationForm = new Registration();
        registrationForm.setName(registration.getName());

        List<String> registeredCustomerSectors = customerSectorService.getCustomerSectors(session.id()).stream().map((customerSector -> sectorService.getSector(customerSector.sectorCode()).name())).toList();

        registrationForm.setSectors(registeredCustomerSectors);
        registrationForm.setAgreeToTerms(registration.isAgreeToTerms());

        return registrationForm;
    }

    @Override
    public Registration getRegisteredCustomer() {
        Customer customer = customerService.getCustomer(sessionService.getSession().id());

        Registration registration = new Registration();
        registration.setName(customer.name());
        registration.setAgreeToTerms(customer.agreeToTerms());

        List<String> registeredCustomerSectors = customerSectorService.getCustomerSectors(customer.id()).stream().map((customerSector -> sectorService.getSector(customerSector.sectorCode()).name())).toList();

        registration.setSectors(registeredCustomerSectors);

        return registration;
    }
}
