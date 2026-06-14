package ua.opnu.labwork2.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Дані для створення нового відвідувача")
public record VisitorCreateRequest(
        @Schema(description = "Ім'я", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Size(min = 2, max = 100) String firstName,

        @Schema(description = "Прізвище", example = "Doe", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Size(min = 2, max = 100) String lastName,

        @Schema(description = "Email", example = "john@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Email String email,

        @Schema(description = "Дата реєстрації", example = "2026-05-28")
        @NotNull @PastOrPresent LocalDate registrationDate
) {}

