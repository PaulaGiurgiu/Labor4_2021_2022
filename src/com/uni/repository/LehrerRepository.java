package com.uni.repository;

import com.uni.model.Lehrer;

import java.io.Serializable;

public class LehrerRepository extends InMemoryRepository<Lehrer> implements Serializable {

    public LehrerRepository()
    {
        super();
    }

    /**
     *
     * @param entity ein Objekt von Typ Lehrer
     * @return eine aktualisierte Version des Objektes
     */
    @Override
    public Lehrer update(Lehrer entity) {
        Lehrer lehrerToUpdate = this.repoList.stream()
                .filter(lehrer -> lehrer.getLehrerID() == entity.getLehrerID())
                .findFirst()
                .orElseThrow();

        lehrerToUpdate.setVorname(entity.getVorname());
        lehrerToUpdate.setNachname(entity.getNachname());
        lehrerToUpdate.setVorlesungen(entity.getVorlesungen());

        return lehrerToUpdate;
    }
}
