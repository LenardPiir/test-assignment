package com.helmes.sectorapi.registration.api;

import com.helmes.sectorapi.registration.internal.RegistrationForm;

public interface RegistrationService {

    RegistrationForm saveCustomer(RegistrationForm registrationForm);

    RegistrationForm getRegisteredCustomer();
}
