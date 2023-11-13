package com.helmes.sectorapi.domain.customersector.internal;

import com.helmes.sectorapi.domain.customersector.api.CustomerSector;
import com.helmes.sectorapi.domain.customersector.api.CustomerSectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class CustomerSectorServiceImpl implements CustomerSectorService {
    private final CustomerSectorRepository repository;
    private final CustomerSectorMapper mapper;

    @Override
    public List<CustomerSector> getCustomerSectors(UUID customerId) {
        return mapper.toDomains(repository.findAllById_CustomerId(customerId));
    }

    @Override
    public List<CustomerSector> saveCustomerSectors(UUID customerId, List<String> sectorCodes) {
        //TODO: refactor
        List<CustomerSectorEntity> entities = repository.findAllById_CustomerId(customerId);

        entities.forEach(entity -> {
            if (!sectorCodes.contains(entity.getId().getSectorCode())) {
                repository.delete(entity);
            }
        });

        sectorCodes.forEach(sectorCode -> {
            CustomerSectorEntity entity = new CustomerSectorEntity();
            CustomerSectorId id = new CustomerSectorId();
            id.setCustomerId(customerId);
            id.setSectorCode(sectorCode);
            entity.setId(id);
            repository.save(entity);
        });

        return mapper.toDomains(repository.findAllById_CustomerId(customerId));
    }
}
