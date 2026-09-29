package com.example.travolta;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gaji")
public class GajiController {

    private final GajiService gajiService;

    public GajiController(GajiService gajiService) {
        this.gajiService = gajiService;
    }

    @PostMapping
    public GajiResponse hitung(@Valid @RequestBody GajiRequest request) {
        return gajiService.hitung(request);
    }
}
