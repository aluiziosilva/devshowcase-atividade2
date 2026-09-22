package com.devshowcase.api.services;

import com.devshowcase.api.dtos.CreateProjectDTO;
import com.devshowcase.api.dtos.FeedbackRequestDTO;
import com.devshowcase.api.dtos.FeedbackResponseDTO;
import com.devshowcase.api.dtos.ProjectResponseDTO;
import com.devshowcase.api.exceptions.ResourceNotFoundException;
import com.devshowcase.api.models.Feedback;
import com.devshowcase.api.models.Profile;
import com.devshowcase.api.models.Project;
import com.devshowcase.api.models.Technology;
import com.devshowcase.api.repositories.FeedbackRepository;
import com.devshowcase.api.repositories.ProfileRepository;
import com.devshowcase.api.repositories.ProjectRepository;
import com.devshowcase.api.repositories.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Transactional
    public ProjectResponseDTO create(CreateProjectDTO dto) {
        Profile profile = profileRepository.findById(dto.profileId())
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com ID: " + dto.profileId()));

        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setUrl(dto.url());
        project.setProfile(profile);

        if (dto.technologyIds() != null && !dto.technologyIds().isEmpty()) {
            Set<Technology> technologies = new HashSet<>(technologyRepository.findAllById(dto.technologyIds()));
            project.setTechnologies(technologies);
        }

        project = projectRepository.save(project);
        return new ProjectResponseDTO(project);
    }

    @Transactional(readOnly = true)
    public Page<ProjectResponseDTO> findAll(Long technologyId, Pageable pageable) {
        Page<Project> result;
        if (technologyId != null) {
            result = projectRepository.findByTechnologiesId(technologyId, pageable);
        } else {
            result = projectRepository.findAll(pageable);
        }
        return result.map(ProjectResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public ProjectResponseDTO findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o ID: " + id));
        return new ProjectResponseDTO(project);
    }

    @Transactional
    public FeedbackResponseDTO addFeedback(Long projectId, FeedbackRequestDTO dto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o ID: " + projectId));

        Feedback feedback = new Feedback();
        feedback.setRating(dto.getRating());
        feedback.setComment(dto.getComment());
        feedback.setProject(project);

        feedback = feedbackRepository.save(feedback);

        project.getFeedbacks().add(feedback);

        double average = project.getFeedbacks().stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        project.setAverageRating(Math.round(average * 100.0) / 100.0);
        projectRepository.save(project);

        return new FeedbackResponseDTO(feedback);
    }

    @Transactional
    public ProjectResponseDTO upvote(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o ID: " + projectId));

        int currentLikes = project.getLikes() != null ? project.getLikes() : 0;
        project.setLikes(currentLikes + 1);

        project = projectRepository.save(project);
        return new ProjectResponseDTO(project);
    }
}