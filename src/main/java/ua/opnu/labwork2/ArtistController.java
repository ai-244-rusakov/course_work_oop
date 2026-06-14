package ua.opnu.labwork2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.dto.*;
import java.util.List;

@Tag(name = "Артисти", description = "Управління музичними артистами")
@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Operation(summary = "Створити нового артиста")
    @PostMapping
    public ResponseEntity<ArtistResponse> createArtist(@Valid @RequestBody ArtistCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.create(request));
    }

    @Operation(summary = "Отримати всіх артистів")
    @GetMapping
    public ResponseEntity<List<ArtistResponse>> getArtists() {
        return ResponseEntity.ok(artistService.findAll());
    }

    @Operation(summary = "Отримати артиста за ID")
    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponse> getArtist(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.findById(id));
    }

    @Operation(summary = "Оновити артиста")
    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponse> updateArtist(@PathVariable Long id, @Valid @RequestBody ArtistUpdateRequest request) {
        return ResponseEntity.ok(artistService.update(id, request));
    }

    @Operation(summary = "Видалити артиста")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Отримати виступи артиста")
    @GetMapping("/{id}/performances")
    public ResponseEntity<List<PerformanceResponse>> getArtistPerformances(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistPerformances(id));
    }
}

