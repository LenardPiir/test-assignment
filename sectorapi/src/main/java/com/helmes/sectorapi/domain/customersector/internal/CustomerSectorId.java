package com.helmes.sectorapi.domain.customersector.internal;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
class CustomerSectorId implements Serializable {
    private UUID customerId;
    private String sectorCode;
}
