package com.helmes.sectorapi.domain.customer.internal;

import com.helmes.sectorapi.domain.customer.api.Customer;
import com.helmes.sectorapi.domain.customer.api.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper mapper;
    private final CustomerRepository repository;

    @Override
    public Customer saveCustomer(UUID id, String name, boolean agreeToTerms) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setAgreeToTerms(agreeToTerms);
        entity = repository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public Customer getCustomer(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseGet(() -> new Customer(id, null, false));
    }
}

