package com.helmes.sectorapi.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    private String name;

    @OneToMany(mappedBy="industry")
    private List<IndustrySpecification> industrySpecifications;

    @ManyToOne
    @JoinColumn(name="sector_id", nullable=false)
    @JsonIgnore
    private Sector sector;
}
