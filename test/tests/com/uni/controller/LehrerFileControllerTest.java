package com.uni.controller;

import com.uni.model.Lehrer;
import com.uni.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class LehrerFileControllerTest {
    private LehrerFileController lehrerFileController;

    {
        try {
            lehrerFileController = new LehrerFileController("test//lehrerTest.txt");
        } catch (IOException e) {
            lehrerFileController = new LehrerFileController();
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_writeObject() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test//lehrerTest.txt"));
            lehrerFileController.controller_writeObject(objectOutputStream, new Lehrer("Alice", "Hart", 1L));
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_readObject() {
        controller_writeObject();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("test//lehrerTest.txt"));
            Lehrer lehrer = (Lehrer) lehrerFileController.controller_readObject(objectInputStream);
            System.out.println(lehrer);
            Assertions.assertEquals(new Lehrer("Alice", "Hart", 1L).toString(), lehrer.toString());
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_findAll() {
        try {
            Assertions.assertEquals(0, lehrerFileController.controller_findAll().size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_save() {
        try {
            lehrerFileController.controller_save("Alice", "Hart", 1L);
            lehrerFileController.controller_save("Zoe", "Hart", 2L);

            Assertions.assertEquals(2, lehrerFileController.controller_findAll().size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_update() {
        controller_save();
        try {
            lehrerFileController.controller_update(new Lehrer("Alice", "Spring", 1));
            Assertions.assertEquals("Spring", lehrerFileController.controller_findAll().get(0).getNachname());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}