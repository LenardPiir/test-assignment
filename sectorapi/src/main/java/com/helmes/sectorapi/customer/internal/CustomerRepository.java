package com.helmes.sectorapi.customer.internal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface CustomerRepository extends CrudRepository<CustomerEntity, UUID> {
}
