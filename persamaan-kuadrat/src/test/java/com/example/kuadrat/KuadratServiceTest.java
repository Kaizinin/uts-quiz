package com.example.kuadrat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KuadratServiceTest {

    private final KuadratService service = new KuadratService();

    @Test
    void duaAkarRealBerbeda() {
        KuadratResponse r = service.hitung(new KuadratRequest(1.0, -5.0, 6.0));
        assertEquals("1", r.diskriminan());
        assertEquals("3", r.x1());
        assertEquals("2", r.x2());
    }

    @Test
    void akarKembar() {
        KuadratResponse r = service.hitung(new KuadratRequest(1.0, -2.0, 1.0));
        assertEquals("0", r.diskriminan());
        assertEquals("1", r.x1());
        assertEquals("1", r.x2());
    }

    @Test
    void akarKompleks() {
        KuadratResponse r = service.hitung(new KuadratRequest(1.0, 2.0, 5.0));
        assertEquals("-16", r.diskriminan());
        assertEquals("-1 + 2i", r.x1());
        assertEquals("-1 - 2i", r.x2());
    }

    @Test
    void aNolDitolak() {
        assertThrows(IllegalArgumentException.class,
                () -> service.hitung(new KuadratRequest(0.0, 2.0, 1.0)));
    }
}
