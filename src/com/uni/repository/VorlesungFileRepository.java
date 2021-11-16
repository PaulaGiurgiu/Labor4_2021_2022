package com.uni.repository;

import com.uni.model.Vorlesung;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VorlesungFileRepository implements CrudRepository<Vorlesung> {
    protected ObjectOutputStream objectOutputStream;
    protected FileOutputStream fileOutputStream;
    protected ObjectInputStream objectInputStream;
    protected FileInputStream fileInputStream;
    protected String fileName;
    protected List<Vorlesung> repoList;

    public VorlesungFileRepository(){
        super();
        this.fileName = "src//vorlesung.ser";
        repoList = new ArrayList<>();
    }

    public VorlesungFileRepository(String fileName) throws IOException {
        super();
        this.fileName = fileName;
        repoList = new ArrayList<>();
        this.fileOutputStream = new FileOutputStream(fileName);
        this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
        this.fileInputStream = new FileInputStream(fileName);
        this.objectInputStream = new ObjectInputStream(fileInputStream);
    }

    /**
     * @return eine Liste mit allen Vorlesungen
     */
    public List<Vorlesung> getRepositoryList() {
        return repoList;
    }

    /**
     * @param outputStream ein "ObjectOutputStream"
     * @param entity die Vorlesung, die man in der Datei schreiben will
     * @throws IOException falls die Vorlesung nicht in der Datei geschrieben wurde
     */
    public void writeObject(ObjectOutputStream outputStream, Vorlesung entity) throws IOException {
        outputStream.writeObject(entity);
    }

    /**
     * @param inputStream ein "ObjectInputStream"
     * @return die Vorlesung, die aus der Datei gelesen wurde
     * @throws IOException  falls die Vorlesung nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls die Vorlesung nicht aus der Datei gelesen wurde
     */
    public Object readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Vorlesung entity;
        entity = (Vorlesung) inputStream.readObject();

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
    public Vorlesung findOne(int id) throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Vorlesung student : repoList) {
                writeObject(objectOutputStream, student);
            }
        }

        List<Vorlesung> list = new ArrayList<>();
        while (fileInputStream.available() > 0) {
            Vorlesung entity = (Vorlesung) objectInputStream.readObject();
            if (entity.getVorlesungID() == id) {
                list.add(entity);
            }

        }
        return list.get(list.size() - 1);
    }

    /**
     *
     * @return eine Liste mit allen Vorlesungen
     * @throws IOException falls man nicht aus der Datei lesen kann
     * @throws ClassNotFoundException falls man nicht aus der Datei lesen kann
     */
    @Override
    public List<Vorlesung> findAll() throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Vorlesung vorlesung : repoList) {
                writeObject(objectOutputStream, vorlesung);
            }
        }

        return repoList;
    }

    /**
     *
     * @param entity ein Objekt von Typ "Vorlesung"
     * @return das neue eingefügte Object
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    @Override
    public Vorlesung save(Vorlesung entity) throws IOException {
        repoList.add(entity);
        if (fileInputStream.available() <= 0)
        {
            for (Vorlesung vorlesung : repoList) {
                writeObject(objectOutputStream, vorlesung);
            }
        }
        return entity;
    }

    /**
     *
     * @param entity ein Objekt von Typ "Vorlesung"
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    @Override
    public void delete(Vorlesung entity) throws IOException {
        if (fileInputStream.available() <= 0)
        {
            for (Vorlesung vorlesung : repoList) {
                writeObject(objectOutputStream, vorlesung);
            }
        }
        repoList.remove(entity);
    }

    /**
     *
     * @param entity ein Objekt von Typ "Vorlesung"
     * @return das neue aktualisierte Object
     * @throws IOException falls man nicht in der Datei schreiben kann
     * @throws ClassNotFoundException falls man nicht in der Datei schreiben kann
     */
    @Override
    public Vorlesung update(Vorlesung entity) throws IOException, ClassNotFoundException {
        Vorlesung vorlesungToUpdate = this.repoList.stream()
                .filter(vorlesung -> vorlesung.getVorlesungID() == entity.getVorlesungID())
                .findFirst()
                .orElseThrow();

        vorlesungToUpdate.setName(entity.getName());
        vorlesungToUpdate.setLehrer(entity.getLehrer());
        vorlesungToUpdate.setMaxEnrollment(entity.getMaxEnrollment());
        vorlesungToUpdate.setStudentsEnrolled(entity.getStudentsEnrolled());
        vorlesungToUpdate.setCredits(entity.getCredits());

        if (fileInputStream.available() <= 0)
        {
            for (Vorlesung vorlesung : repoList) {
                writeObject(objectOutputStream, vorlesung);
            }
        }

        return vorlesungToUpdate;
    }
}
