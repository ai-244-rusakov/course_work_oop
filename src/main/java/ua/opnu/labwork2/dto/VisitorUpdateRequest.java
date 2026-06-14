package ua.opnu.labwork2.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Дані для оновлення відвідувача")
public record VisitorUpdateRequest(
        @Schema(description = "Ім'я", example = "John")
        @NotBlank @Size(min = 2, max = 100) String firstName,

        @Schema(description = "Прізвище", example = "Doe")
        @NotBlank @Size(min = 2, max = 100) String lastName,

        @Schema(description = "Email", example = "john@example.com")
        @NotBlank @Email String email,

        @Schema(description = "Дата реєстрації", example = "2026-05-28")
        @NotNull @PastOrPresent LocalDate registrationDate
) {}

