package com.mindex.challenge.data;

import java.time.LocalDate;

public class Compensation {
    private Employee employee;
    private double salary;
    private LocalDate effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() { return this.employee; }

    public void setEmployee(Employee employee) { this.employee = employee; }

    public double getSalary() { return this.salary; }

    public void setSalary(double salary) { this.salary = salary; }

    public LocalDate getEffectiveDate() { return this.effectiveDate; }

    public void setEffectiveDate(LocalDate date) { this.effectiveDate = date; }
}
