package com.uni.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lehrer extends Person implements Serializable {
    private long lehrerID;
    private List<Long> vorlesungen;
    /**
     * //wir erstellen ein neues Objekt von Typ "Lehrer"
     *
     * @param vorname  ein String
     * @param nachname ein String
     * @param lehrerID eine "long" Zahl
     */
    public Lehrer(String vorname, String nachname, long lehrerID) {
        super(vorname, nachname);
        this.lehrerID = lehrerID;
        this.vorlesungen = new ArrayList<>();
    }

    public Lehrer(String vorname, String nachname, long lehrerID, List<Long> vorlesungen) {
        super(vorname, nachname);
        this.lehrerID = lehrerID;
        this.vorlesungen = vorlesungen;
    }

    @Override
    public String toString() {
        return "Lehrer{" +
                "lehrerID=" + lehrerID +
                ", vorlesungen=" + vorlesungen +
                "} " + super.toString();
    }

    /* Getters */
    @Override
    public String getVorname() {
        return super.getVorname();
    }

    @Override
    public String getNachname() {
        return super.getNachname();
    }

    public long getLehrerID() { return lehrerID; }

    public List<Long> getVorlesungen() { return vorlesungen; }

    /* Setters */
    @Override
    public void setVorname(String vorname) {
        super.setVorname(vorname);
    }

    @Override
    public void setNachname(String nachname) {
        super.setNachname(nachname);
    }

    public void setLehrerID(long lehrerID) { this.lehrerID = lehrerID; }

    public void setVorlesungen(List<Long> vorlesungen) { this.vorlesungen = vorlesungen; }
}
