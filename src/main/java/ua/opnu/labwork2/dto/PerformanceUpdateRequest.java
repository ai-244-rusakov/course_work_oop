package ua.opnu.labwork2.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Дані для оновлення виступу")
public record PerformanceUpdateRequest(
        @Schema(description = "Час виступу", example = "2026-07-10T19:30:00")
        @NotNull @FutureOrPresent LocalDateTime performanceTime,

        @Schema(description = "Тривалість у хвилинах", example = "60")
        @NotNull @Positive Integer durationMinutes,

        @Schema(description = "ID артиста", example = "1")
        @NotNull @Positive Long artistId,

        @Schema(description = "ID сцени", example = "1")
        @NotNull @Positive Long stageId,

        @Schema(description = "ID відвідувача", example = "1")
        Long visitorId
) {}

