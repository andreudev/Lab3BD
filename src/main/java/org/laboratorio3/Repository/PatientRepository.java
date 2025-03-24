package org.laboratorio3.Repository;

import java.util.List;

public interface PatientRepository <T>{
    void create(T t);
    void update(T t);
    void delete(Long id);
    T read(T t);
    T readById(int id);
    List<T> readAll();
}
