package com.devshowcase.api.controllers;

import com.devshowcase.api.dtos.CreateProjectDTO;
import com.devshowcase.api.dtos.FeedbackRequestDTO;
import com.devshowcase.api.dtos.FeedbackResponseDTO;
import com.devshowcase.api.dtos.ProjectResponseDTO;
import com.devshowcase.api.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@Valid @RequestBody CreateProjectDTO dto) {
        ProjectResponseDTO response = projectService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> findAll(
            @RequestParam(value = "technologyId", required = false) Long technologyId,
            Pageable pageable) {
        Page<ProjectResponseDTO> list = projectService.findAll(technologyId, pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> findById(@PathVariable Long id) {
        ProjectResponseDTO response = projectService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<FeedbackResponseDTO> addFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequestDTO dto) {
        FeedbackResponseDTO response = projectService.addFeedback(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<ProjectResponseDTO> upvote(@PathVariable Long id) {
        ProjectResponseDTO response = projectService.upvote(id);
        return ResponseEntity.ok(response);
    }
}