package com.yaroslav;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    Employee e1, e2, e3, e4;
    Collection<Employee> employees;
    EmployeeService svc;
    LocalDate relativeDate;

    @org.junit.jupiter.api.BeforeEach
    void startup() {
        svc = new EmployeeService();
        relativeDate = LocalDate.of(2020, 12, 15);
        e1 = new Employee("Vasyl", LocalDate.of(1994, 12, 18));
        e2 = new Employee("Vitaliy", LocalDate.of(1997, 12, 24));
        e3 = new Employee("Yaroslav", LocalDate.of(2002, 01, 15));
        e4 = new Employee("Igor", LocalDate.of(1990, 2, 11));
        employees = List.of(e1, e2, e3, e4);
    }

    @org.junit.jupiter.api.Test
    void sort__Test1() {
        var sort1 = svc.sortByBirthday(employees, 0, relativeDate);

        assertEquals(1, sort1.size());
        var hashset1 = sort1.values().iterator().next();
        assertTrue(hashset1.contains(e1));
        assertTrue(hashset1.contains(e2));
    }
}