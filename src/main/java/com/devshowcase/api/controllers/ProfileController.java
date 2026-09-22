package com.devshowcase.api.controllers;

import com.devshowcase.api.dtos.CreateProfileDTO;
import com.devshowcase.api.dtos.ProfileResponseDTO;
import com.devshowcase.api.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> create(@Valid @RequestBody CreateProfileDTO dto) {
        ProfileResponseDTO response = profileService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> findById(@PathVariable Long id) {
        ProfileResponseDTO response = profileService.findById(id);
        return ResponseEntity.ok(response);
    }
}