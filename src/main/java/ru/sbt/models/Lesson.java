package ru.sbt.models;

import java.util.Date;
import java.util.UUID;

public class Lesson {
    private final UUID id;
    private final Date date;
    private final String subject;

    public Lesson(Date date, String subject) {
        this(UUID.randomUUID(), date, subject);
    }

    public Lesson(UUID id, Date date, String subject) {
        this.id = id;
        this.date = date;
        this.subject = subject;
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }
}
