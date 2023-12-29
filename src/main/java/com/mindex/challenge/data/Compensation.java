package com.mindex.challenge.data;

public class Compensation {
    private String employeeId;
    private double salary;
    private String effectiveDate;

    public Compensation() {
    }

    public String getEmployeeID() { return this.employeeId; }

    public void setEmployeeID(String employeeID) { this.employeeId = employeeID; }

    public double getSalary() { return this.salary; }

    public void setSalary(double salary) { this.salary = salary; }

    public String getEffectiveDate() { return this.effectiveDate; }

    public void setEffectiveDate(String date) { this.effectiveDate = date; }

    public String toString() {
        return "EmployeeID: " + getEmployeeID() + " Salary: " + getSalary() + " Effective Date: " + getEffectiveDate();
    }
}
