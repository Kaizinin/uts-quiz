package com.example.kuadrat;

import jakarta.validation.constraints.NotNull;

/** Koefisien persamaan ax^2 + bx + c = 0. */
public record KuadratRequest(
        @NotNull Double a,
        @NotNull Double b,
        @NotNull Double c) {
}
