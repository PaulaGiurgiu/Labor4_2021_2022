package com.uni.controller;

import com.uni.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentFileControllerTest {
    private StudentFileController studentFileController;

    {
        try {
            studentFileController = new StudentFileController("test//studentTest.txt");
        } catch (IOException e) {
            studentFileController = new StudentFileController();
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_writeObject() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test//studentTest.txt"));
            studentFileController.controller_writeObject(objectOutputStream, new Student("Alice", "Hart", 1L));
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_readObject() {
        controller_writeObject();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("test//studentTest.txt"));
            Student student;
            student = (Student) studentFileController.controller_readObject(objectInputStream);
            System.out.println(student);
            Assertions.assertEquals(new Student("Alice", "Hart", 1L).toString(), student.toString());
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_findAll() {
        try {
            Assertions.assertEquals(0, studentFileController.controller_findAll().size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_save() {
        try {
            studentFileController.controller_save("Alice", "Hart", 1L);
            studentFileController.controller_save("Zoe", "Hart", 2L);

            Assertions.assertEquals(2, studentFileController.controller_findAll().size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_update() {
        controller_save();
        try {
            studentFileController.controller_update(new Student("Alice", "Spring", 1L));
            Assertions.assertEquals("Spring", studentFileController.controller_findAll().get(0).getNachname());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}