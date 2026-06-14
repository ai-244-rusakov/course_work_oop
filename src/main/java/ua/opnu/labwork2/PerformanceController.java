package ua.opnu.labwork2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.dto.*;
import java.util.List;

@Tag(name = "Виступи", description = "Управління виступами на фестивалях")
@RestController
@RequestMapping("/performances")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @Operation(summary = "Створити новий виступ")
    @PostMapping
    public ResponseEntity<PerformanceResponse> createPerformance(@Valid @RequestBody PerformanceCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(performanceService.create(request));
    }

    @Operation(summary = "Отримати всі виступи")
    @GetMapping
    public ResponseEntity<List<PerformanceResponse>> getPerformances() {
        return ResponseEntity.ok(performanceService.findAll());
    }

    @Operation(summary = "Отримати виступ за ID")
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResponse> getPerformance(@PathVariable Long id) {
        return ResponseEntity.ok(performanceService.findById(id));
    }

    @Operation(summary = "Оновити виступ")
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceResponse> updatePerformance(@PathVariable Long id, @Valid @RequestBody PerformanceUpdateRequest request) {
        return ResponseEntity.ok(performanceService.update(id, request));
    }

    @Operation(summary = "Видалити виступ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformance(@PathVariable Long id) {
        performanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

