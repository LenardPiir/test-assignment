package com.helmes.sectorapi.registration.internal;

import com.helmes.sectorapi.customer.api.Customer;
import com.helmes.sectorapi.customer.api.CustomerService;
import com.helmes.sectorapi.customersector.api.CustomerSectorService;
import com.helmes.sectorapi.registration.api.RegistrationService;
import com.helmes.sectorapi.sector.api.SectorService;
import com.helmes.sectorapi.session.api.Session;
import com.helmes.sectorapi.session.api.SessionService;
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
    public RegistrationForm saveCustomer(RegistrationForm registration) {
        Session session = sessionService.getSession();

        customerSectorService.saveCustomerSectors(session.id(), registration.getSectors());

        customerService.saveCustomer(session.id(), registration.getName(), registration.isAgreeToTerms());

        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setName(registration.getName());

        List<String> registeredCustomerSectors = customerSectorService.getCustomerSectors(session.id()).stream().map((customerSector -> sectorService.getSector(customerSector.sectorCode()).name())).toList();

        registrationForm.setSectors(registeredCustomerSectors);
        registrationForm.setAgreeToTerms(registration.isAgreeToTerms());

        return registrationForm;
    }

    @Override
    public RegistrationForm getRegisteredCustomer() {
        Customer customer = customerService.getCustomer(sessionService.getSession().id());

        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setName(customer.name());
        registrationForm.setAgreeToTerms(customer.agreeToTerms());

        List<String> registeredCustomerSectors = customerSectorService.getCustomerSectors(customer.id()).stream().map((customerSector -> sectorService.getSector(customerSector.sectorCode()).name())).toList();

        registrationForm.setSectors(registeredCustomerSectors);

        return registrationForm;
    }
}
