package com.skypro.employee.service;

import com.skypro.employee.model.Employee;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Collection<Employee> getDepartmentEmployee(int department) {
        return getEmployeesByDepartment(department).collect(Collectors.toList());
    }

    public int getDepartmentSalarySum(int department) {
        return getEmployeesByDepartment(department).mapToInt(Employee::getSalary).sum();
    }

    public int getMaxSalaryInDepartment(int department){
        return getEmployeesByDepartment(department).mapToInt(Employee::getSalary).max().orElseThrow(RuntimeException::new);
    }

    public int getMinSalaryInDepartment(int department){
        return getEmployeesByDepartment(department).mapToInt(Employee::getSalary).min().orElseThrow(RuntimeException::new);
    }

    public Stream<Employee> getEmployeesByDepartment(int department) {
        return employeeService.getAllEmployees().stream().filter(e -> e.getDepartment() == department);
    }
}
