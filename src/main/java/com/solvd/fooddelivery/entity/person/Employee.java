package com.solvd.fooddelivery.entity.person;

import com.solvd.fooddelivery.entity.vehicle.CivilVehicle;

import java.time.LocalDate;
import java.time.Period;

public class Employee extends Human<CivilVehicle> {

    private LocalDate hiringDate;
    private long employeeId;

    public Employee(String name, LocalDate dateOfBirth) {
        super(name, dateOfBirth);
    }

    public Period countWorkingPeriod() {
        return Period.between(LocalDate.now(), getHiringDate());
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        return hiringDate != null ? hiringDate.equals(employee.hiringDate) : employee.hiringDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (hiringDate != null ? hiringDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", hiringDate=").append(hiringDate);
        sb.append('}');
        return sb.toString();
    }
}
