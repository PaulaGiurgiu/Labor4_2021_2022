package com.uni.repository;

import com.uni.exception.DeleteVorlesungFromLehrerException;

import java.io.IOException;
import java.util.List;

public interface CrudRepository<T> {
    T findOne(int id) throws DeleteVorlesungFromLehrerException, IOException, ClassNotFoundException;
    List<T> findAll() throws DeleteVorlesungFromLehrerException, IOException, ClassNotFoundException;
    T save(T entity) throws IOException;
    void delete(T entity) throws DeleteVorlesungFromLehrerException, IOException, ClassNotFoundException;
    T update(T entity) throws DeleteVorlesungFromLehrerException, IOException, ClassNotFoundException;

}
