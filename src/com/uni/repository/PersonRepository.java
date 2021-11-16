package com.uni.repository;
import com.uni.model.Person;

public class PersonRepository extends InMemoryRepository<Person> {
    public PersonRepository()
    {
        super();
    }

    @Override
    public Person save(Person entity) {
        return super.save(entity);
    }

    /**
     *
     * @param entity ein Objekt von Typ Person
     * @return eine aktualisierte Version des Objektes
     */
    @Override
    public Person update(Person entity) {
        Person personToUpdate = this.repoList.stream()
                .filter(person -> person.getVorname() == entity.getVorname())
                .findFirst()
                .orElseThrow();

        personToUpdate.setNachname(entity.getNachname());

        return personToUpdate;
    }

}
