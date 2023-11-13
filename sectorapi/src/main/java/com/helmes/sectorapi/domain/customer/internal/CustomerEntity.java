package com.helmes.sectorapi.domain.customer.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
class CustomerEntity {
    @Id
    private UUID id;
    private String name;
    private boolean agreeToTerms;
}
