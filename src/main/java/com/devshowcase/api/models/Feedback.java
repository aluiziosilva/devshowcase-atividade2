package com.devshowcase.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Feedback() {
    }

    public Feedback(Long id, Integer rating, String comment, Project project) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}