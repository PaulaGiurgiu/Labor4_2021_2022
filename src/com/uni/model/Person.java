package com.uni.model;

import java.io.Serializable;

public class Person implements Serializable {
    private String vorname;
    private String nachname;

    /**
     * wir erstellen ein neues Objekt von Typ Person
     * @param vorname ein String
     * @param nachname ein String
     */
    public Person(String vorname, String nachname)
    {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                '}';
    }

    /* Getters */
    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    /* Setters */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }


}
