package com.uni.model;

import com.uni.model.Lehrer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LehrerTest {
    private final Lehrer lehrer1 = new Lehrer("Alice", "Hart", 1);

    @Test
    void getVorname() {
        assertEquals("Alice", lehrer1.getVorname());
    }

    @Test
    void getNachname() {
        assertEquals("Hart", lehrer1.getNachname());
    }

    @Test
    void getLehrerID() {
        assertEquals(1, lehrer1.getLehrerID());
    }

    @Test
    void getVorlesungen() {
        assertEquals(0, lehrer1.getVorlesungen().size());
    }

    @Test
    void setVorname() {
        lehrer1.setVorname("Zoe");
        assertEquals("Zoe", lehrer1.getVorname());
    }

    @Test
    void setNachname() {
        lehrer1.setNachname("Miller");
        assertEquals("Miller", lehrer1.getNachname());
    }

    @Test
    void setLehrerID() {
        lehrer1.setLehrerID(12);
        assertEquals(12, lehrer1.getLehrerID());
    }

    @Test
    void setVorlesungen() {
        List<Long> list = new ArrayList<Long>();
        list.add(list.size(), 12L);
        list.add(list.size(), 14L);
        lehrer1.setVorlesungen(list);
        assertEquals(2, lehrer1.getVorlesungen().size());
    }
}