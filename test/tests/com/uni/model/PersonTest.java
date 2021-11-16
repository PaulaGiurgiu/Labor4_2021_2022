package com.uni.model;

import com.uni.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    private final Person person1 = new Person("Alice", "Hart");

    @Test
    void getVorname() {
        assertEquals("Alice", person1.getVorname());
    }

    @Test
    void getNachname() {
        assertEquals("Hart", person1.getNachname());
    }

    @Test
    void setVorname() {
        person1.setVorname("Zoe");
        assertEquals("Zoe", person1.getVorname());
    }

    @Test
    void setNachname() {
        person1.setNachname("Miller");
        assertEquals("Miller", person1.getNachname());
    }
}