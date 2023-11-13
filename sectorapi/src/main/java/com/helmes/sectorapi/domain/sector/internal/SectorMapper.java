package com.helmes.sectorapi.domain.sector.internal;

import com.helmes.sectorapi.domain.sector.api.Sector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class SectorMapper {
    public Sector toDomain(SectorEntity entity) {
        return new Sector(
                entity.getCode(),
                entity.getName(),
                entity.getLevel(),
                toDomains(entity.getChildSectors())
        );
    }

    public List<Sector> toDomains(List<SectorEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities
                .stream()
                .map(this::toDomain)
                .toList();
    }
}
