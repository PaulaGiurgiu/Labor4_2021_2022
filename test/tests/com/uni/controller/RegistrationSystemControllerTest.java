package com.uni.controller;

import com.uni.exception.DeleteVorlesungFromLehrerException;
import com.uni.exception.ExistException;
import com.uni.exception.RegisterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemControllerTest {
    private RegistrationSystemController registrationSystem = new RegistrationSystemController();

    public RegistrationSystemControllerTest(){
        registrationSystem.controller_addStudent("Zoe", "Miller", 1111L);
        registrationSystem.controller_addStudent("Alice", "Hart", 1112L);
        registrationSystem.controller_addStudent("Alice", "Miller", 1113L);

        registrationSystem.controller_addLehrer("Tom", "John", 1L);
        registrationSystem.controller_addLehrer("Jack", "Storm", 2L);

        registrationSystem.controller_addVorlesung("BD", 1L, 100L, 30, 5);
        registrationSystem.controller_addVorlesung("BD2", 2L, 101L, 31, 6);
        registrationSystem.controller_addVorlesung("BD3", 1L, 102L, 32, 7);
    }

    @Test
    void controller_register() {
        try {
            registrationSystem.controller_register(100L, 1111L);
            registrationSystem.controller_register(100L, 1112L);
            registrationSystem.controller_register(101L, 1111L);
            registrationSystem.controller_register(101L, 1112L);
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }

        Assertions.assertEquals(2, registrationSystem.controller_getAllCourses().get(0).getStudentsEnrolled().size());
    }

    @Test
    void controller_unregister() {
        controller_register();
        try {
            registrationSystem.controller_unregister(100L, 1111L);
            registrationSystem.controller_unregister(102L, 1111L);
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(1, registrationSystem.controller_getAllCourses().get(0).getStudentsEnrolled().size());
    }

    @Test
    void controller_getAllCourses() {
        controller_register();
        Assertions.assertEquals(2, registrationSystem.controller_getAllCourses().get(0).getStudentsEnrolled().size());
        Assertions.assertEquals(2, registrationSystem.controller_getAllCourses().get(1).getStudentsEnrolled().size());
        Assertions.assertEquals(0, registrationSystem.controller_getAllCourses().get(2).getStudentsEnrolled().size());
    }

    @Test
    void controller_getAllStudents() {
        controller_register();
        Assertions.assertEquals(3, registrationSystem.controller_getAllStudents().size());
    }

    @Test
    void controller_getAllLehrer() {
        Assertions.assertEquals(2, registrationSystem.controller_getAllLehrer().size());
    }

    @Test
    void controller_retrieveCoursesWithFreePlaces() {
        controller_register();
        HashMap<Integer, Long> map = new HashMap<Integer, Long>();
        map.put(32, 102L);
        map.put(28, 100L);
        map.put(29, 101L);
        Assertions.assertEquals(map.size(), registrationSystem.controller_retrieveCoursesWithFreePlaces().size());
    }

    @Test
    void controller_retrieveStudentsEnrolledForACourse() {
        controller_register();
        try {
            Assertions.assertEquals(2, registrationSystem.controller_retrieveStudentsEnrolledForACourse(100L).size());
            Assertions.assertEquals(2, registrationSystem.controller_retrieveStudentsEnrolledForACourse(101L).size());
            Assertions.assertEquals(0, registrationSystem.controller_retrieveStudentsEnrolledForACourse(102L).size());
        } catch (ExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_changeCreditFromACourse() {
        controller_register();
        try {
            registrationSystem.controller_changeCreditFromACourse(100L, 12);
            registrationSystem.controller_changeCreditFromACourse(101L, 13);
            registrationSystem.controller_changeCreditFromACourse(102L, 14);

            Assertions.assertEquals(12, registrationSystem.controller_getAllCourses().get(0).getCredits());
            Assertions.assertEquals(13, registrationSystem.controller_getAllCourses().get(1).getCredits());
            Assertions.assertEquals(14, registrationSystem.controller_getAllCourses().get(2).getCredits());
        } catch (RegisterException | ExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_deleteVorlesungFromLehrer() {
        controller_register();
        try {
            registrationSystem.controller_deleteVorlesungFromLehrer(1L, 100L);
            Assertions.assertEquals(2, registrationSystem.controller_getAllCourses().size());
        } catch (DeleteVorlesungFromLehrerException | RegisterException e) {
            System.out.println(e.getMessage());
        }
    }
}