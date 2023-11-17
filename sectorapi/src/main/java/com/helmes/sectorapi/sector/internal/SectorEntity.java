package com.helmes.sectorapi.sector.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity(name = "sector")
@Data
@AllArgsConstructor
@NoArgsConstructor
class SectorEntity implements Serializable {
    @Id
    private String code;
    private String name;
    private int level;
    @ManyToOne
    @JoinColumn(name = "parent_code")
    private SectorEntity parentSector;
    @OneToMany(mappedBy = "parentSector")
    private List<SectorEntity> childSectors;
}
