package com.helmes.sectorapi.sector.api;

import java.util.List;

public interface SectorService {
    List<Sector> getAllByLevel(int level);

    Sector getSector(String code);
}
