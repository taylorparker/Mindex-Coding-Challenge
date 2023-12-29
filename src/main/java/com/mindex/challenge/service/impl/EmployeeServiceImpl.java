package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompensationRepository compensationRepository;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found for employeeId: " + id);
        }

        calculateDirectReports(employee);

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found for employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(calculateDirectReports(employee));

        return reportingStructure;
    }

    public Integer calculateDirectReports(Employee employee) {
        int reports = 0;
        List<Employee> listOfDirectReports = new ArrayList<>();

        if (employee.getDirectReports() != null) {
            reports += employee.getDirectReports().size();

            for (Employee directReport : employee.getDirectReports()) {
                directReport = read(directReport.getEmployeeId());
                reports += calculateDirectReports(directReport);
                listOfDirectReports.add(directReport);
            }
        }

        employee.setDirectReports(listOfDirectReports);

        return reports;
    }

    @Override
    public Compensation createEmployeeCompensation(Compensation compensation) {
        LOG.debug("Creating Compensation [{}]", compensation.toString());

        return compensationRepository.save(compensation);
    }

    @Override
    public List<Compensation> getEmployeeCompensation(String id) {

        List<Compensation> compensation = compensationRepository.findByEmployeeId(id);

        if (compensation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No compensation found for employee id: " + id);
        }
        return compensation;
    }


}
