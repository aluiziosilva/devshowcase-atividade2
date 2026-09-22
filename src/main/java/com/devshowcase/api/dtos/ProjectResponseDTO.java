package com.devshowcase.api.dtos;

import com.devshowcase.api.models.Project;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String url;
    private Integer likes;
    private Double averageRating;
    private ProfileResponseDTO profile;
    private Set<TechnologyResponseDTO> technologies;
    private List<FeedbackResponseDTO> feedbacks;

    public ProjectResponseDTO() {
    }

    public ProjectResponseDTO(Project entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.url = entity.getUrl();
        this.likes = entity.getLikes();
        this.averageRating = entity.getAverageRating();
        this.profile = entity.getProfile() != null ? new ProfileResponseDTO(entity.getProfile()) : null;
        this.technologies = entity.getTechnologies() != null 
                ? entity.getTechnologies().stream().map(TechnologyResponseDTO::new).collect(Collectors.toSet())
                : null;
        this.feedbacks = entity.getFeedbacks() != null
                ? entity.getFeedbacks().stream().map(FeedbackResponseDTO::new).collect(Collectors.toList())
                : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public ProfileResponseDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileResponseDTO profile) {
        this.profile = profile;
    }

    public Set<TechnologyResponseDTO> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<TechnologyResponseDTO> technologies) {
        this.technologies = technologies;
    }

    public List<FeedbackResponseDTO> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackResponseDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }
}