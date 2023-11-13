package com.helmes.sectorapi.domain.customer.internal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface CustomerRepository extends CrudRepository<CustomerEntity, UUID> {
}
