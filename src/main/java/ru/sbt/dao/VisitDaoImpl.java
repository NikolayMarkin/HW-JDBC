package ru.sbt.dao;

import javafx.util.Pair;
import ru.sbt.models.Lesson;
import ru.sbt.models.Student;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class VisitDaoImpl implements VisitDao {

    private final Connection connection;

    public VisitDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveVisit(Student student, Lesson lesson) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Student_lesson (student_id, lesson_id) values(?, ?)");

            statement.setObject(1, student.getId());
            statement.setObject(2, lesson.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Map<Lesson, List<Student>> getListOfVisitsByLesson() {
        Map<Lesson, List<Student>> map = new TreeMap<>();

        List<Pair<Student, Lesson>> rawList = getRawList();

        for (Pair<Student, Lesson> pair : rawList) {
            Student student = pair.getKey();
            Lesson lesson = pair.getValue();

            putPairInMap(lesson, student, map);
        }

        return map;
    }

    @Override
    public Map<Student, List<Lesson>> getListOfVisitsByStudent() {
        Map<Student, List<Lesson>> map = new HashMap<>();

        List<Pair<Student, Lesson>> rawList = getRawList();

        for (Pair<Student, Lesson> pair : rawList) {
            Student student = pair.getKey();
            Lesson lesson = pair.getValue();

            putPairInMap(student, lesson, map);
        }

        return map;
    }

    private List<Pair<Student, Lesson>> getRawList() {
        List<Pair<Student, Lesson>> list = new ArrayList<>();
        try {
            String sql = "select s.id as s_id, s.name, s.department, l.id as l_id, l.date, l.subject from Lessons l " +
                    "join Student_lesson v on l.id = v.lesson_id " +
                    "join Students s on s.id = v.student_id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID studentId = (UUID)resultSet.getObject("s_id");
                String studentName = resultSet.getString("name");
                String department = resultSet.getString("department");
                UUID lessonId = (UUID)resultSet.getObject("l_id");
                Date date = resultSet.getDate("date");
                String subject = resultSet.getString("subject");

                Student student = new Student(studentId, studentName, department);
                Lesson lesson = new Lesson(lessonId, date, subject);

                list.add(new Pair<>(student, lesson));
            }

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private <K, V> void putPairInMap(K key, V value, Map<K, List<V>> map) {
        if (map.containsKey(key)){
            List<V> list = map.get(key);
            list.add(value);
        } else {
            List<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        }
    }

}
