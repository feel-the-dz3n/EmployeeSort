package com.yaroslav;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;

public class Main {
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

    public static String getMonthName(Month month) {
        switch (month) {
            case JANUARY:
                return "Январь";
            case FEBRUARY:
                return "Ферваль";
            case MARCH:
                return "Март";
            case APRIL:
                return "Апрель";
            case MAY:
                return "Май";
            case JUNE:
                return "Июнь";
            case JULY:
                return "Июль";
            case AUGUST:
                return "Август";
            case SEPTEMBER:
                return "Сентябрь";
            case OCTOBER:
                return "Октябрь";
            case NOVEMBER:
                return "Ноябрь";
            case DECEMBER:
                return "Декабрь";
            default:
                return month.toString();
        }
    }

    public static void printYearMonthRow(YearMonth ym) {
        System.out.printf("%s %d\n", getMonthName(ym.getMonth()), ym.getYear());
    }

    public static void printEmployeeRow(int n, Employee e) {
        System.out.printf(
                "(%02d) - %s (%s)\n",
                n,
                e.getName(),
                toPluralForm(e.getAge() + 1, "год", "года", "лет"));
    }

    public static void main(String[] args) {
        var relativeDate = LocalDate.of(2020, 12, 15);
        var svc = new EmployeeService();
        Collection<Employee> employees = null;

        try {
            employees = new EmployeeRepository(
                    new DatabaseInformation(
                            "org.postgresql.Driver",
                            "jdbc:postgresql://127.0.0.1:5432/",
                            "postgres",
                            "123456"))
                    .getEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                    printEmployeeRow(celebrant.getId(), celebrant);
                }
            } else {
                System.out.println("Пусто");
            }

            System.out.println();
        }
    }
}
