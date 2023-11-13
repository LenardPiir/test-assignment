package com.helmes.sectorapi.domain.customersector.api;

import java.io.Serializable;
import java.util.UUID;

public record CustomerSector(UUID customerId, String sectorCode) implements Serializable {
}

