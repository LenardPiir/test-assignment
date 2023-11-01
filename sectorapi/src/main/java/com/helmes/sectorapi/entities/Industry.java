package com.helmes.sectorapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Industry {

    @Id
    private Long id;

    @OneToMany
    private List<IndustrySpecification> industrySpecifications;
}
