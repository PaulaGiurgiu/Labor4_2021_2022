package com.uni.repository;

import com.uni.model.Lehrer;
import com.uni.model.Person;
import com.uni.model.Student;
import com.uni.model.Vorlesung;
import com.uni.repository.LehrerRepository;
import com.uni.repository.PersonRepository;
import com.uni.repository.StudentRepository;
import com.uni.repository.VorlesungRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryRepositoryTest {
    private final PersonRepository personRepository = new PersonRepository();
    private final Person person1 = new Person("Alice", "Hart");

    private final StudentRepository studentRepository = new StudentRepository();
    private final Student student1 = new Student("Zoe", "Miller", 11);

    private final LehrerRepository lehrerRepository = new LehrerRepository();
    private final Lehrer lehrer1 = new Lehrer("Tom", "John", 1);

    private final VorlesungRepository vorlesungRepository = new VorlesungRepository();
    private final Vorlesung vorlesung1 = new Vorlesung("BD", 1, 11, 30, 5);

    @Test
    void findOne() {
        personRepository.save(person1);
        assertEquals(person1, personRepository.findOne(0));

        studentRepository.save(student1);
        assertEquals(student1, studentRepository.findOne(0));

        lehrerRepository.save(lehrer1);
        assertEquals(lehrer1, lehrerRepository.findOne(0));

        vorlesungRepository.save(vorlesung1);
        assertEquals(vorlesung1, vorlesungRepository.findOne(0));
    }

    @Test
    void findAll() {
        personRepository.save(person1);
        assertEquals(1, personRepository.findAll().size());

        studentRepository.save(student1);
        assertEquals(1, studentRepository.findAll().size());

        lehrerRepository.save(lehrer1);
        assertEquals(1, lehrerRepository.findAll().size());

        vorlesungRepository.save(vorlesung1);
        assertEquals(1, vorlesungRepository.findAll().size());
    }

    @Test
    void save() {
        assertEquals(person1, personRepository.save(person1));

        assertEquals(student1, studentRepository.save(student1));

        assertEquals(lehrer1, lehrerRepository.save(lehrer1));

        assertEquals(vorlesung1, vorlesungRepository.save(vorlesung1));
    }

    @Test
    void delete() {
        personRepository.save(person1);
        assertEquals(1, personRepository.findAll().size());
        personRepository.delete(person1);
        assertEquals(0, personRepository.findAll().size());

        studentRepository.save(student1);
        assertEquals(1, studentRepository.findAll().size());
        studentRepository.delete(student1);
        assertEquals(0, studentRepository.findAll().size());

        lehrerRepository.save(lehrer1);
        assertEquals(1, lehrerRepository.findAll().size());
        lehrerRepository.delete(lehrer1);
        assertEquals(0, lehrerRepository.findAll().size());

        vorlesungRepository.save(vorlesung1);
        assertEquals(1, vorlesungRepository.findAll().size());
        vorlesungRepository.delete(vorlesung1);
        assertEquals(0, vorlesungRepository.findAll().size());

    }
}