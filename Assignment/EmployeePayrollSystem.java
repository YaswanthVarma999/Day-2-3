package com.wt;

import java.util.ArrayList;

class Employee {
 private int employeeId;
 private String name;
 private String department;
 protected double baseSalary;

 public Employee(int employeeId, String name, String department, double baseSalary) {
     this.employeeId = employeeId;
     this.name = name;
     this.department = department;
     this.baseSalary = baseSalary;
 }

 public int getEmployeeId() {
     return employeeId;
 }

 public String getName() {
     return name;
 }

 public String getDepartment() {
     return department;
 }

 public double calculateSalary() {
     return baseSalary;
 }

 @Override
 public String toString() {
     return "Employee ID: " + employeeId + ", Name: " + name + ", Department: " + department + ", Base Salary: " + baseSalary;
 }
}

class PermanentEmployee extends Employee {
 private double bonus;

 public PermanentEmployee(int employeeId, String name, String department, double baseSalary, double bonus) {
     super(employeeId, name, department, baseSalary);
     this.bonus = bonus;
 }

 @Override
 public double calculateSalary() {
     return baseSalary + bonus;
 }

 @Override
 public String toString() {
     return super.toString() + ", Bonus: " + bonus + ", Total Salary: " + calculateSalary();
 }
}

class ContractualEmployee extends Employee {
 private int hoursWorked;
 private double hourlyRate;

 public ContractualEmployee(int employeeId, String name, String department, int hoursWorked, double hourlyRate) {
     super(employeeId, name, department, 0); // Base salary is not applicable
     this.hoursWorked = hoursWorked;
     this.hourlyRate = hourlyRate;
 }

 @Override
 public double calculateSalary() {
     return hoursWorked * hourlyRate;
 }

 @Override
 public String toString() {
     return super.toString() + ", Hours Worked: " + hoursWorked + ", Hourly Rate: " + hourlyRate + ", Total Salary: " + calculateSalary();
 }
}

//Payroll System
class Payroll {
 private ArrayList<Employee> employees;

 public Payroll() {
     employees = new ArrayList<>();
 }

 public void addEmployee(Employee employee) {
     employees.add(employee);
     System.out.println("Employee added successfully: " + employee.getName());
 }

 public void removeEmployee(int employeeId) {
     employees.removeIf(employee -> employee.getEmployeeId() == employeeId);
     System.out.println("Employee removed successfully.");
 }

 public void displayAllEmployees() {
     System.out.println("Employee Details:");
     for (Employee employee : employees) {
         System.out.println(employee);
     }
 }

 public void generateSalaryReport() {
     System.out.println("Salary Report:");
     for (Employee employee : employees) {
         System.out.println("Employee: " + employee.getName() + ", Total Salary: " + employee.calculateSalary());
     }
 }
}

//Main Class
public class EmployeePayrollSystem {
 public static void main(String[] args) {
     Payroll payroll = new Payroll();

     // Adding employees
     PermanentEmployee emp1 = new PermanentEmployee(1, "Alice", "IT", 50000, 10000);
     PermanentEmployee emp2 = new PermanentEmployee(2, "Bob", "HR", 40000, 5000);
     ContractualEmployee emp3 = new ContractualEmployee(3, "Charlie", "Finance", 120, 50);

     payroll.addEmployee(emp1);
     payroll.addEmployee(emp2);
     payroll.addEmployee(emp3);

     // Display all employees
     payroll.displayAllEmployees();

     // Generate salary report
     payroll.generateSalaryReport();

     // Remove an employee
     payroll.removeEmployee(2);

     // Display all employees after removal
     payroll.displayAllEmployees();
 }
}

