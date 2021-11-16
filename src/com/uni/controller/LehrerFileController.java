package com.uni.controller;

import com.uni.model.Lehrer;
import com.uni.repository.LehrerFileRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class LehrerFileController {
    private final LehrerFileRepository lehrerFileRepository;

    public LehrerFileController(){
        lehrerFileRepository = new LehrerFileRepository();
    }

    public LehrerFileController(String fileName) throws IOException {
        lehrerFileRepository = new LehrerFileRepository(fileName);
    }

    /**
     * @param outputStream ein "ObjectOutputStream"
     * @param entity der Lehrer, der man in der Datei schreiben will
     * @throws IOException falls der Lehrer nicht in der Datei geschrieben wurde
     */
    public void controller_writeObject(ObjectOutputStream outputStream, Lehrer entity) throws IOException {
        lehrerFileRepository.writeObject(outputStream, entity);
    }

    /**
     * @param inputStream ein "ObjectInputStream"
     * @return der Lehrer, der aus der Datei gelesen wurde
     * @throws IOException  falls der Lehrer nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls der Lehrer nicht aus der Datei gelesen wurde
     */
    public Object controller_readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return lehrerFileRepository.readObject(inputStream);
    }

    /**
     *
     * @param id das Id eines Lehrers aus der Liste "Lehrer"
     * @throws IOException falls das Object nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls das Object nicht aus der Datei gelesen wurde
     */
    public void controller_findOne(int id) throws IOException, ClassNotFoundException {
        lehrerFileRepository.findOne(id);
    }

    /**
     *
     * @return eine Liste mit allen Lehrer
     * @throws IOException falls man nicht aus der Datei lesen kann
     * @throws ClassNotFoundException falls man nicht aus der Datei lesen kann
     */
    public List<Lehrer> controller_findAll() throws IOException, ClassNotFoundException {
        return lehrerFileRepository.findAll();
    }

    /**
     *
     * @param Vorname ein String
     * @param Nachname ein String
     * @param Id eine "long" Zahl
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    public void controller_save(String Vorname, String Nachname, Long Id) throws IOException {
        lehrerFileRepository.save(new Lehrer(Vorname, Nachname, Id));
    }

    /**
     *
     * @param Id eine "long" Zahl
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    public void controller_delete(Long Id) throws IOException {
        Lehrer lehrerToDelete = null;
        for (Lehrer lehrer: lehrerFileRepository.getRepositoryList()) {
            if (lehrer.getLehrerID() == Id){
                lehrerToDelete = new Lehrer(lehrer.getVorname(), lehrer.getNachname(), Id);
            }
        }
        lehrerFileRepository.delete(lehrerToDelete);
    }

    /**
     *
     * @param entity ein Objekt von Typ "Lehrer"
     * @throws IOException falls man nicht in der Datei schreiben kann
     * @throws ClassNotFoundException falls man nicht in der Datei schreiben kann
     */
    public void controller_update(Lehrer entity) throws IOException, ClassNotFoundException {
        lehrerFileRepository.update(entity);
    }
}
