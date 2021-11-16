package com.uni.UI;

import com.uni.controller.LehrerFileController;
import com.uni.controller.RegistrationSystemController;
import com.uni.controller.StudentFileController;
import com.uni.controller.VorlesungFileController;
import com.uni.exception.DeleteVorlesungFromLehrerException;
import com.uni.exception.ExistException;
import com.uni.exception.RegisterException;
import com.uni.model.Lehrer;
import com.uni.model.Student;
import com.uni.model.Vorlesung;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class Konsole {
    RegistrationSystemController registrationSystem = new RegistrationSystemController();
    VorlesungFileController vorlesungFileController;
    String vorlesungFileName;
    StudentFileController studentFileController;
    String studentFileName;
    LehrerFileController lehrerFileController;
    String lehrerFileName;

    public Konsole() {
        System.out.println("Willkommen! \nTaste 1:\tRegister " +
                                        "\nTaste 2:\tUnegister " +
                                        "\nTaste 3:\tPrint Liste Vorlesungen " +
                                        "\nTaste 4:\tPrint Liste Studenten " +
                                        "\nTaste 5:\tPrint Liste Lehrer " +
                                        "\nTaste 6:\tVorlesungen mit freie Plätze " +
                                        "\nTaste 7:\tPrint Liste Studenten von einer Vorlesung " +
                                        "\nTaste 8:\tCredit Anzahl ändern " +
                                        "\nTaste 9:\tVorlesung löschen von Lehrer" +
                                        "\nTaste 10:\tSort Liste Studenten " +
                                        "\nTaste 11:\tSort Liste Vorlesung " +
                                        "\nTaste 12:\tFilter Liste Studenten " +
                                        "\nTaste 13:\tFilter Liste Vorlesung " +
                                        "\nTaste 14:\tAdd Vorlesung " +
                                        "\nTaste 15:\tAdd Student " +
                                        "\nTaste 16:\tAdd Lehrer" +
                                        "\nTaste 0:\tExit\n");


        ui_setFileNames();
        ui_addElements();

        Scanner command = new Scanner(System.in);
        int commandInput;
        do {
            System.out.println("\nCommand: ");
            switch (commandInput = command.nextInt()) {
                case 0:
                    System.out.println("Bis bald.");
                    break;
                case 1:
                    System.out.println("Register");
                    ui_getAllCourses();
                    ui_getAllStudents();
                    ui_register();
                    break;
                case 2:
                    System.out.println("Unregister");
                    ui_getAllCourses();
                    ui_getAllStudents();
                    ui_unregister();
                    break;
                case 3:
                    System.out.println("Print Liste Vorlesungen");
                    ui_getAllCourses();
                    ui_getAllCoursesFromFile();
                    break;
                case 4:
                    System.out.println("Print Liste Studenten");
                    ui_getAllStudents();
                    ui_getAllStudentsFromFile();
                    break;
                case 5:
                    System.out.println("Print Liste Lehrer");
                    ui_getAllLehrer();
                    ui_getAllLehrerFromFile();
                    break;
                case 6:
                    System.out.println("Vorlesungen mit freie Plätze");
                    ui_retrieveCoursesWithFreePlaces();
                    break;
                case 7:
                    System.out.println("Print Liste Studenten von einer Vorlesung");
                    ui_getAllCourses();
                    ui_retrieveStudentsEnrolledForACourse();
                    break;
                case 8:
                    System.out.println("Credit Anzahl ändern");
                    ui_getAllCourses();
                    ui_changeCreditFromACourse();
                    break;
                case 9:
                    System.out.println("Vorlesung löschen");
                    ui_getAllCourses();
                    ui_deleteVorlesungFromLehrer();
                    break;
                case 10:
                    System.out.println("Sort Liste Studenten nach 'Vorname'");
                    ui_sortListeStudenten();
                    break;
                case 11:
                    System.out.println("Sort Liste Vorlesung nach 'Name'");
                    ui_sortListeVorlesungen();
                    break;
                case 12:
                    System.out.println("Filter Liste Studenten nach Studenten mit Anzahl der Vorlesungen >= 2");
                    ui_filterListeStudenten();
                    break;
                case 13:
                    System.out.println("Filter Liste Vorlesung nach Vorlesungen mit maximal Enrollment > 30");
                    ui_filterListeVorlesungen();
                    break;
                case 14:
                    System.out.println("Add Vorlesung");
                    ui_addVorlesung();
                    break;
                case 15:
                    System.out.println("Add Student");
                    ui_addStudent();
                    break;
                case 16:
                    System.out.println("Add Lehrer");
                    ui_addLehrer();
                    break;
            }

        } while (commandInput != 0);
    }


    /**
     * die "ui_register"-Methode meldet ein bestimmter Student an eine Vorlesung an
     */
    private void ui_register(){
        System.out.println("Register");
        Scanner idVorlesung = new Scanner(System.in);
        Scanner idStudent = new Scanner(System.in);
        long VorlesungID, StudentID;

        System.out.println("Vorlesung ID: ");
        VorlesungID = idVorlesung.nextLong();
        System.out.println("Student ID: ");
        StudentID = idStudent.nextLong();
        try {
            registrationSystem.controller_register(VorlesungID, StudentID);
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * die "ui_unregister"-Methode löscht der Student aus der Vorlesung
     */
    private void ui_unregister(){
        System.out.println("Unregister");
        Scanner idVorlesung = new Scanner(System.in);
        Scanner idStudent = new Scanner(System.in);
        long VorlesungID, StudentID;

        System.out.println("Vorlesung ID: ");
        VorlesungID = idVorlesung.nextLong();
        System.out.println("Student ID: ");
        StudentID = idStudent.nextLong();
        try {
            registrationSystem.controller_unregister(VorlesungID, StudentID);
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * die "ui_getAllCourses"-Methode gibt alle Vorlesungen zurück
     */
    private void ui_getAllCourses() {
        System.out.println("Print Liste Vorlesungen");
        for (Vorlesung vorlesung:registrationSystem.controller_getAllCourses()) {
            System.out.println(vorlesung);
        }

    }

    /**
     * die "ui_getAllCoursesFromFile"-Methode gibt alle Vorlesungen aus der Datei zurück
     */
    private void ui_getAllCoursesFromFile(){
        System.out.println("Print Liste Vorlesungen aus der Datei");
        for (Vorlesung vorlesung: registrationSystem.controller_getAllCourses()) {
            try {
                vorlesungFileController.controller_update(vorlesung);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Problem mit der Aktualisierung der Datei");
            }
        }

        try {
            for (Vorlesung vorlesung: vorlesungFileController.controller_findAll()) {
                System.out.println(vorlesung);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problem mit der 'Vorlesung'-Datei. ");
        }
    }

    /**
     * die "ui_getAllStudents"-Methode gibt alle Studenten zurück
     */
    private void ui_getAllStudents(){
        System.out.println("Print Liste Studenten");
        for (Student student: registrationSystem.controller_getAllStudents()) {
            System.out.println(student);
        }
    }

    /**
     * die "ui_getAllStudentsFromFile"-Methode gibt alle Studenten aus der Datei zurück
     */
    private void ui_getAllStudentsFromFile(){
        System.out.println("Print Liste Studenten aus der Datei");
        for (Student student: registrationSystem.controller_getAllStudents()) {
            try {
                studentFileController.controller_update(student);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Problem mit der Aktualisierung der Datei");
            }
        }

        try {
            for (Student student: studentFileController.controller_findAll()) {
                System.out.println(student);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problem mit der 'Student'-Datei. ");
        }
    }

    /**
     * die "ui_getAllLehrer"-Methode gibt alle Lehrer zurück
     */
    private void ui_getAllLehrer(){
        System.out.println("Print Liste Lehrer");
        for (Lehrer lehrer:registrationSystem.controller_getAllLehrer()) {
            System.out.println(lehrer);
        }
    }

    /**
     * die "ui_getAllLehrerFromFile"-Methode gibt alle Lehrer aus der Datei zurück
     */
    private void ui_getAllLehrerFromFile(){
        System.out.println("Print Liste Lehrer aus der Datei");
        for (Lehrer lehrer: registrationSystem.controller_getAllLehrer()) {
            try {
                lehrerFileController.controller_update(lehrer);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Problem mit der Aktualisierung der Datei");
            }
        }

        try {
            for (Lehrer lehrer: lehrerFileController.controller_findAll()) {
                System.out.println(lehrer);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problem mit der 'Lehrer'-Datei. ");
        }
    }

    /**
     * die "ui_retrieveCoursesWithFreePlaces"-Methode zeigt alle Vorlesungen mit freie Plätze
     */
    private void ui_retrieveCoursesWithFreePlaces(){
        System.out.println("Vorlesungen mit freie Plätze");
        for (Map.Entry map: registrationSystem.controller_retrieveCoursesWithFreePlaces().entrySet()) {
            System.out.println(map.getKey() + " " + map.getValue());
        }
    }

    /**
     * die "ui_retrieveStudentsEnrolledForACourse"-Methode zeigt die "Student"-Liste von einer Vorlesung
     */
    private void ui_retrieveStudentsEnrolledForACourse(){
        System.out.println("Print Liste Studenten von einer Vorlesung");
        Scanner idVorlesung = new Scanner(System.in);
        long VorlesungId;

        System.out.println("Vorlesung ID: ");
        VorlesungId = idVorlesung.nextLong();
        try {
            for (Long student:registrationSystem.controller_retrieveStudentsEnrolledForACourse(VorlesungId)) {
                System.out.println(student);
            }
        } catch (ExistException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * die "ui_changeCreditFromACourse"-Methode ändert die Anzahl der Credits
     */
    private void ui_changeCreditFromACourse(){
        System.out.println("Credit Anzahl ändern");
        Scanner idVorlesung = new Scanner(System.in);
        Scanner credits = new Scanner(System.in);
        long VorlesungId;
        int newCredit;

        System.out.println("Vorlesung ID: ");
        VorlesungId = idVorlesung.nextLong();
        System.out.println("New Credits: ");
        newCredit = credits.nextInt();
        try {
            registrationSystem.controller_changeCreditFromACourse(VorlesungId, newCredit);
        } catch (RegisterException | ExistException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * die "ui_deleteVorlesungFromLehrer"-Methode löscht die Vorlesung von Lehrer
     */
    private void ui_deleteVorlesungFromLehrer(){
        System.out.println("Vorlesung löschen");
        Scanner idVorlesung = new Scanner(System.in);
        Scanner idLehrer = new Scanner(System.in);
        long VorlesungId, LehrerId;

        System.out.println("Vorlesung ID: ");
        VorlesungId = idVorlesung.nextLong();
        System.out.println("Lehrer ID: ");
        LehrerId = idLehrer.nextLong();
        try {
            registrationSystem.controller_deleteVorlesungFromLehrer(LehrerId, VorlesungId);
        } catch (DeleteVorlesungFromLehrerException | RegisterException e) {
            System.out.println(e.getMessage());
        }

        ui_getAllCourses();
    }

    /**
     * die "ui_sortListeStudenten"-Methode sort die "Student"-Liste nach 'Vorname'
     */
    private void ui_sortListeStudenten(){
        System.out.println("Sort Liste Studenten");
        for (Student student: registrationSystem.controller_sortListeStudenten()) {
            System.out.println(student);
        }
    }

    /**
     * die "ui_sortListeVorlesungen"-Methode sort die "Vorlesung"-Liste nach 'Name'
     */
    private void ui_sortListeVorlesungen(){
        System.out.println("Sort Liste Vorlesung");
        for (Vorlesung vorlesung: registrationSystem.controller_sortListeVorlesungen()) {
            System.out.println(vorlesung);
        }
    }

    /**
     * die "ui_filterListeStudenten"-Methode filter die "Student"-Liste nach Studenten mit Anzahl der Vorlesungen >= 2
     */
    private void ui_filterListeStudenten(){
        System.out.println("Filter Liste Studenten");
        for (Student student: registrationSystem.controller_filterListeStudenten()) {
            System.out.println(student);
        }
    }

    /**
     * die "ui_filterListeVorlesungen"-Methode filter die "Vorlesung"-Liste nach Vorlesungen mit maximal Enrollment > 30
     */
    private void ui_filterListeVorlesungen(){
        System.out.println("Filter Liste Vorlesung");
        for (Vorlesung vorlesung: registrationSystem.controller_filterListeVorlesungen()) {
            System.out.println(vorlesung);
        }
    }


    /**
     * die "ui_addStudent"-Methode fügt einen neuen Student
     */
    private void ui_addStudent(){
        System.out.println("Add Student");
        Scanner vorname = new Scanner(System.in);
        Scanner nachname = new Scanner(System.in);
        Scanner id = new Scanner(System.in);
        String Vorname, Nachname;
        long Id;

        System.out.println("Vorname: ");
        Vorname = vorname.nextLine();
        System.out.println("Nachname: ");
        Nachname = nachname.nextLine();
        System.out.println("ID: ");
        Id = id.nextLong();
        registrationSystem.controller_addStudent(Vorname, Nachname, Id);

        try {
            studentFileController.controller_save(Vorname, Nachname, Id);
        } catch (IOException e) {
            System.out.println("Problem mit der Einfugung von Daten in der 'Student'-Datei." );
        }

    }

    /**
     * die "ui_addLehrer"-Methode fügt einen neuen Lehrer
     */
    private void ui_addLehrer(){
        System.out.println("Add Lehrer");
        Scanner vorname = new Scanner(System.in);
        Scanner nachname = new Scanner(System.in);
        Scanner id = new Scanner(System.in);
        String Vorname, Nachname;
        long Id;

        System.out.println("Vorname: ");
        Vorname = vorname.nextLine();
        System.out.println("Nachname: ");
        Nachname = nachname.nextLine();
        System.out.println("ID: ");
        Id = id.nextLong();
        registrationSystem.controller_addLehrer(Vorname, Nachname, Id);

        try {
            lehrerFileController.controller_save(Vorname, Nachname, Id);
        } catch (IOException e) {
            System.out.println("Problem mit der Einfugung von Daten in der 'Lehrer'-Datei." );
        }
    }

    /**
     * die "ui_addVorlesung"-Methode fügt eine neue Vorlesung
     */
    private void ui_addVorlesung(){
        System.out.println("Add Vorlesung");
        Scanner name = new Scanner(System.in);
        Scanner idLehrer = new Scanner(System.in);
        Scanner idVorlesung = new Scanner(System.in);
        Scanner maxEnrollment = new Scanner(System.in);
        Scanner credits = new Scanner(System.in);
        String Name;
        long IdLehrer, IdVorlesung;
        int MaxEnrollment, Credits;

        System.out.println("Name der Vorlesung: ");
        Name = name.nextLine();
        System.out.println("Lehrer ID: ");
        IdLehrer = idLehrer.nextLong();
        System.out.println("Vorlesung ID: ");
        IdVorlesung = idVorlesung.nextLong();
        System.out.println("Maximal Teilnehner: ");
        MaxEnrollment = maxEnrollment.nextInt();
        System.out.println("Anzahl Credit: ");
        Credits = credits.nextInt();
        registrationSystem.controller_addVorlesung(Name, IdLehrer, IdVorlesung, MaxEnrollment, Credits);

        try {
            vorlesungFileController.controller_save(Name, IdLehrer, IdVorlesung, MaxEnrollment, Credits);
        } catch (IOException e){
            System.out.println("Problem mit der Einfugung von Daten in der 'Vorlesung'-Datei.");
        }
    }

    /**
     * die "ui_addElements"-Methode fügt Daten am Anfang des Programs
     */
    private void ui_addElements(){
        registrationSystem.controller_addStudent("Zoe", "Miller", 1111L);
        registrationSystem.controller_addStudent("Alice", "Hart", 1112L);
        registrationSystem.controller_addLehrer("Tom", "John", 1L);
        registrationSystem.controller_addLehrer("Jack", "Storm", 2L);

        registrationSystem.controller_addVorlesung("BD", 1L, 100L, 30, 5);
        registrationSystem.controller_addVorlesung("BD2", 2L, 101L, 31, 6);
        registrationSystem.controller_addVorlesung("BD3", 1L, 102L, 32, 7);

        try {
            studentFileController.controller_save("Zoe", "Miller", 1111L);
            studentFileController.controller_save("Alice", "Hart", 1112L);
        } catch (IOException e) {
            System.out.println("Problem mit der Einfugung von Daten " + e.getMessage());
        }

        try {
            lehrerFileController.controller_save("Tom", "John", 1L);
            lehrerFileController.controller_save("Jack", "Storm", 2L);
        } catch (IOException e) {
            System.out.println("Problem mit der Einfugung von Daten " + e.getMessage());
        }

        try {
            vorlesungFileController.controller_save("BD", 1L, 100L, 30, 5);
            vorlesungFileController.controller_save("BD2", 2L, 101L, 31, 6);
            vorlesungFileController.controller_save("BD3", 1L, 102L, 32, 7);
        } catch (IOException e) {
            System.out.println("Problem mit der Einfugung von Daten " + e.getMessage());
        }

    }

    /**
     * die "ui_setFileNames"-Methode erstellt eine neue Datei fur "Student", "Lehrer", "Vorlesung", in der man
     * die neue Datein schreiben wird
     */
    public void ui_setFileNames(){
        System.out.println("Bitte die Name der Dateien schreiben.\nIn dieser Dateien werden die Daten eingefugt.");
        //Vorlesung
        Scanner vorlesung = new Scanner(System.in);
        System.out.println("Vorlesung Datei: ");
        vorlesungFileName = "src//";
        vorlesungFileName += vorlesung.nextLine();

        File fileVorlesung = new File(vorlesungFileName);
        try {
            fileVorlesung.createNewFile();
        } catch (IOException ex) {
            System.out.println("Problem mit der Erstellung der Datei " + ex.getMessage() + ".");
        }

        vorlesungFileController = new VorlesungFileController();
        try {
            vorlesungFileController = new VorlesungFileController(vorlesungFileName);
        } catch (IOException e) {
            System.out.println("Problem mit der Erstellung der Datei " + e.getMessage() + ". \nSet dafault File.");
            vorlesungFileName = "src//vorlesung.ser";
            File fileVorlesung1 = new File(vorlesungFileName);
            try {
                fileVorlesung1.createNewFile();
            } catch (IOException ex) {
                System.out.println("Problem mit der Erstellung der Datei " + ex.getMessage() + ".");
            }
            try {
                vorlesungFileController = new VorlesungFileController(vorlesungFileName);
            } catch (IOException ex) {
                System.out.println("Problem mit der Erstellung der Datei " + e.getMessage() + ".");
            }
        }

        //Student
        Scanner student = new Scanner(System.in);
        System.out.println("Student Datei: ");
        studentFileName = "src//";
        studentFileName += student.nextLine();

        File fileStudent = new File(studentFileName);
        try {
            fileStudent.createNewFile();
        } catch (IOException ex) {
            System.out.println("Problem mit der Erstellung der Datei " + ex.getMessage() + ".");
        }

        studentFileController = new StudentFileController();
        try {
            studentFileController = new StudentFileController(studentFileName);
        } catch (IOException e) {
            System.out.println("Problem mit der Erstellung der Datei " + e.getMessage() + ". \nSet dafault File.");
            studentFileName = "src//student.ser";
            File fileStudent1 = new File(studentFileName);
            try {
                fileStudent1.createNewFile();
            } catch (IOException ex) {
                System.out.println("Problem mit der Erstellung der Datei " + ex.getMessage() + ".");
            }
            try {
                studentFileController = new StudentFileController(studentFileName);
            } catch (IOException ex) {
                System.out.println("Problem mit der Erstellung der Datei " + e.getMessage() + ".");
            }
        }

        //Lehrer
        Scanner lehrer = new Scanner(System.in);
        System.out.println("Lehrer Datei: ");
        lehrerFileName = "src//";
        lehrerFileName += lehrer.nextLine();

        File fileLehrer = new File(lehrerFileName);
        try {
            fileLehrer.createNewFile();
        } catch (IOException ex) {
            System.out.println("Problem mit der Erstellung der Datei " + ex.getMessage() + ".");
        }

        lehrerFileController = new LehrerFileController();
        try {
            lehrerFileController = new LehrerFileController(lehrerFileName);
        } catch (IOException e) {
            System.out.println("Problem mit der Erstellung der Datei " + e.getMessage() + ". \nSet dafault File.");
            lehrerFileName = "src//lehrer.ser";
            File fileLehrer1 = new File(lehrerFileName);
            try {
                fileLehrer1.createNewFile();
            } catch (IOException ex) {
                System.out.println("Problem mit der Erstellung der Datei " + ex.getMessage() + ".");
            }
            try {
                lehrerFileController = new LehrerFileController(lehrerFileName);
            } catch (IOException ex) {
                System.out.println("Problem mit der Erstellung der Datei " + e.getMessage() + ".");
            }
        }
    }
    

}
