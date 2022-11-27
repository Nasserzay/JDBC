package com.nasseralzayed.jdbc.dao;

import com.nasseralzayed.jdbc.model.Employee;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

  List<Employee> findAll() throws SQLException;

  Employee findById(int id) throws SQLException;

  void save(Employee employee) throws SQLException;

  Employee deleteById(int id) throws SQLException;

}
