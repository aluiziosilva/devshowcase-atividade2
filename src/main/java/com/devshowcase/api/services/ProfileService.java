package com.devshowcase.api.services;

import com.devshowcase.api.dtos.CreateProfileDTO;
import com.devshowcase.api.dtos.ProfileResponseDTO;
import com.devshowcase.api.exceptions.ResourceNotFoundException;
import com.devshowcase.api.models.Profile;
import com.devshowcase.api.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public ProfileResponseDTO create(CreateProfileDTO dto) {
        Profile profile = new Profile();
        profile.setName(dto.name());
        profile.setBio(dto.bio());
        profile = profileRepository.save(profile);
        return new ProfileResponseDTO(profile);
    }

    @Transactional(readOnly = true)
    public ProfileResponseDTO findById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com o ID: " + id));
        return new ProfileResponseDTO(profile);
    }

    @Transactional(readOnly = true)
    public List<ProfileResponseDTO> findAll() {
        return profileRepository.findAll().stream().map(ProfileResponseDTO::new).toList();
    }
}