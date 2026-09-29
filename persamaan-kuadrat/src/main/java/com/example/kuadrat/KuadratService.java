package com.example.kuadrat;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class KuadratService {

    public KuadratResponse hitung(KuadratRequest req) {
        double a = req.a();
        double b = req.b();
        double c = req.c();

        if (a == 0) {
            throw new IllegalArgumentException(
                    "Nilai a tidak boleh 0 (bukan persamaan kuadrat).");
        }

        double d = b * b - 4 * a * c;
        String jenis;
        String x1;
        String x2;

        if (d > 0) {
            jenis = "Dua akar real berbeda";
            double akarD = Math.sqrt(d);
            x1 = fmt((-b + akarD) / (2 * a));
            x2 = fmt((-b - akarD) / (2 * a));
        } else if (d == 0) {
            jenis = "Dua akar real kembar";
            x1 = fmt(-b / (2 * a));
            x2 = x1;
        } else {
            jenis = "Akar kompleks (tidak real)";
            double real = -b / (2 * a);
            double imaj = Math.sqrt(-d) / (2 * Math.abs(a));
            x1 = fmt(real) + " + " + fmt(imaj) + "i";
            x2 = fmt(real) + " - " + fmt(imaj) + "i";
        }

        return new KuadratResponse(susun(a, b, c), fmt(d), jenis, x1, x2);
    }

    /** Membulatkan ke 6 desimal dan membuang nol di belakang koma. */
    static String fmt(double v) {
        double bulat = Math.round(v * 1_000_000d) / 1_000_000d;
        if (bulat == 0) {
            return "0";
        }
        return BigDecimal.valueOf(bulat).stripTrailingZeros().toPlainString();
    }

    private static String susun(double a, double b, double c) {
        return fmt(a) + "x² " + tanda(b) + " " + fmt(Math.abs(b)) + "x "
                + tanda(c) + " " + fmt(Math.abs(c)) + " = 0";
    }

    private static String tanda(double v) {
        return v < 0 ? "-" : "+";
    }
}
