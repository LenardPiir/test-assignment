package com.helmes.sectorapi.sector.internal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SectorRepository extends CrudRepository<SectorEntity, String> {
    List<SectorEntity> findAllByLevel(int level);
    List<SectorEntity> findAll();
}