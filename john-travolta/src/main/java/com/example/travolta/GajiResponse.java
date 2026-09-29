package com.example.travolta;

import java.math.BigDecimal;

public record GajiResponse(
        BigDecimal jamNormal,
        BigDecimal jamLembur,
        BigDecimal gajiNormal,
        BigDecimal gajiLembur,
        BigDecimal totalGaji,
        BigDecimal pengeluaran,
        String status,
        BigDecimal tabungan) {
}
