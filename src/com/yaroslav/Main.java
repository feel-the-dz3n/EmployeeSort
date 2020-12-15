package com.yaroslav;

import java.time.LocalDate;

public class Main {

    public static Employee[] getEmps() {
        return new Employee[]{
                new Employee("Yaroslav Kibysh", LocalDate.of(2002, 01, 15)),
                new Employee("Pavlo Kavun", LocalDate.of(2002, 02, 28)),
                new Employee("Sergey Perevertkin", LocalDate.of(2000, 02, 2)),
                new Employee("Vasya Pupkin", LocalDate.of(1998, 12, 16)),
                new Employee("Viktor Popov", LocalDate.of(1994, 01, 5)),
                new Employee("Vitaliy Petrov", LocalDate.of(1998, 12, 24)),
        };
    }

    public static void main(String[] args) {

    }
}
