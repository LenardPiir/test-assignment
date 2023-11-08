package com.helmes.sectorapi.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy="sector")
    private List<Industry> industries;
}
