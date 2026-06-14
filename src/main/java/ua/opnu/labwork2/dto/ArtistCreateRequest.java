package ua.opnu.labwork2.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Дані для створення нового виконавця")
public record ArtistCreateRequest(
        @Schema(description = "Назва артиста/гурту", example = "The Beatles", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Size(min = 2, max = 150) String name,

        @Schema(description = "Музичний жанр", example = "Rock", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Size(min = 2, max = 100) String genre,

        @Schema(description = "Країна походження", example = "UK", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank @Size(min = 2, max = 100) String country
) {}

