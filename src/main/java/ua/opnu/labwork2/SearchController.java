package ua.opnu.labwork2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.dto.*;
import java.util.List;

@Tag(name = "Пошук", description = "Повнотекстовий пошук по фестивалям, артистам та відвідувачам")
@RestController
@RequestMapping("/search")
public class SearchController {

    private final FestivalService festivalService;
    private final ArtistService artistService;
    private final VisitorService visitorService;

    public SearchController(FestivalService festivalService, ArtistService artistService, VisitorService visitorService) {
        this.festivalService = festivalService;
        this.artistService = artistService;
        this.visitorService = visitorService;
    }

    @Operation(summary = "Пошук фестивалів за назвою або містом")
    @GetMapping("/festivals")
    public ResponseEntity<List<FestivalResponse>> searchFestivals(@RequestParam String query) {
        return ResponseEntity.ok(festivalService.search(query));
    }

    @Operation(summary = "Пошук артистів за назвою")
    @GetMapping("/artists")
    public ResponseEntity<List<ArtistResponse>> searchArtists(@RequestParam String query) {
        return ResponseEntity.ok(artistService.search(query));
    }

    @Operation(summary = "Пошук відвідувачів за ім'ям або прізвищем")
    @GetMapping("/visitors")
    public ResponseEntity<List<VisitorResponse>> searchVisitors(@RequestParam String query) {
        return ResponseEntity.ok(visitorService.search(query));
    }
}