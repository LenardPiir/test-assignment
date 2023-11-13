package com.helmes.sectorapi.domain.customer.api;

import java.util.UUID;

public interface CustomerService {
    Customer saveCustomer(UUID id, String name, boolean agreeToTerms);
    Customer getCustomer(UUID id);
}
