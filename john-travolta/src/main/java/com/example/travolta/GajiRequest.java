package com.example.travolta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

/** Input bebas: jam kerja, rate per jam, dan pengeluaran seminggu. */
public record GajiRequest(
        @NotNull @PositiveOrZero BigDecimal jamKerja,
        @NotNull @Positive BigDecimal rate,
        @NotNull @PositiveOrZero BigDecimal pengeluaran) {
}
