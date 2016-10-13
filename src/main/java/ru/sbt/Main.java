package ru.sbt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:mem:test;INIT=runscript from 'src/main/resources/db/initDB.sql'\\;runscript from 'src/main/resources/db/populateDB.sql'";
        try (Connection connection = DriverManager.getConnection(url)) {

            int a = 0;
        }
    }

}
