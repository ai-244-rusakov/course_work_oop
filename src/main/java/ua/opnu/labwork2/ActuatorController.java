package ua.opnu.labwork2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator")
public class ActuatorController {

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("GET /actuator/health OK");
    }

    @GetMapping("/metrics")
    public ResponseEntity<String> metrics() {
        return ResponseEntity.ok("GET /actuator/metrics OK");
    }

    @GetMapping("/prometheus")
    public ResponseEntity<String> prometheus() {
        return ResponseEntity.ok("GET /actuator/prometheus OK");
    }
}