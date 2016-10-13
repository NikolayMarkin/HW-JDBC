package ru.sbt.models;

import java.util.UUID;

public class Student {

    private final UUID id;
    private final String name;
    private final String department;

    public Student(String name, String department) {
        this(UUID.randomUUID(), name, department);
    }

    public Student(UUID id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
