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
    private final CustomerSectorRepository customerSectorRepository;
    private final CustomerSectorMapper mapper;

    @Override
    public List<CustomerSector> getCustomerSectors(UUID customerId) {
        return mapper.toDomains(customerSectorRepository.findAllById_CustomerId(customerId));
    }

    @Override
    public List<CustomerSector> saveCustomerSectors(UUID customerId, List<String> sectorCodes) {
        List<CustomerSectorEntity> entities = customerSectorRepository.findAllById_CustomerId(customerId);

        deleteMissingCustomerSectors(entities, sectorCodes);
        saveMissingSectors(customerId, entities, sectorCodes);

        return mapper.toDomains(customerSectorRepository.findAllById_CustomerId(customerId));
    }

    private void deleteMissingCustomerSectors(List<CustomerSectorEntity> entities, List<String> sectorCodes) {
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }

        entities.forEach(entity -> {
            if (!sectorCodes.contains(entity.getId().getSectorCode())) {
                customerSectorRepository.delete(entity);
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
                CustomerSectorId customerSectorId = new CustomerSectorId();
                customerSectorId.setCustomerId(customerId);
                customerSectorId.setSectorCode(sectorCode);
                entity.setId(customerSectorId);
                customerSectorRepository.save(entity);
            }
        });
    }
}
