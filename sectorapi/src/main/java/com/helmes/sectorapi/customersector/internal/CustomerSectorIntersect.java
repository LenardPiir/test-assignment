package com.helmes.sectorapi.customersector.internal;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
class CustomerSectorIntersect implements Serializable {
    private UUID customerId;
    private String sectorCode;
}
