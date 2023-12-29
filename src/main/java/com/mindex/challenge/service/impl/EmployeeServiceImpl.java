package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public ReportingStructure getReportingStructure(String id) {
        LOG.debug("Getting reporting structure for id [{}]", id);

        Employee employee = read(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(calculateDirectReports(employee));

        return reportingStructure;
    }

    public Integer calculateDirectReports(Employee employee) {
        int reports = 0;
        List<Employee> tempList = new ArrayList<>();

        if (employee.getDirectReports() != null) {
            reports += employee.getDirectReports().size();

            for (Employee directReport : employee.getDirectReports()) {
                directReport = read(directReport.getEmployeeId());
                tempList.add(directReport);
                reports += calculateDirectReports(directReport);
            }
        }

        employee.setDirectReports(tempList);

        return reports;
    }











    @Override
    public Compensation getEmployeeCompensation(String id) {
        return null;
    }

    @Override
    public Compensation createEmployeeCompensation(Compensation compensation) {

        return null;
    }
}
