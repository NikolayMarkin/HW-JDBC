package ru.sbt.dao;

import ru.sbt.models.Student;

import java.util.List;

public interface StudentDao {
    void create(Student newInstance);
    List<Student> getAll();
}
