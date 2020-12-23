package com.yaroslav;

import java.time.LocalDate;
import java.time.Period;

public class Employee {
    private Integer id;
    private String name;
    private LocalDate birthday;

    public Employee(String name, LocalDate birthday) {
        this.setName(name);
        this.setBirthday(birthday);
    }

    public Employee(Integer id, String name, LocalDate birthday) {
        this(name, birthday);
        this.setId(id);
    }

    public int getAge() {
        return this.getAge(LocalDate.now());
    }

    public int getAge(LocalDate relativeDate) {
        return Period.between(getBirthday(), relativeDate).getYears();
    }

    public LocalDate getNextBirthday() {
        return getNextBirthday(LocalDate.now());
    }

    public LocalDate getNextBirthday(LocalDate relativeDate) {
        var birthday = LocalDate.of(
                relativeDate.getYear(),
                this.getBirthday().getMonth(),
                this.getBirthday().getDayOfMonth());

        if (relativeDate.compareTo(birthday) > 0)
            birthday = birthday.plusYears(1);

        return birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
