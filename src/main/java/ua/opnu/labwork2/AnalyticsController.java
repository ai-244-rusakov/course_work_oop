package ua.opnu.labwork2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Аналітика", description = "Аналітичні запити по фестивалям, артистам та виступам")
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final FestivalRepository festivalRepository;
    private final ArtistRepository artistRepository;
    private final VisitorRepository visitorRepository;
    private final PerformanceRepository performanceRepository;

    public AnalyticsController(FestivalRepository festivalRepository, ArtistRepository artistRepository,
                               VisitorRepository visitorRepository, PerformanceRepository performanceRepository) {
        this.festivalRepository = festivalRepository;
        this.artistRepository = artistRepository;
        this.visitorRepository = visitorRepository;
        this.performanceRepository = performanceRepository;
    }

    @Operation(summary = "Загальна кількість фестивалів")
    @GetMapping("/festivals/count")
    public ResponseEntity<Long> getFestivalsCount() {
        return ResponseEntity.ok(festivalRepository.count());
    }

    @Operation(summary = "Кількість артистів")
    @GetMapping("/artists/count")
    public ResponseEntity<Long> getArtistsCount() {
        return ResponseEntity.ok(artistRepository.count());
    }

    @Operation(summary = "Кількість відвідувачів")
    @GetMapping("/visitors/count")
    public ResponseEntity<Long> getVisitorsCount() {
        return ResponseEntity.ok(visitorRepository.count());
    }

    @Operation(summary = "Кількість виступів за сценами")
    @GetMapping("/performances/by-stage")
    public ResponseEntity<java.util.List<Object[]>> getPerformancesByStage() {
        return ResponseEntity.ok(performanceRepository.countPerformancesByStage());
    }
}