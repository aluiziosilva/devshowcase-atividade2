package com.devshowcase.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateProfileDTO(
    @NotBlank(message = "O nome é obrigatório")
    String name,

    @NotBlank(message = "A bio é obrigatória")
    String bio
) {}