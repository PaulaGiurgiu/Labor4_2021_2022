package com.uni.model;

import com.uni.model.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {
    private final Student student1 = new Student("Alice", "Hart", 1);

    @Test
    void getVorname() {
        assertEquals("Alice", student1.getVorname());
    }

    @Test
    void getNachname() {
        assertEquals("Hart", student1.getNachname());
    }

    @Test
    void getStudentID() {
        assertEquals(1, student1.getStudentID());
    }

    @Test
    void getTotalCredits() {
        assertEquals(0, student1.getTotalCredits());
    }

    @Test
    void getEnrolledCourses() {
        assertEquals(0, student1.getEnrolledCourses().size());
    }

    @Test
    void setVorname() {
        student1.setVorname("Zoe");
        assertEquals("Zoe", student1.getVorname());
    }

    @Test
    void setNachname() {
        student1.setNachname("Miller");
        assertEquals("Miller", student1.getNachname());
    }

    @Test
    void setStudentID() {
        student1.setStudentID(11);
        assertEquals(11, student1.getStudentID());
    }

    @Test
    void setTotalCredits() {
        student1.setTotalCredits(2);
        assertEquals(2, student1.getTotalCredits());
    }

    @Test
    void setEnrolledCourses() {
        List<Long> list = new ArrayList<Long>();
        list.add(list.size(), 12L);
        student1.setEnrolledCourses(list);
        assertEquals(1, student1.getEnrolledCourses().size());
    }
}