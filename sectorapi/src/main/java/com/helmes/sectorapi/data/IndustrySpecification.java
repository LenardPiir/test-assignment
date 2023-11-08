package com.helmes.sectorapi.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class IndustrySpecification {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="industry_id", nullable=false)
    @JsonIgnore
    private Industry industry;

}
