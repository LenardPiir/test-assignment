package com.helmes.sectorapi.data;

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
public class Sector {

    @Id
    private Long id;

    private String name;

    @OneToMany
    private List<Industry> industries;
}
