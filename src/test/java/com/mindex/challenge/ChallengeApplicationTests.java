package com.mindex.challenge;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private CompensationRepository compensationRepository;
	@Autowired
	private EmployeeService employeeService;

	@Test
	public void employeeTest() {
		Employee employee = employeeRepository.findByEmployeeId("b7839309-3348-463b-a7e3-5de1c168beb3");
		assertNotNull(employee);
		assertEquals("Paul", employee.getFirstName());
		assertEquals("McCartney", employee.getLastName());
		assertEquals("Developer I", employee.getPosition());
		assertEquals("Engineering", employee.getDepartment());
	}

	@Test
	public void compensationTest() {
		Compensation compensation = new Compensation();
		compensation.setEmployeeID("b7839309-3348-463b-a7e3-5de1c168beb3");
		compensation.setSalary(80000.00);
		compensation.setEffectiveDate("12-29-2023");

		compensationRepository.save(compensation);

		Compensation fetchedCompensation = compensationRepository.findByEmployeeId("b7839309-3348-463b-a7e3-5de1c168beb3");
		assertNotNull(fetchedCompensation);
		assertEquals(80000.00, fetchedCompensation.getSalary(), 0.001);
		assertEquals("12-29-2023", fetchedCompensation.getEffectiveDate());
	}
	@Test
	public void directReportsTest() {
		Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
		assertNotNull(employee);

		ReportingStructure reportingStructure = employeeService.getReportingStructure(employee.getEmployeeId());
		assertNotNull(reportingStructure);

		assertEquals(Integer.valueOf(4), reportingStructure.getNumberOfReports());
	}
}
