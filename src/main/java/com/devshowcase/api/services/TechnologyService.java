package com.devshowcase.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devshowcase.api.models.Technology;
import com.devshowcase.api.repositories.TechnologyRepository;
import com.devshowcase.api.exceptions.ResourceNotFoundException;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }

    public Technology findById(Long id) {
        return technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tecnologia nao encontrada com o ID: " + id));
    }

    public Technology save(Technology technology) {
        return technologyRepository.save(technology);
    }
}