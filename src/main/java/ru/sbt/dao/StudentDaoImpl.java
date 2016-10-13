package ru.sbt.dao;

import ru.sbt.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentDaoImpl implements StudentDao {
    private final Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
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

    @Override
    public List<Student> getAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("select id, name, department from Students");

            ResultSet resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                UUID id = (UUID)resultSet.getObject("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");

                students.add(new Student(id, name, department));
            }

            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public Student findById(UUID id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("insert into Students (id, name, department) values(?, ? , ?)");
//
//            statement.setObject(1, newInstance.getId());
//            statement.setString(2, newInstance.getName());
//            statement.setString(3, newInstance.getDepartment());
//
//            statement.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
