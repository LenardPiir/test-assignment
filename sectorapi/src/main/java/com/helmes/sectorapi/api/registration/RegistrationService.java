package com.helmes.sectorapi.api.registration;

public interface RegistrationService {

    Registration saveCustomer(Registration registration);

    Registration getRegisteredCustomer();
}
