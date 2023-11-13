package com.helmes.sectorapi.domain.customersector.internal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface CustomerSectorRepository extends CrudRepository<CustomerSectorEntity, CustomerSectorId> {
    List<CustomerSectorEntity> findAllById_CustomerId(UUID customerId);
}
