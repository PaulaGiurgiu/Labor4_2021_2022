package com.uni.repository;

import com.uni.model.Lehrer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LehrerFileRepository implements CrudRepository<Lehrer>{
    protected ObjectOutputStream objectOutputStream;
    protected FileOutputStream fileOutputStream;
    protected ObjectInputStream objectInputStream;
    protected FileInputStream fileInputStream;
    protected String fileName;
    protected List<Lehrer> repoList;

    public LehrerFileRepository(){
        super();
        this.fileName = "src//lehrer.ser";
        repoList = new ArrayList<>();
    }

    public LehrerFileRepository(String fileName) throws IOException {
        super();
        this.fileName = fileName;
        repoList = new ArrayList<>();
        this.fileOutputStream = new FileOutputStream(fileName);
        this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
        this.fileInputStream = new FileInputStream(fileName);
        this.objectInputStream = new ObjectInputStream(fileInputStream);
    }

    /**
     * @return eine Liste mit allen Lehrer
     */
    public List<Lehrer> getRepositoryList() {
        return repoList;
    }

    /**
     * @param outputStream ein "ObjectOutputStream"
     * @param entity der Lehrer, der man in der Datei schreiben will
     * @throws IOException falls der Lehrer nicht in der Datei geschrieben wurde
     */
    public void writeObject(ObjectOutputStream outputStream, Lehrer entity) throws IOException {
        outputStream.writeObject(entity);
    }

    /**
     * @param inputStream ein "ObjectInputStream"
     * @return der Lehrer, der aus der Datei gelesen wurde
     * @throws IOException  falls der Lehrer nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls der Lehrer nicht aus der Datei gelesen wurde
     */
    public Object readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Lehrer entity;
        entity = (Lehrer) inputStream.readObject();

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
    public Lehrer findOne(int id) throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer: repoList) {
                writeObject(objectOutputStream, lehrer);
            }
        }

        List<Lehrer> list = new ArrayList<>();
        while (fileInputStream.available() > 0) {
            Lehrer entity = (Lehrer) objectInputStream.readObject();
            if (entity.getLehrerID() == id) {
                list.add(entity);
            }

        }
        return list.get(list.size() - 1);
    }

    /**
     *
     * @return eine Liste mit allen Lehrer
     * @throws IOException falls man nicht aus der Datei lesen kann
     * @throws ClassNotFoundException falls man nicht aus der Datei lesen kann
     */
    @Override
    public List<Lehrer> findAll() throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer: repoList) {
                writeObject(objectOutputStream, lehrer);
            }
        }

        return repoList;
    }

    /**
     *
     * @param entity ein Objekt von Typ "Lehrer"
     * @return das neue eingefügte Object
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    @Override
    public Lehrer save(Lehrer entity) throws IOException {
        repoList.add(entity);
        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer : repoList) {
                writeObject(objectOutputStream, lehrer);
            }
        }
        return entity;
    }

    /**
     *
     * @param entity ein Objekt von Typ "Lehrer"
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    @Override
    public void delete(Lehrer entity) throws IOException {
        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer : repoList) {
                writeObject(objectOutputStream, lehrer);
            }
        }
        repoList.remove(entity);
    }

    /**
     *
     * @param entity ein Objekt von Typ "Lehrer"
     * @return das neue aktualisierte Object
     * @throws IOException falls man nicht in der Datei schreiben kann
     * @throws ClassNotFoundException falls man nicht in der Datei schreiben kann
     */
    @Override
    public Lehrer update(Lehrer entity) throws IOException, ClassNotFoundException {
        Lehrer lehrerToUpdate = this.repoList.stream()
                .filter(lehrer -> lehrer.getLehrerID() == entity.getLehrerID())
                .findFirst()
                .orElseThrow();

        lehrerToUpdate.setVorname(entity.getVorname());
        lehrerToUpdate.setNachname(entity.getNachname());
        lehrerToUpdate.setVorlesungen(entity.getVorlesungen());

        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer : repoList) {
                writeObject(objectOutputStream, lehrer);
            }
        }

        return lehrerToUpdate;
    }

}
