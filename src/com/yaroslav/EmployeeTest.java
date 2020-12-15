package com.yaroslav;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    Employee e1;

    @BeforeEach
    void setUp() {
        e1 = new Employee("Yaroslav", LocalDate.of(2002, 01, 15));
    }

    @Test
    void getAge() {
        assertEquals(18, e1.getAge(LocalDate.of(2021, 01, 14)));
        assertEquals(19, e1.getAge(LocalDate.of(2021, 01, 15)));
        assertEquals(17, e1.getAge(LocalDate.of(2020, 01, 10)));
    }

    @Test
    void getNextBirthday() {
        var nextDate19_1 = e1.getNextBirthday(LocalDate.of(2020, 12, 15));
        var nextDate19_2 = e1.getNextBirthday(LocalDate.of(2021, 1, 14));
        var nextDate20 = e1.getNextBirthday(LocalDate.of(2021, 1, 15));

        assertEquals(2021, nextDate19_1.getYear());
        assertEquals(01, nextDate19_1.getMonth());
        assertEquals(15, nextDate19_1.getDayOfMonth());

        assertEquals(2021, nextDate19_2.getYear());
        assertEquals(01, nextDate19_2.getMonth());
        assertEquals(15, nextDate19_2.getDayOfMonth());

        assertEquals(2022, nextDate20.getYear());
        assertEquals(01, nextDate20.getMonth());
        assertEquals(15, nextDate20.getDayOfMonth());
    }
}