package com.devshowcase.api.dtos;

import com.devshowcase.api.models.Feedback;

public class FeedbackResponseDTO {

    private Long id;
    private Integer rating;
    private String comment;

    public FeedbackResponseDTO() {
    }

    public FeedbackResponseDTO(Feedback entity) {
        this.id = entity.getId();
        this.rating = entity.getRating();
        this.comment = entity.getComment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}