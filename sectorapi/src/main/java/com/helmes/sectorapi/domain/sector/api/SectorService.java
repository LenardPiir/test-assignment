package com.helmes.sectorapi.domain.sector.api;

import java.util.List;

public interface SectorService {
    List<Sector> getAllByLevel(int level);

    Sector getSector(String code);
}
