package com.helmes.sectorapi.domain.customersector.api;

import java.util.List;
import java.util.UUID;

public interface CustomerSectorService {
    List<CustomerSector> getCustomerSectors(UUID customerId);

    List<CustomerSector> saveCustomerSectors(UUID customerId, List<String> sectorCodes);
}