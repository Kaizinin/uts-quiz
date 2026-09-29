package com.example.kuadrat;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/kuadrat")
public class KuadratController {

    private final KuadratService kuadratService;

    public KuadratController(KuadratService kuadratService) {
        this.kuadratService = kuadratService;
    }

    @PostMapping
    public KuadratResponse hitung(@Valid @RequestBody KuadratRequest request) {
        return kuadratService.hitung(request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> inputTidakValid(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
    }
}
