package ua.opnu.labwork2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.dto.*;
import java.util.List;

@Tag(name = "Відвідувачі", description = "Управління відвідувачами фестивалів")
@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Operation(summary = "Зареєструвати нового відвідувача")
    @PostMapping
    public ResponseEntity<VisitorResponse> createVisitor(@Valid @RequestBody VisitorCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitorService.create(request));
    }

    @Operation(summary = "Отримати всіх відвідувачів")
    @GetMapping
    public ResponseEntity<List<VisitorResponse>> getVisitors() {
        return ResponseEntity.ok(visitorService.findAll());
    }

    @Operation(summary = "Отримати відвідувача за ID")
    @GetMapping("/{id}")
    public ResponseEntity<VisitorResponse> getVisitor(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.findById(id));
    }

    @Operation(summary = "Оновити дані відвідувача")
    @PutMapping("/{id}")
    public ResponseEntity<VisitorResponse> updateVisitor(@PathVariable Long id, @Valid @RequestBody VisitorUpdateRequest request) {
        return ResponseEntity.ok(visitorService.update(id, request));
    }

    @Operation(summary = "Видалити відвідувача")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {
        visitorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Отримати фестивалі відвідувача")
    @GetMapping("/{id}/festivals")
    public ResponseEntity<List<FestivalResponse>> getVisitorFestivals(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.getVisitorFestivals(id));
    }
}

