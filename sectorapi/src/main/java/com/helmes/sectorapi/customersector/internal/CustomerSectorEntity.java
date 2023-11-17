package com.helmes.sectorapi.customersector.internal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "customer_sector")
@Data
@AllArgsConstructor
@NoArgsConstructor
class CustomerSectorEntity {
    @EmbeddedId
    private CustomerSectorIntersect id;
}
