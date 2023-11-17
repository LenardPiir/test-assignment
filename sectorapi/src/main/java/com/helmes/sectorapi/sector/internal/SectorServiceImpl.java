package com.helmes.sectorapi.sector.internal;

import com.helmes.sectorapi.sector.api.Sector;
import com.helmes.sectorapi.sector.api.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class SectorServiceImpl implements SectorService {
    private final SectorRepository repository;
    private final SectorMapper mapper;

    @Override
    public List<Sector> getAllByLevel(int level) {
        return repository.findAllByLevel(level)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Sector getSector(String code) {
        return repository.findById(code)
                .map(mapper::toDomain)
                .orElseThrow(() -> new RuntimeException("No sector found!"));
    }
}
