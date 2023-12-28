package com.mindex.challenge.data;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;
    @Autowired
    private EmployeeService employeeService;
    public ReportingStructure() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getNumberOfReports(Employee tempEmployee) { return numberOfReports; }

    public void setNumberOfReports(Integer numberOfReports) { this.numberOfReports = numberOfReports; }
}
