package com.yaroslav;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashSet;

public class Database {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "123456";

    Connection conn;
    Statement stmt;

    private void connect() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private void disconnect() throws SQLException {
        conn.close();
    }

    public Collection<Employee> getEmployees() throws Exception {
        HashSet<Employee> employees = new HashSet<>();
        connect();

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.employees");

        while (rs.next()) {
            employees.add(new Employee(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    convertToLocalDateViaSqlDate(rs.getDate("Birthdate"))
            ));
        }

        stmt.close();
        disconnect();

        return employees;
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}
