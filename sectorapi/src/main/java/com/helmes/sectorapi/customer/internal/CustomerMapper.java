package com.helmes.sectorapi.customer.internal;

import com.helmes.sectorapi.customer.api.Customer;
import org.springframework.stereotype.Component;

@Component
class CustomerMapper {
    public Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getName(),
                entity.isAgreeToTerms()
        );
    }
}
