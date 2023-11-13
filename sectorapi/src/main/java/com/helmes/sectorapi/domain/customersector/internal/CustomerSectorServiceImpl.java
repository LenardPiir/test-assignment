package com.helmes.sectorapi.domain.customersector.internal;

import com.helmes.sectorapi.domain.customersector.api.CustomerSector;
import com.helmes.sectorapi.domain.customersector.api.CustomerSectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        List<CustomerSectorEntity> entities = repository.findAllById_CustomerId(customerId);

        deleteMissingCustomerSectors(entities, sectorCodes);
        saveMissingSectors(customerId, entities, sectorCodes);

        return mapper.toDomains(repository.findAllById_CustomerId(customerId));
    }

    private void deleteMissingCustomerSectors(List<CustomerSectorEntity> entities, List<String> sectorCodes) {
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }

        entities.forEach(entity -> {
            if (!sectorCodes.contains(entity.getId().getSectorCode())) {
                repository.delete(entity);
            }
        });
    }

    private void saveMissingSectors(UUID customerId, List<CustomerSectorEntity> entities, List<String> sectorCodes) {
        if (CollectionUtils.isEmpty(sectorCodes)) {
            return;
        }

        sectorCodes.forEach(sectorCode -> {
            if (entities.stream().noneMatch(entity -> entity.getId().getSectorCode().equals(sectorCode))) {
                CustomerSectorEntity entity = new CustomerSectorEntity();
                CustomerSectorId id = new CustomerSectorId();
                id.setCustomerId(customerId);
                id.setSectorCode(sectorCode);
                entity.setId(id);
                repository.save(entity);
            }
        });
    }
}
