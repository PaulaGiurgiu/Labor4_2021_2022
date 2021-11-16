package com.uni.controller;

import com.uni.model.Student;
import com.uni.repository.StudentFileRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class StudentFileController {
    private final StudentFileRepository studentFileRepository;

    public StudentFileController(){
        studentFileRepository = new StudentFileRepository();
    }

    public StudentFileController(String fileName) throws IOException {
        studentFileRepository = new StudentFileRepository(fileName);
    }

    /**
     *
     * @param outputStream ein "ObjectOutputStream"
     * @param entity der Student, der man in der Datei schreiben will
     * @throws IOException falls der Student nicht in der Datei geschrieben wurde
     */
    public void controller_writeObject(ObjectOutputStream outputStream, Student entity) throws IOException {
        studentFileRepository.writeObject(outputStream, entity);
    }

    /**
     *
     * @param inputStream ein "ObjectInputStream"
     * @return der Student, der aus der Datei gelesen wurde
     * @throws IOException falls der Student nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls der Student nicht aus der Datei gelesen wurde
     */
    public Object controller_readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return studentFileRepository.readObject(inputStream);
    }

    /**
     *
     * @param id das Id eines Studentes
     * @throws IOException falls der Student nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls der Student nicht aus der Datei gelesen wurde
     */
    public void controller_findOne(int id) throws IOException, ClassNotFoundException {
        studentFileRepository.findOne(id);
    }

    /**
     *
     * @return eine Liste mit allen Studenten
     * @throws IOException falls man nicht aus der Datei lesen kann
     * @throws ClassNotFoundException falls man nicht aus der Datei lesen kann
     */
    public List<Student> controller_findAll() throws IOException, ClassNotFoundException {
        return studentFileRepository.findAll();
    }

    /**
     *
     * @param Vorname ein String
     * @param Nachname ein String
     * @param Id eine "Long"-Zahl
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    public void controller_save(String Vorname, String Nachname, Long Id) throws IOException {
        studentFileRepository.save(new Student(Vorname, Nachname, Id));
    }

    /**
     *
     * @param Id eine "Long"-Zahl
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    public void controller_delete(Long Id) throws IOException {
        Student studentToDelete = null;
        for (Student student: studentFileRepository.getRepositoryList()) {
            if (student.getStudentID() == Id){
                studentToDelete = new Student(student.getVorname(), student.getNachname(), Id);
            }
        }
        studentFileRepository.delete(studentToDelete);
    }

    /**
     *
     * @param entity ein Objekt von Typ "Student"
     * @throws IOException falls man nicht in der Datei schreiben kann
     * @throws ClassNotFoundException falls man nicht in der Datei schreiben kann
     */
    public void controller_update(Student entity) throws IOException, ClassNotFoundException {
        studentFileRepository.update(entity);
    }
}
