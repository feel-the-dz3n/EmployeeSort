package com.yaroslav;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {
    static List<Employee> employees = List.of(
            new Employee("Yaroslav Kibysh", LocalDate.of(2002, 01, 15)),
            new Employee("Pavlo Kavun", LocalDate.of(2002, 02, 28)),
            new Employee("Sergey Perevertkin", LocalDate.of(2000, 02, 2)),
            new Employee("Vasya Pupkin", LocalDate.of(1998, 12, 16)),
            new Employee("Viktor Popov", LocalDate.of(1994, 01, 5)),
            new Employee("Vitaliy Petrov", LocalDate.of(1998, 12, 24))
    );

    public static String toPluralForm(int n, String one, String few, String many) {
        String word;
        int nMod = Math.abs(n);

        if (nMod % 100 != 11 && nMod % 10 == 1) word = one; // not ends with 11 and ends with 1
        else if ((nMod % 100 < 10 || nMod % 100 >= 20)
                && (nMod % 10 >= 2 && nMod % 10 < 5))
            word = few; // two last digits are < 10 & >= 20, last digit >= 2 & < 5
        else word = many; // other cases

        return n + " " + word;
    }

    public static void printYearMonthRow(YearMonth ym) {
        System.out.println(ym); // TODO: correct date formatting
    }

    public static void printEmployeeRow(int n, Employee e) {
        System.out.printf(
                "(%d) - %s (%s)\n",
                n,
                e.name,
                toPluralForm(e.getAge() + 1, "год", "года", "лет"));
    }

    public static void main(String[] args) {
        var relativeDate = LocalDate.of(2020, 12, 15);
        var svc = new EmployeeService();

        var celebrantsMap = svc.sortByBirthday(
                employees,
                2,
                relativeDate);

        printCelebrantsMap(celebrantsMap);
    }

    public static void printCelebrantsMap(HashMap<YearMonth, HashSet<Employee>> celebrantsMap) {
        // We're about to iterate through all keys (YearMonth 'es)
        var iterator = celebrantsMap.keySet().iterator();

        while (iterator.hasNext()) {
            YearMonth yearMonth = iterator.next();

            // Get celebrants for current year/month by key
            HashSet<Employee> celebrants = celebrantsMap.get(yearMonth);

            printYearMonthRow(yearMonth);

            if (celebrants.size() >= 1) {
                for (var celebrant : celebrants) {
                    printEmployeeRow(employees.indexOf(celebrant), celebrant);
                }
            } else {
                System.out.println("Пусто");
            }

            System.out.println();
        }
    }
}
