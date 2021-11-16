package com.uni.controller;

import com.uni.exception.DeleteVorlesungFromLehrerException;
import com.uni.exception.ExistException;
import com.uni.exception.RegisterException;
import com.uni.model.Lehrer;
import com.uni.model.Student;
import com.uni.model.Vorlesung;
import com.uni.repository.RegistrationSystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationSystemController{
    private final RegistrationSystem registrationSystem = new RegistrationSystem();

    public RegistrationSystemController() {
    }

    /**
     * @param VorlesungID eine "long" Zahl
     * @param StudentID eine "long" Zahl
     * @throws RegisterException falls der Student mit der Id "StudentID" nicht an der Vorlesung mit der Id "VorlesungID" registriert wurde
     */
    public void controller_register(long VorlesungID, long StudentID) throws RegisterException {
        registrationSystem.register(VorlesungID, StudentID);
    }

    /**
     * @param VorlesungID eine "long" Zahl
     * @param StudentID eine "long" Zahl
     * @throws RegisterException falls der Student mit der Id "StudentID" nicht aus der Vorlesung mit der Id "VorlesungID" "unregistriert" wurde
     */
    public void controller_unregister(long VorlesungID, long StudentID) throws RegisterException {
        registrationSystem.unregister(VorlesungID, StudentID);
    }

    /**
     * @return die Liste aller Vorlesungen
     */
    public List<Vorlesung> controller_getAllCourses(){
        return registrationSystem.getAllCourses();
    }

    /**
     * @return die Liste aller Studenten
     */
    public List<Student> controller_getAllStudents(){
        return registrationSystem.getAllStudents();
    }

    /**
     * @return die Liste aller Lehrer
     */
    public List<Lehrer> controller_getAllLehrer(){
        return registrationSystem.getAllLehrer();
    }

    /**
     * @return ein HashMap mit der Vorlesungen, die freie Platze haben und deren Anzahl
     */
    public HashMap<Integer, Vorlesung> controller_retrieveCoursesWithFreePlaces(){
        return registrationSystem.retrieveCoursesWithFreePlaces();
    }

    /**
     * @param VorlesungID eine "Long" Zahl, die ein "Vorlesung" Id entspricht
     * @return eine Liste von Studenten, die an der gegebenen Vorlesung teilnehmen
     * @throws ExistException falls die Vorlesung nicht in der Liste ist
     */
    public List<Long> controller_retrieveStudentsEnrolledForACourse(long VorlesungID) throws ExistException {
        return registrationSystem.retrieveStudentsEnrolledForACourse(VorlesungID);
    }

    /**
     * @param VorlesungID eine "Long" Zahl, die ein "Vorlesung" Id entspricht
     * @param newCredit die neue Anzahl von Credits
     * @throws RegisterException falls die alte Studenten nicht mehr an der Vorlesung teilnehmen konnten
     * @throws ExistException falls die Vorlesung existiert nicht
     */
    public void controller_changeCreditFromACourse(long VorlesungID, int newCredit) throws RegisterException, ExistException {
        registrationSystem.changeCreditFromACourse(VorlesungID, newCredit);
    }

    /**
     * @param LehrerID eine "Long" Zahl, die ein "Lehrer" Id entspricht
     * @param VorlesungID eine "Long" Zahl, die ein "Vorlesung" Id entspricht
     * @throws DeleteVorlesungFromLehrerException falls der Lehrer nich die Vorlesung löschen kann
     * @throws RegisterException falls die Vorlesung existiert nicht
     */
    public void controller_deleteVorlesungFromLehrer(long LehrerID, long VorlesungID) throws DeleteVorlesungFromLehrerException, RegisterException {
        registrationSystem.deleteVorlesungFromLehrer(LehrerID, VorlesungID);
    }

    /**
     * @return die sortierte Liste der Studenten
     */
    public List<Student> controller_sortListeStudenten(){
        Collections.sort(registrationSystem.getAllStudents(), new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getVorname().compareTo(o2.getVorname());
            }
        });

        return registrationSystem.getAllStudents();
    }

    /**
     * @return die sortierte Liste der Vorlesungen
     */
    public List<Vorlesung> controller_sortListeVorlesungen(){
        Collections.sort(registrationSystem.getAllCourses(), new Comparator<Vorlesung>() {
            @Override
            public int compare(Vorlesung o1, Vorlesung o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return registrationSystem.getAllCourses();
    }

    /**
     * @return die filtrierte Liste der Studenten
     */
    public List<Student> controller_filterListeStudenten(){
        List<Student> studentList = this.controller_getAllStudents().stream()
                .filter(student -> student.getEnrolledCourses().size() >=2).collect(Collectors.toList());

        return studentList;
    }

    /**
     * @return die filtrierte Liste der Vorlesungen
     */
    public List<Vorlesung> controller_filterListeVorlesungen(){
        List<Vorlesung> vorlesungList = this.controller_getAllCourses().stream()
                .filter(vorlesung -> vorlesung.getMaxEnrollment() > 30).collect(Collectors.toList());

        return vorlesungList;
    }

    /**
     * @param Vorname ein String
     * @param Nachname ein String
     * @param Id ein "Long"-Zahl
     */
    public void controller_addStudent(String Vorname, String Nachname, Long Id){
        registrationSystem.getStudentRepository().save(new Student(Vorname, Nachname, Id));
    }

    /**
     * @param Vorname ein String
     * @param Nachname ein String
     * @param Id ein "Long"-Zahl
     */
    public void controller_addLehrer(String Vorname, String Nachname, Long Id){
        registrationSystem.getLehrerRepository().save(new Lehrer(Vorname, Nachname, Id));
    }

    /**
     * @param Name ein String
     * @param IdLehrer ein "Long"-Zahl
     * @param IdVorlesung ein "Long"-Zahl
     * @param MaxEnrollment ein Zahl
     * @param Credits ein Zahl
     */
    public void controller_addVorlesung(String Name, Long IdLehrer, Long IdVorlesung, int MaxEnrollment, int Credits){
        registrationSystem.getVorlesungRepository().save(new Vorlesung(Name, IdLehrer, IdVorlesung, MaxEnrollment, Credits));
    }
}
