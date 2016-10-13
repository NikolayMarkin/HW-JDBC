package ru.sbt.dao;

import ru.sbt.models.Student;


import java.sql.Connection;
import java.util.UUID;

public class StudentDao {
    private final Connection connection;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Student newInstance) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Students (id, name, department) values(?, ? , ?)");

            statement.setObject(1, newInstance.getId());
            statement.setString(2, newInstance.getName());
            statement.setString(3, newInstance.getDepartment());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Student findById(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Students (id, name, department) values(?, ? , ?)");

            statement.setObject(1, newInstance.getId());
            statement.setString(2, newInstance.getName());
            statement.setString(3, newInstance.getDepartment());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
