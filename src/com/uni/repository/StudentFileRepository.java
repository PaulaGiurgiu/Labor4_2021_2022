package com.uni.repository;

import com.uni.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileRepository implements CrudRepository<Student> {
    protected ObjectOutputStream objectOutputStream;
    protected FileOutputStream fileOutputStream;
    protected ObjectInputStream objectInputStream;
    protected FileInputStream fileInputStream;
    protected String fileName;
    protected List<Student> repoList;

    public StudentFileRepository(){
        super();
        this.fileName = "src//student.ser";
        repoList = new ArrayList<>();
    }

    public StudentFileRepository(String fileName) throws IOException {
        super();
        this.fileName = fileName;
        repoList = new ArrayList<>();
        this.fileOutputStream = new FileOutputStream(fileName);
        this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
        this.fileInputStream = new FileInputStream(fileName);
        this.objectInputStream = new ObjectInputStream(fileInputStream);
    }

    /**
     * @return eine Liste mit allen Studenten
     */
    public List<Student> getRepositoryList() {
        return repoList;
    }

    /**
     * @param outputStream ein "ObjectOutputStream"
     * @param entity der Student, der man in der Datei schreiben will
     * @throws IOException falls der Student nicht in der Datei geschrieben wurde
     */
    public void writeObject(ObjectOutputStream outputStream, Student entity) throws IOException {
        outputStream.writeObject(entity);
    }

    /**
     * @param inputStream ein "ObjectInputStream"
     * @return der Student, der aus der Datei gelesen wurde
     * @throws IOException  falls der Student nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls der Student nicht aus der Datei gelesen wurde
     */
    public Object readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Student entity;
        entity = (Student) inputStream.readObject();

        return entity;
    }

    /**
     *
     * @param id das Id eines Objektes aus der Liste "repoList"
     * @return das Objekt aus der Liste "repoList"
     * @throws IOException falls das Object nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls das Object nicht aus der Datei gelesen wurde
     */
    @Override
    public Student findOne(int id) throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Student student : repoList) {
                writeObject(objectOutputStream, student);
            }
        }

        List<Student> list = new ArrayList<>();
        while (fileInputStream.available() > 0) {
            Student entity = (Student) objectInputStream.readObject();
            if (entity.getStudentID() == id) {
                list.add(entity);
            }

        }
        return list.get(list.size() - 1);
    }

    /**
     *
     * @return eine Liste mit allen Studenten
     * @throws IOException falls man nicht aus der Datei lesen kann
     * @throws ClassNotFoundException falls man nicht aus der Datei lesen kann
     */
    @Override
    public List<Student> findAll() throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Student student : repoList) {
                writeObject(objectOutputStream, student);
            }
        }

        return repoList;
    }

    /**
     *
     * @param entity ein Objekt von Typ "Student"
     * @return das neue eingefügte Object
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    @Override
    public Student save(Student entity) throws IOException {
        repoList.add(entity);
        if (fileInputStream.available() <= 0)
        {
            for (Student student : repoList) {
                writeObject(objectOutputStream, student);
            }
        }
        return entity;
    }

    /**
     *
     * @param entity ein Objekt von Typ "Student"
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    @Override
    public void delete(Student entity) throws IOException {
        if (fileInputStream.available() <= 0)
        {
            for (Student student : repoList) {
                writeObject(objectOutputStream, student);
            }
        }
        repoList.remove(entity);
    }

    /**
     *
     * @param entity ein Objekt von Typ "Student"
     * @return das neue aktualisierte Object
     * @throws IOException falls man nicht in der Datei schreiben kann
     * @throws ClassNotFoundException falls man nicht in der Datei schreiben kann
     */
    @Override
    public Student update(Student entity) throws IOException, ClassNotFoundException {
        Student studentToUpdate = this.repoList.stream()
                .filter(student -> student.getStudentID() == entity.getStudentID())
                .findFirst()
                .orElseThrow();

        studentToUpdate.setVorname(entity.getVorname());
        studentToUpdate.setNachname(entity.getNachname());
        studentToUpdate.setTotalCredits(entity.getTotalCredits());
        studentToUpdate.setEnrolledCourses(entity.getEnrolledCourses());

        if (fileInputStream.available() <= 0)
        {
            for (Student student : repoList) {
                writeObject(objectOutputStream, student);
            }
        }

        return entity;
    }
}
