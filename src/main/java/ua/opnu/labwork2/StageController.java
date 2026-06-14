package ua.opnu.labwork2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.dto.*;
import java.util.List;

@Tag(name = "Сцени", description = "Управління сценами на фестивалях")
@RestController
@RequestMapping("/stages")
public class StageController {

    private final StageService stageService;

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @Operation(summary = "Створити нову сцену")
    @PostMapping
    public ResponseEntity<StageResponse> createStage(@Valid @RequestBody StageCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stageService.create(request));
    }

    @Operation(summary = "Отримати всі сцени")
    @GetMapping
    public ResponseEntity<List<StageResponse>> getStages() {
        return ResponseEntity.ok(stageService.findAll());
    }

    @Operation(summary = "Отримати сцену за ID")
    @GetMapping("/{id}")
    public ResponseEntity<StageResponse> getStage(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.findById(id));
    }

    @Operation(summary = "Оновити сцену")
    @PutMapping("/{id}")
    public ResponseEntity<StageResponse> updateStage(@PathVariable Long id, @Valid @RequestBody StageUpdateRequest request) {
        return ResponseEntity.ok(stageService.update(id, request));
    }

    @Operation(summary = "Видалити сцену")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Отримати виступи сцени")
    @GetMapping("/{id}/performances")
    public ResponseEntity<List<PerformanceResponse>> getStagePerformances(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.getStagePerformances(id));
    }

    @Operation(summary = "Отримати фестиваль сцени")
    @GetMapping("/{id}/festival")
    public ResponseEntity<FestivalResponse> getStageFestival(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.getStageFestival(id));
    }
}

