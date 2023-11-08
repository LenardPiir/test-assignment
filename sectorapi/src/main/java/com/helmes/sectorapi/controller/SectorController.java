package com.helmes.sectorapi.controller;

import com.helmes.sectorapi.dto.SectorDto;
import com.helmes.sectorapi.service.SectorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @GetMapping("/sectors")
    public @ResponseBody List<SectorDto> getSectors() {
        return sectorService.getSectors();
    }
}
