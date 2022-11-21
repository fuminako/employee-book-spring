package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import com.skypro.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private final List<Employee> employees = List.of(
            new Employee("TestO", "TestO", 1, 5000),
            new Employee("TestS", "TestS", 2, 4000),
            new Employee("TestT", "TestT", 1, 2000),
            new Employee("TestF", "TestF", 3, 6000));

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
    }

    @Test
    void getEmployeesByDepartmentTest() {
        Collection<Employee> collection = this.departmentService.getDepartmentEmployee(1);
        assertThat(collection).hasSize(2).contains(employees.get(0), employees.get(2));
    }

    @Test
    void getDepartmentSalarySumTest() {
        int sum = this.departmentService.getDepartmentSalarySum(1);
        assertThat(sum).isEqualTo(7000);
    }

    @Test
    void maxSalaryInDepartmentTest() {
        int max = this.departmentService.getMaxSalaryInDepartment(1);
        assertThat(max).isEqualTo(5000);
    }

    @Test
    void minSalaryInDepartmentTest() {
        int max = this.departmentService.getMinSalaryInDepartment(1);
        assertThat(max).isEqualTo(2000);
    }

}
