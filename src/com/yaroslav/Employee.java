package com.yaroslav;

import java.time.LocalDate;
import java.time.Period;

public class Employee {
    public String name;
    public LocalDate birthday;

    public Employee(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }
}
