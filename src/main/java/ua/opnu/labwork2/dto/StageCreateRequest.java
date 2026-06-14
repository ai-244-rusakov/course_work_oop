package ua.opnu.labwork2.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Дані для створення нової сцени")
public record StageCreateRequest(
        @Schema(description = "Назва сцени", example = "Main Stage", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Size(min = 2, max = 150) String name,

        @Schema(description = "Місткість (макс глядачів)", example = "5000", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull @Positive Integer capacity,

        @Schema(description = "ID фестивалю до якого належить сцена", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull @Positive Long festivalId
) {}

