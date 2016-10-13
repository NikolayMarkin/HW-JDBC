DROP TABLE IF EXISTS Student_lesson;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Lessons;

CREATE TABLE Students (
    id  UUID PRIMARY KEY,
    name VARCHAR(128),
    department VARCHAR(128)
);

CREATE TABLE Lessons (
    id UUID PRIMARY KEY,
    date TIMESTAMP,
    subject VARCHAR(128)
);

CREATE TABLE Student_lesson (
    student_id UUID,
    lesson_id UUID,
    FOREIGN KEY(student_id) REFERENCES Students(id),
    FOREIGN KEY(lesson_id) REFERENCES Lessons(id)
)