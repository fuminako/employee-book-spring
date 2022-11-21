package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("TestO", "TestO", 1, 5000),
                new EmployeeRequest("TestS", "TestS", 2, 4000),
                new EmployeeRequest("TestT", "TestT", 1, 2000),
                new EmployeeRequest("TestF", "TestF", 3, 6000)
        ).forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployeeTest() {
        EmployeeRequest request = new EmployeeRequest("One", "One", 1, 5000);
        Employee result = employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), result.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());
        assertThat(employeeService.getAllEmployees()).contains(result);
    }

    @Test
    public void sumOfSalariesTest() {
        int sum = employeeService.getSalarySum();
        assertThat(sum).isEqualTo(17000);
    }

    @Test
    public void averageSalaryTest() {
        double average = employeeService.getAverageS();
        assertThat(average).isEqualTo(4250);
    }

    @Test
    public void salaryAboveAverageTest() {
        Collection<Employee> employees = employeeService.getSalaryAboveAverage();
        assertThat(employees).hasSize(2);
    }

    @Test
    public void employeesWithMinSalaryTest() {
        Employee employee = employeeService.getMinSalary();
        assertThat(employee).isNotNull().extracting(Employee::getFirstName).isEqualTo("TestT");
    }

    @Test
    public void employeesWithMaxSalaryTest() {
        Employee employee = employeeService.getMaxSalary();
        assertThat(employee).isNotNull().extracting(Employee::getFirstName).isEqualTo("TestT");
    }

    @Test
    public void removeEmployeeTest() {
        employeeService.removeEmployee(0);
        Collection<Employee> employees = employeeService.getAllEmployees();
        assertThat(employees).hasSize(3);
    }
}

