package ru.sbt.dao;

import ru.sbt.models.Lesson;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LessonDaoImpl implements LessonDao {

    private final Connection connection;

    public LessonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Lesson newLesson) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Lessons (id, date, subject) values(?, ? , ?)");

            statement.setObject(1, newLesson.getId());
            statement.setDate(2, new Date(newLesson.getDate().getTime()));
            statement.setString(3, newLesson.getSubject());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
