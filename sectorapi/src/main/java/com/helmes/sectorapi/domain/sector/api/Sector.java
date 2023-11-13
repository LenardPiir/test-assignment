package com.helmes.sectorapi.domain.sector.api;

import java.io.Serializable;
import java.util.List;

public record Sector(String code, String name, int level, List<Sector> children) implements Serializable {
}
