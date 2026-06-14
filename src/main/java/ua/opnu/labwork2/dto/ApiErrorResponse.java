package ua.opnu.labwork2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Структура відповіді при виникненні помилки")
public record ApiErrorResponse(
        @Schema(description = "Час виникнення помилки") LocalDateTime timestamp,
        @Schema(description = "HTTP статус код", example = "400") int status,
        @Schema(description = "Назва помилки", example = "Bad Request") String error,
        @Schema(description = "Детальне повідомлення", example = "Validation failed") String message,
        @Schema(description = "Шлях запиту", example = "/courses") String path,
        @Schema(description = "Мапа помилок валідації полів") Map<String, String> errors
) {}