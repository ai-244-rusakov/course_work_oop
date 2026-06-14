package ua.opnu.labwork2.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Дані для створення нового фестивалю")
public record FestivalCreateRequest(
        @Schema(description = "Назва фестивалю", example = "Atlas Music Festival", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Size(min = 3, max = 150) String name,

        @Schema(description = "Місто проведення", example = "Kyiv", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Size(min = 2, max = 100) String city,

        @Schema(description = "Дата початку", example = "2026-07-10", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull @FutureOrPresent LocalDate startDate,

        @Schema(description = "Дата завершення", example = "2026-07-15", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull @FutureOrPresent LocalDate endDate
) {}

