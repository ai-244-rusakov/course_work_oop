package ua.opnu.labwork2.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Дані для оновлення сцени")
public record StageUpdateRequest(
        @Schema(description = "Назва сцени", example = "Main Stage")
        @NotBlank @Size(min = 2, max = 150) String name,

        @Schema(description = "Місткість (макс глядачів)", example = "5000")
        @NotNull @Positive Integer capacity,

        @Schema(description = "ID фестивалю до якого належить сцена", example = "1")
        @NotNull @Positive Long festivalId
) {}

