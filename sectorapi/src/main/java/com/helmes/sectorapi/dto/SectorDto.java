package com.helmes.sectorapi.dto;

import com.helmes.sectorapi.data.Industry;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SectorDto {

    private Long id;

    private String name;

    private List<Industry> industries;

}
