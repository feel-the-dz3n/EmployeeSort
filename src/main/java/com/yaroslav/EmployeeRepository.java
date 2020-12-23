package com.yaroslav;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class EmployeeRepository {
    private DatabaseInformation database;

    public EmployeeRepository(DatabaseInformation database) {
        this.database = database;
    }

    public Collection<Employee> getEmployees() throws Exception {
        HashSet<Employee> employees = new HashSet<>();
        var conn = database.connect();

        var stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.employees");

        while (rs.next()) {
            employees.add(new Employee(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    convertToLocalDateViaSqlDate(rs.getDate("Birthdate"))
            ));
        }

        stmt.close();
        conn.close();

        return employees;
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}
