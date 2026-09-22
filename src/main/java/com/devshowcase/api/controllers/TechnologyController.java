package com.devshowcase.api.controllers;

import com.devshowcase.api.dtos.CreateTechnologyDTO;
import com.devshowcase.api.dtos.TechnologyResponseDTO;
import com.devshowcase.api.models.Technology;
import com.devshowcase.api.repositories.TechnologyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyRepository technologyRepository;

    @PostMapping
    public ResponseEntity<TechnologyResponseDTO> create(@RequestBody @Valid CreateTechnologyDTO dto) {
        Technology tech = new Technology();
        tech.setName(dto.name());

        Technology savedTech = technologyRepository.save(tech);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TechnologyResponseDTO(savedTech));
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponseDTO>> findAll() {
        List<TechnologyResponseDTO> responseList = technologyRepository.findAll()
                .stream()
                .map(TechnologyResponseDTO::new)
                .toList();

        return ResponseEntity.ok(responseList);
    }
}