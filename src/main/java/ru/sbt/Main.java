package ru.sbt;

import ru.sbt.dao.*;
import ru.sbt.models.Lesson;
import ru.sbt.models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:~/test;INIT=runscript from 'src/main/resources/db/initDB.sql'\\;runscript from 'src/main/resources/db/populateDB.sql'";
        try (Connection connection = DriverManager.getConnection(url)) {

            StudentDao studentDao = new StudentDaoImpl(connection);

            Student bob = new Student("Bob", "123");
            Student alex = new Student("Alex", "42");
            Student alice = new Student("Alice", "123");
            studentDao.create(bob);
            studentDao.create(alex);
            studentDao.create(alice);

            List<Student> students = studentDao.getAll();


            List<String> stringDates = Arrays.asList("2016-10-01 11:00:00", "2016-10-05 18:30:00", "2016-10-07 11:00:00",
                    "2016-10-07 11:00:00", "2016-10-12 18:30:00", "2016-10-15 11:00:00", "2016-10-19 18:30:00",
                    "2016-10-22 11:00:00", "2016-10-26 18:30:00", "2016-10-29 11:00:00");

            VisitDao visitDao = new VisitDaoImpl(connection);

            LessonDao lessonDao = new LessonDaoImpl(connection);
            int i = 1;
            for (String stringDate : stringDates) {
                Lesson lesson = new Lesson(getDate(stringDate), "JAVA");
                lessonDao.create(lesson);

                visitDao.saveVisit(bob, lesson);
                if (i % 2 == 0) {
                    visitDao.saveVisit(alex, lesson);
                }
                if (i % 3 == 0) {
                    visitDao.saveVisit(alice, lesson);
                }
                i++;
            }

            System.out.println("Список посещаемости по дням");
            Map<Lesson, List<Student>> visitList = visitDao.getListOfVisitsByLesson();
            printVisitList(visitList);

            System.out.println("Лекции которые посещали студенты:");
            Map<Student, List<Lesson>> visitListByStudent = visitDao.getListOfVisitsByStudent();
            printVisitList(visitListByStudent);
        }
    }

    private static <T,K> void printVisitList(Map<T, List<K>> visitList) {
        for (Map.Entry<T, List<K>> entry : visitList.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("    " + entry.getValue());
        }
    }

    private static java.util.Date getDate(String date) {

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
