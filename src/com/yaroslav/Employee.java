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
        return this.getAge(LocalDate.now());
    }

    public int getAge(LocalDate relativeDate) {
        return Period.between(birthday, relativeDate).getYears();
    }

    public LocalDate getNextBirthday() {
        return getNextBirthday(LocalDate.now());
    }

    public LocalDate getNextBirthday(LocalDate relativeDate) {
        return birthday; // FIXME
    }
}
