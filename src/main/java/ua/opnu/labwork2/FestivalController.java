package ua.opnu.labwork2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.dto.*;
import java.util.List;

@Tag(name = "Фестивалі", description = "Управління музичними фестивалями")
@RestController
@RequestMapping("/festivals")
public class FestivalController {

    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @Operation(summary = "Створити новий фестиваль")
    @ApiResponse(responseCode = "201", description = "Фестиваль успішно створено")
    @PostMapping
    public ResponseEntity<FestivalResponse> createFestival(@Valid @RequestBody FestivalCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(festivalService.create(request));
    }

    @Operation(summary = "Отримати всі фестивалі")
    @ApiResponse(responseCode = "200", description = "Список фестивалів отримано")
    @GetMapping
    public ResponseEntity<List<FestivalResponse>> getFestivals() {
        return ResponseEntity.ok(festivalService.findAll());
    }

    @Operation(summary = "Отримати фестиваль за ID")
    @GetMapping("/{id}")
    public ResponseEntity<FestivalResponse> getFestival(@PathVariable Long id) {
        return ResponseEntity.ok(festivalService.findById(id));
    }

    @Operation(summary = "Оновити фестиваль")
    @PutMapping("/{id}")
    public ResponseEntity<FestivalResponse> updateFestival(@PathVariable Long id, @Valid @RequestBody FestivalUpdateRequest request) {
        return ResponseEntity.ok(festivalService.update(id, request));
    }

    @Operation(summary = "Видалити фестиваль")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFestival(@PathVariable Long id) {
        festivalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Отримати артистів фестивалю")
    @GetMapping("/{id}/artists")
    public ResponseEntity<List<ArtistResponse>> getFestivalArtists(@PathVariable Long id) {
        return ResponseEntity.ok(festivalService.getFestivalArtists(id));
    }

    @Operation(summary = "Отримати сцени фестивалю")
    @GetMapping("/{id}/stages")
    public ResponseEntity<List<StageResponse>> getFestivalStages(@PathVariable Long id) {
        return ResponseEntity.ok(festivalService.getFestivalStages(id));
    }
}

