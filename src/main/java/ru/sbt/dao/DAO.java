package ru.sbt.dao;

import java.io.Serializable;

public interface DAO<T, PK extends Serializable> {
    void create(T newInstance);

    T findById(PK id);

    void update(T instance);

    void delete(T instance);

}
