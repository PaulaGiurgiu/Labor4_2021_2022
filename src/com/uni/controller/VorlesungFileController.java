package com.uni.controller;

import com.uni.model.Vorlesung;
import com.uni.repository.VorlesungFileRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class VorlesungFileController {
    private final VorlesungFileRepository volesungFileRepository;

    public VorlesungFileController(){
        volesungFileRepository = new VorlesungFileRepository();
    }

    public VorlesungFileController(String fileName) throws IOException {
        volesungFileRepository = new VorlesungFileRepository(fileName);
    }

    /**
     * @param outputStream ein "ObjectOutputStream"
     * @param entity die Vorlesung, die man in der Datei schreiben will
     * @throws IOException falls die Vorlesung nicht in der Datei geschrieben wurde
     */
    public void controller_writeObject(ObjectOutputStream outputStream, Vorlesung entity) throws IOException {
        volesungFileRepository.writeObject(outputStream, entity);
    }

    /**
     * @param inputStream ein "ObjectInputStream"
     * @return die Vorlesung, die aus der Datei gelesen wurde
     * @throws IOException  falls die Vorlesung nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls die Vorlesung nicht aus der Datei gelesen wurde
     */
    public Object controller_readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return volesungFileRepository.readObject(inputStream);
    }

    /**
     *
     * @param id das Id einer Vorlesung aus der Liste "Vorlesung"
     * @throws IOException falls die Vorlesung nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls die Vorlesung nicht aus der Datei gelesen wurde
     */
    public void controller_findOne(int id) throws IOException, ClassNotFoundException {
        volesungFileRepository.findOne(id);
    }

    /**
     *
     * @return eine Liste mit allen Vorlesungen
     * @throws IOException falls man nicht aus der Datei lesen kann
     * @throws ClassNotFoundException falls man nicht aus der Datei lesen kann
     */
    public List<Vorlesung> controller_findAll() throws IOException, ClassNotFoundException {
        return volesungFileRepository.findAll();
    }

    /**
     *
     * @param Name ein String
     * @param IdLehrer eine "Long"-Zahl
     * @param IdVorlesung eine "Long"-Zahl
     * @param MaxEnrollment eine Zahl
     * @param Credits eine Zahl
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    public void controller_save(String Name, long IdLehrer, long IdVorlesung, int MaxEnrollment, int Credits) throws IOException {
        volesungFileRepository.save(new Vorlesung(Name, IdLehrer, IdVorlesung, MaxEnrollment, Credits));
    }

    /**
     *
     * @param Id eine "Long"-Zahl
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    public void controller_delete(Long Id) throws IOException {
        Vorlesung vorlesungToDelete = null;
        for (Vorlesung vorlesung: volesungFileRepository.getRepositoryList()) {
            if (vorlesung.getVorlesungID() == Id){
                vorlesungToDelete = new Vorlesung(vorlesung.getName(), vorlesung.getLehrer(), vorlesung.getVorlesungID(), vorlesung.getMaxEnrollment(), vorlesung.getCredits());
            }
        }
        volesungFileRepository.delete(vorlesungToDelete);
    }

    /**
     *
     * @param entity ein Objekt von Typ "Vorlesung"
     * @throws IOException falls man nicht in der Datei schreiben kann
     * @throws ClassNotFoundException falls man nicht in der Datei schreiben kann
     */
    public void controller_update(Vorlesung entity) throws IOException, ClassNotFoundException {
        volesungFileRepository.update(entity);
    }
}
