package com.nasseralzayed.jdbc;

import com.nasseralzayed.jdbc.dao.EmployeeDao;
import com.nasseralzayed.jdbc.dao.EmployeeDaoImpl;
import com.nasseralzayed.jdbc.model.Employee;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws SQLException, ParseException {

    EmployeeDao employeeDao = new EmployeeDaoImpl();

    // Employee employee = new Employee(0, "Sam", false, new Date(),300); //manual insert

   /* Employee employee = Employee.builder() //insert with builder
        .name("Abdul")
        .gender(true)
        .birthDate(new Date())
        .salary(2500.5)
        .build();
    employeeDao.save(employee);*/

    //Employee employee = new Employee(2, "Hala", false, new Date(), 300.50); //manual update
    //employeeDao.save(employee);

    //Employee emp = employeeDao.findById(2);
    // System.out.println(emp);

    employeeDao.findAll().forEach(System.out::println); //find all  employees

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Employee Name: ");
    String name = scanner.nextLine();
    System.out.println("Enter Employee Salary: ");
    int salary = scanner.nextInt();
    System.out.println("Enter Employee Gender: ");
    boolean isManager = scanner.nextBoolean();
    System.out.println("Enter Employee Date: ");
    String date = scanner.next();
    java.sql.Date sqlDate = new java.sql.Date(format.parse(date).getTime());

    Employee employee = new Employee(0, name, isManager, sqlDate, salary);

    employeeDao.save(employee);

    //employeeDao.deleteById(2);

    System.out.println("Done");

  }

}
