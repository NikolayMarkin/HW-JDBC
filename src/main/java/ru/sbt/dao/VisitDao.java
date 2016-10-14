package ru.sbt.dao;

import ru.sbt.models.Lesson;
import ru.sbt.models.Student;

import java.util.List;
import java.util.Map;

public interface VisitDao {
    void saveVisit(Student student, Lesson lesson);

    Map<Lesson, List<Student>> getListOfVisitsByLesson();

    Map<Student, List<Lesson>> getListOfVisitsByStudent();
}