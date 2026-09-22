package com.devshowcase.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateTechnologyDTO(
    @NotBlank(message = "O nome da tecnologia é obrigatório")
    String name
) {}