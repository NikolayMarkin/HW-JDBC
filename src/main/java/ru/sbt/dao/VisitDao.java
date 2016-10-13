package ru.sbt.dao;

import ru.sbt.models.Lesson;
import ru.sbt.models.Student;

import java.util.List;
import java.util.Map;

public interface VisitDao {
    void saveVisit(Student student, Lesson lesson);

    Map<Lesson, Student> getListOfVisitsByLesson();

    List<Student> getListOfVisitsByLesson(Lesson lesson);

    Map<Student, Lesson> getListOfVisitsByStudent();

    List<Lesson> getListOfVisitsByStudent(Student student);
}