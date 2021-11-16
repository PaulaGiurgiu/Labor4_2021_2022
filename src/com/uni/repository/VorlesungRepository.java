package com.uni.repository;

import com.uni.model.Vorlesung;

import java.io.Serializable;

public class VorlesungRepository extends InMemoryRepository<Vorlesung> implements Serializable {

    public VorlesungRepository() {
        super();
    }

    /**
     *
     * @param entity ein Objekt von Typ Vorlesung
     * @return eine aktualisierte Version des Objektes
     */
    @Override
    public Vorlesung update(Vorlesung entity) {
        Vorlesung vorlesungToUpdate = this.repoList.stream()
                .filter(vorlesung -> vorlesung.getVorlesungID() == entity.getVorlesungID())
                .findFirst()
                .orElseThrow();

        vorlesungToUpdate.setName(entity.getName());
        vorlesungToUpdate.setLehrer(entity.getLehrer());
        vorlesungToUpdate.setMaxEnrollment(entity.getMaxEnrollment());
        vorlesungToUpdate.setStudentsEnrolled(entity.getStudentsEnrolled());
        vorlesungToUpdate.setCredits(entity.getCredits());

        return vorlesungToUpdate;
    }
}
