package com.example.travolta;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GajiService {

    private static final BigDecimal BATAS_JAM_NORMAL = BigDecimal.valueOf(40);
    private static final BigDecimal FAKTOR_LEMBUR = new BigDecimal("1.5");

    public GajiResponse hitung(GajiRequest req) {
        // 1) Hitung gaji
        BigDecimal jamNormal = req.jamKerja().min(BATAS_JAM_NORMAL);
        BigDecimal jamLembur = req.jamKerja().subtract(BATAS_JAM_NORMAL).max(BigDecimal.ZERO);

        BigDecimal rateLembur = req.rate().multiply(FAKTOR_LEMBUR);
        BigDecimal gajiNormal = req.rate().multiply(jamNormal);
        BigDecimal gajiLembur = rateLembur.multiply(jamLembur);
        BigDecimal totalGaji = gajiNormal.add(gajiLembur);

        // 2) Bandingkan pemasukan dengan pengeluaran
        int banding = totalGaji.compareTo(req.pengeluaran());
        String status;
        BigDecimal tabungan = BigDecimal.ZERO;

        if (banding > 0) {
            status = "bisa menabung";
            tabungan = totalGaji.subtract(req.pengeluaran());
        } else if (banding == 0) {
            status = "tidak bisa menabung";
        } else {
            status = "cari tambahan";
        }

        return new GajiResponse(jamNormal, jamLembur, gajiNormal, gajiLembur,
                totalGaji, req.pengeluaran(), status, tabungan);
    }
}
