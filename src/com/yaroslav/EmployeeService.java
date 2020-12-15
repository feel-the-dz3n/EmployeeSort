package com.yaroslav;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EmployeeService {
    public HashMap<YearMonth, HashSet<Employee>> sortByBirthday(
            Collection<Employee> employees,
            Integer monthCount) {
        return sortByBirthday(employees, monthCount, LocalDate.now());
    }

    public HashMap<YearMonth, HashSet<Employee>> sortByBirthday(
            Collection<Employee> employees,
            Integer monthCount,
            LocalDate relativeDate) {
        HashMap<YearMonth, HashSet<Employee>> map = new HashMap<>();

        // Iterating through each month
        for (int i = 0; i <= monthCount; i++) {
            // Adding current month iteration to relative date
            LocalDate calculatedDate = relativeDate.plusMonths(i);

            // Suitable employees for current y/m will be stored here
            HashSet<Employee> localEmployees = new HashSet<>();

            // Iterating through employees
            for (var employee : employees) {
                var nextBirthday = employee.getNextBirthday(relativeDate);

                // Checking if employee's next birthday will be
                // in the same year/month of previously calculated date
                if (nextBirthday.getYear() == calculatedDate.getYear()
                        && nextBirthday.getMonth() == calculatedDate.getMonth()) {
                    // OK, so put the information to our hashset
                    localEmployees.add(employee);
                }
            }

            // Store y/m and employees information in our hashmap
            map.put(YearMonth.of(calculatedDate.getYear(), calculatedDate.getMonth()),
                    localEmployees);
        }

        return map;
    }
}
