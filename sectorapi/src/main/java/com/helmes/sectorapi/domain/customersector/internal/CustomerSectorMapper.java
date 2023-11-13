package com.helmes.sectorapi.domain.customersector.internal;

import com.helmes.sectorapi.domain.customersector.api.CustomerSector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class CustomerSectorMapper {
    public CustomerSector toDomain(CustomerSectorEntity entity) {
        CustomerSectorId id = entity.getId();

        return new CustomerSector(
                id.getCustomerId(),
                id.getSectorCode()
        );
    }

    public List<CustomerSector> toDomains(List<CustomerSectorEntity> customerSectors) {
        return customerSectors.stream().map(this::toDomain).toList();
    }
}
