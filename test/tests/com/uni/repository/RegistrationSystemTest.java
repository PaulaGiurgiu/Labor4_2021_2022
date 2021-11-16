package com.uni.repository;

import com.uni.exception.DeleteVorlesungFromLehrerException;
import com.uni.exception.ExistException;
import com.uni.exception.RegisterException;
import com.uni.model.Lehrer;
import com.uni.model.Student;
import com.uni.model.Vorlesung;
import com.uni.repository.RegistrationSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class RegistrationSystemTest {
    private RegistrationSystem registrationSystem = new RegistrationSystem();

    private final Student student1 = new Student("Zoe", "Miller", 1111);
    private final Student student2 = new Student("Alice", "Hart", 1112);
    private final Student student3 = new Student("Alice", "Miller", 1113);

    private final Lehrer lehrer1 = new Lehrer("Tom", "John", 1);
    private final Lehrer lehrer2 = new Lehrer("Jack", "Storm", 2);

    private final Vorlesung vorlesung1 = new Vorlesung("BD", lehrer1.getLehrerID(), 100, 30, 5);
    private final Vorlesung vorlesung2 = new Vorlesung("BD2", lehrer2.getLehrerID(), 101, 31, 6);
    private final Vorlesung vorlesung3 = new Vorlesung("BD3", lehrer1.getLehrerID(), 102, 32, 7);

    public RegistrationSystemTest(){

        registrationSystem.getStudentRepository().save(student1);
        registrationSystem.getStudentRepository().save(student2);

        registrationSystem.getLehrerRepository().save(lehrer1);
        registrationSystem.getLehrerRepository().save(lehrer2);

        registrationSystem.getVorlesungRepository().save(vorlesung1);
        registrationSystem.getVorlesungRepository().save(vorlesung2);
        registrationSystem.getVorlesungRepository().save(vorlesung3);
    }

    @Test
    void register() {
        try {
            Assertions.assertTrue(registrationSystem.register(vorlesung1, student1));
            Assertions.assertTrue(registrationSystem.register(vorlesung1, student2));
            Assertions.assertTrue(registrationSystem.register(vorlesung2, student1));
            Assertions.assertTrue(registrationSystem.register(vorlesung2, student2));
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }


        List<Long> list = new ArrayList<>();
        Assertions.assertEquals(100, registrationSystem.getVorlesungRepository().findOne(0).getVorlesungID());
        list.add(1111L);
        list.add(1112L);
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(0).getStudentsEnrolled());


        list.clear();
        Assertions.assertEquals(101, registrationSystem.getVorlesungRepository().findOne(1).getVorlesungID());
        list.add(1111L);
        list.add(1112L);
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(1).getStudentsEnrolled());

        list.clear();
        Assertions.assertEquals(102, registrationSystem.getVorlesungRepository().findOne(2).getVorlesungID());
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(2).getStudentsEnrolled());

        Assertions.assertEquals(1111, registrationSystem.getStudentRepository().findOne(0).getStudentID());
        list.add(100L);
        list.add(101L);
        Assertions.assertEquals(list, registrationSystem.getStudentRepository().findOne(0).getEnrolledCourses());
        Assertions.assertEquals(11, registrationSystem.getStudentRepository().findOne(0).getTotalCredits());

        Assertions.assertEquals(1112, registrationSystem.getStudentRepository().findOne(1).getStudentID());
        Assertions.assertEquals(list, registrationSystem.getStudentRepository().findOne(1).getEnrolledCourses());
        Assertions.assertEquals(11, registrationSystem.getStudentRepository().findOne(1).getTotalCredits());

        list.clear();
        Assertions.assertEquals(1, registrationSystem.getLehrerRepository().findOne(0).getLehrerID());
        list.add(vorlesung1.getVorlesungID());
        list.add(vorlesung3.getVorlesungID());
        Assertions.assertEquals(list, registrationSystem.getLehrerRepository().findOne(0).getVorlesungen());

        list.clear();
        Assertions.assertEquals(2, registrationSystem.getLehrerRepository().findOne(1).getLehrerID());
        list.add(vorlesung2.getVorlesungID());
        Assertions.assertEquals(list, registrationSystem.getLehrerRepository().findOne(1).getVorlesungen());

    }

    @Test
    void testRegister() {
        try {
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student2.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student2.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung3.getVorlesungID(), student3.getStudentID()));
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }

        List<Long> list = new ArrayList<>();
        Assertions.assertEquals(100, registrationSystem.getVorlesungRepository().findOne(0).getVorlesungID());
        list.add(1111L);
        list.add(1112L);
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(0).getStudentsEnrolled());


        list.clear();
        Assertions.assertEquals(101, registrationSystem.getVorlesungRepository().findOne(1).getVorlesungID());
        list.add(1111L);
        list.add(1112L);
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(1).getStudentsEnrolled());

        list.clear();
        Assertions.assertEquals(102, registrationSystem.getVorlesungRepository().findOne(2).getVorlesungID());
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(2).getStudentsEnrolled());

        Assertions.assertEquals(1111, registrationSystem.getStudentRepository().findOne(0).getStudentID());
        list.add(100L);
        list.add(101L);
        Assertions.assertEquals(list, registrationSystem.getStudentRepository().findOne(0).getEnrolledCourses());
        Assertions.assertEquals(11, registrationSystem.getStudentRepository().findOne(0).getTotalCredits());

        Assertions.assertEquals(1112, registrationSystem.getStudentRepository().findOne(1).getStudentID());
        Assertions.assertEquals(list, registrationSystem.getStudentRepository().findOne(1).getEnrolledCourses());
        Assertions.assertEquals(11, registrationSystem.getStudentRepository().findOne(1).getTotalCredits());

        list.clear();
        Assertions.assertEquals(1, registrationSystem.getLehrerRepository().findOne(0).getLehrerID());
        list.add(vorlesung1.getVorlesungID());
        list.add(vorlesung3.getVorlesungID());
        Assertions.assertEquals(list, registrationSystem.getLehrerRepository().findOne(0).getVorlesungen());

        list.clear();
        Assertions.assertEquals(2, registrationSystem.getLehrerRepository().findOne(1).getLehrerID());
        list.add(vorlesung2.getVorlesungID());
        Assertions.assertEquals(list, registrationSystem.getLehrerRepository().findOne(1).getVorlesungen());
    }

    @Test
    void unregister() {
        register();

        try {
            registrationSystem.unregister(vorlesung1.getVorlesungID(), student1.getStudentID());
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }

        List<Long> list = new ArrayList<>();
        Assertions.assertEquals(100, registrationSystem.getVorlesungRepository().findOne(0).getVorlesungID());
        list.add(1112L);
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(0).getStudentsEnrolled());

        list.clear();
        Assertions.assertEquals(101, registrationSystem.getVorlesungRepository().findOne(1).getVorlesungID());
        list.add(1111L);
        list.add(1112L);
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(1).getStudentsEnrolled());

        list.clear();
        Assertions.assertEquals(1111, registrationSystem.getStudentRepository().findOne(0).getStudentID());
        list.add(101L);
        Assertions.assertEquals(list, registrationSystem.getStudentRepository().findOne(0).getEnrolledCourses());
        Assertions.assertEquals(6, registrationSystem.getStudentRepository().findOne(0).getTotalCredits());

        list.clear();
        Assertions.assertEquals(1112, registrationSystem.getStudentRepository().findOne(1).getStudentID());
        list.add(100L);
        list.add(101L);
        Assertions.assertEquals(list, registrationSystem.getStudentRepository().findOne(1).getEnrolledCourses());
        Assertions.assertEquals(11, registrationSystem.getStudentRepository().findOne(1).getTotalCredits());


        try {
            registrationSystem.unregister(vorlesung2.getVorlesungID(), student2.getStudentID());
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }

        list.clear();
        Assertions.assertEquals(100, registrationSystem.getVorlesungRepository().findOne(0).getVorlesungID());
        list.add(1112L);
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(0).getStudentsEnrolled());

        list.clear();
        Assertions.assertEquals(101, registrationSystem.getVorlesungRepository().findOne(1).getVorlesungID());
        list.add(1111L);
        Assertions.assertEquals(list, registrationSystem.getVorlesungRepository().findOne(1).getStudentsEnrolled());

        list.clear();
        Assertions.assertEquals(1111, registrationSystem.getStudentRepository().findOne(0).getStudentID());
        list.add(101L);
        Assertions.assertEquals(list, registrationSystem.getStudentRepository().findOne(0).getEnrolledCourses());
        Assertions.assertEquals(6, registrationSystem.getStudentRepository().findOne(0).getTotalCredits());

        list.clear();
        Assertions.assertEquals(1112, registrationSystem.getStudentRepository().findOne(1).getStudentID());
        list.add(100L);
        Assertions.assertEquals(list, registrationSystem.getStudentRepository().findOne(1).getEnrolledCourses());
        Assertions.assertEquals(5, registrationSystem.getStudentRepository().findOne(1).getTotalCredits());

    }

    @Test
    void retrieveCoursesWithFreePlaces(){
        try {
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student2.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student2.getStudentID()));
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }

        HashMap<Integer, Vorlesung> map = new HashMap<Integer, Vorlesung>();
        map.put(32, vorlesung3);
        map.put(28, vorlesung1);
        map.put(29, vorlesung2);
        Assertions.assertEquals(map, registrationSystem.retrieveCoursesWithFreePlaces());
    }

    @Test
    void retrieveStudentsEnrolledForACourse() {
        try {
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student2.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student2.getStudentID()));
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }


        List<Long> list = new ArrayList<>();
        list.add(1111L);
        list.add(1112L);
        try {
            Assertions.assertEquals(list, registrationSystem.retrieveStudentsEnrolledForACourse(vorlesung1.getVorlesungID()));
            Assertions.assertEquals(list, registrationSystem.retrieveStudentsEnrolledForACourse(vorlesung2.getVorlesungID()));
            list.clear();
            Assertions.assertEquals(list, registrationSystem.retrieveStudentsEnrolledForACourse(vorlesung3.getVorlesungID()));
        } catch (ExistException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void getAllCourses() {
        register();
        Assertions.assertEquals(3, registrationSystem.getAllCourses().size());
    }

    @Test
    void getAllStudents() {
        register();
        Assertions.assertEquals(2, registrationSystem.getAllStudents().size());
    }

    @Test
    void getAllLehrer() {
        register();
        Assertions.assertEquals(2, registrationSystem.getAllLehrer().size());
    }

    @Test
    void changeCreditFromACourse()  {
        try {
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student1.getStudentID()));
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }

        try {
            Assertions.assertTrue(registrationSystem.changeCreditFromACourse(100, 10));
        } catch (RegisterException | ExistException e) {
            System.out.println(e.getMessage());
        }

        Assertions.assertEquals(1111, registrationSystem.getStudentRepository().findOne(0).getStudentID());
        Assertions.assertEquals(16, registrationSystem.getStudentRepository().findOne(0).getTotalCredits());
    }

    @Test
    void deleteVorlesungFromLehrer() {
        try {
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung1.getVorlesungID(), student2.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student1.getStudentID()));
            Assertions.assertTrue(registrationSystem.register(vorlesung2.getVorlesungID(), student2.getStudentID()));
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }


        try {
            registrationSystem.deleteVorlesungFromLehrer(lehrer1.getLehrerID(), vorlesung1.getVorlesungID());
            registrationSystem.deleteVorlesungFromLehrer(lehrer1.getLehrerID(), vorlesung2.getVorlesungID());
        } catch (DeleteVorlesungFromLehrerException e) {
            System.out.println(e.getMessage());
        } catch (RegisterException e) {
            e.printStackTrace();
        }

        List<Vorlesung> list = new ArrayList<>();
        list.add(vorlesung2);
        list.add(vorlesung3);
        Assertions.assertEquals(list, registrationSystem.getAllCourses());
    }
}