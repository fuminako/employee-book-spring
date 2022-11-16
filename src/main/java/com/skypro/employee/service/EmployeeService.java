package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Введите ФИ");
        }
        Employee employee = new Employee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream().mapToInt(Employee::getSalary).sum();
    }

    public Employee getMinSalary() {
        return employees.values().stream().min(Comparator.comparingInt(Employee::getSalary)).get();
    }

    public Employee getMaxSalary() {
        return employees.values().stream().max(Comparator.comparingInt(Employee::getSalary)).get();
    }

    public double getAverageS() {
        return employees.values().stream().mapToInt(Employee::getSalary).average().getAsDouble();
    }
    public Collection<Employee> getSalaryAboveAverage() {
        return employees.values().stream().filter(e -> e.getSalary() > getAverageS()).toList();
    }
}