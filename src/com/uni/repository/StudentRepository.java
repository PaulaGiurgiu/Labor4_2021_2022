package com.uni.repository;

import com.uni.model.Student;

import java.io.Serializable;

public class StudentRepository extends InMemoryRepository<Student> implements Serializable {

    public StudentRepository()
    {
        super();
    }

    /**
     *
     * @param entity ein Objekt von Typ Student
     * @return eine aktualisierte Version des Objektes
     */
    @Override
    public Student update(Student entity) {
        Student studentToUpdate = this.repoList.stream()
                .filter(student -> student.getStudentID() == entity.getStudentID())
                .findFirst()
                .orElseThrow();

        studentToUpdate.setVorname(entity.getVorname());
        studentToUpdate.setNachname(entity.getNachname());
        studentToUpdate.setTotalCredits(entity.getTotalCredits());
        studentToUpdate.setEnrolledCourses(entity.getEnrolledCourses());

        return studentToUpdate;
    }
}
