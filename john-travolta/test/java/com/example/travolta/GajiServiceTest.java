package com.example.travolta;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GajiServiceTest {

    private final GajiService service = new GajiService();

    private static BigDecimal bd(String v) {
        return new BigDecimal(v);
    }

    @Test
    void kerja52Jam_bisaMenabung() {
        GajiResponse r = service.hitung(new GajiRequest(bd("52"), bd("15000"), bd("600000")));
        assertEquals(0, bd("870000").compareTo(r.totalGaji()));
        assertEquals(0, bd("270000").compareTo(r.tabungan()));
        assertEquals("bisa menabung", r.status());
    }

    @Test
    void pemasukanSamaDenganPengeluaran_tidakBisaMenabung() {
        GajiResponse r = service.hitung(new GajiRequest(bd("40"), bd("15000"), bd("600000")));
        assertEquals("tidak bisa menabung", r.status());
    }

    @Test
    void pemasukanKurang_cariTambahan() {
        GajiResponse r = service.hitung(new GajiRequest(bd("30"), bd("15000"), bd("600000")));
        assertEquals("cari tambahan", r.status());
    }
}
