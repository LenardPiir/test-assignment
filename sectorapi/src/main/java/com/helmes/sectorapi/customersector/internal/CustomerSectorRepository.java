package com.helmes.sectorapi.customersector.internal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface CustomerSectorRepository extends CrudRepository<CustomerSectorEntity, CustomerSectorIntersect> {
    List<CustomerSectorEntity> findAllById_CustomerId(UUID customerId);
}
