package com.uni.controller;

import com.uni.model.Vorlesung;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class VorlesungFileControllerTest {
    private VorlesungFileController vorlesungFileController;

    {
        try {
            vorlesungFileController = new VorlesungFileController("test//vorlesungTest.txt");
        } catch (IOException e) {
            vorlesungFileController = new VorlesungFileController();
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_writeObject() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test//vorlesungTest.txt"));
            vorlesungFileController.controller_writeObject(objectOutputStream, new Vorlesung("BD", 1, 11, 30, 5));
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_readObject() {
        controller_writeObject();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("test//vorlesungTest.txt"));
            Vorlesung vorlesung;
            vorlesung = (Vorlesung) vorlesungFileController.controller_readObject(objectInputStream);
            System.out.println(vorlesung);
            Assertions.assertEquals(new Vorlesung("BD", 1, 11, 30, 5).toString(), vorlesung.toString());
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_findAll() {
        try {
            Assertions.assertEquals(0, vorlesungFileController.controller_findAll().size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_save() {
        try {
            vorlesungFileController.controller_save("BD", 1, 11, 30, 5);
            vorlesungFileController.controller_save("BD2", 2, 12, 31, 6);

            Assertions.assertEquals(2, vorlesungFileController.controller_findAll().size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void controller_update() {
        controller_save();
        try {
            vorlesungFileController.controller_update(new Vorlesung("BD6", 1, 11, 30, 5));
            Assertions.assertEquals("BD6", vorlesungFileController.controller_findAll().get(0).getName());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}