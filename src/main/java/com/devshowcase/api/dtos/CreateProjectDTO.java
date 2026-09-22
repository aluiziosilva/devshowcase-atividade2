package com.devshowcase.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import java.util.Set;

public record CreateProjectDTO(
    @NotBlank(message = "O título é obrigatório")
    String title,

    @NotBlank(message = "A descrição é obrigatória")
    String description,

    @NotBlank(message = "A URL é obrigatória")
    @URL(message = "Informe uma URL válida")
    String url,

    @NotNull(message = "O ID do perfil é obrigatório")
    Long profileId,

    @NotEmpty(message = "Selecione ao menos uma tecnologia")
    Set<Long> technologyIds
) {}