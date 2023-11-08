package com.helmes.sectorapi.service;

import com.helmes.sectorapi.dto.SectorDto;
import com.helmes.sectorapi.repository.SectorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
@AllArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;

    public List<SectorDto> getSectors() {

        return sectorRepository.findAll().stream().map(sector ->
                SectorDto.builder()
                        .id(sector.getId())
                        .name(sector.getName())
                        .industries(sector.getIndustries()).build()).collect(Collectors.toList());
    }
}
