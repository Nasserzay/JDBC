package com.nasseralzayed.jdbc.dao;

import com.nasseralzayed.jdbc.model.Employee;
import com.nasseralzayed.jdbc.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {


  @Override
  public List<Employee> findAll() throws SQLException {
    Connection con = DBConnection.getConnection();
    if (con == null) {
      return null;
    }

    List<Employee> employees = new LinkedList<>();

    String query = "SELECT * FROM employee";

    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

      ResultSet resultSet = preparedStatement.executeQuery(); // java.sql.ResultSet

      while (resultSet.next()) {

        Employee employee = Employee.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .gender(resultSet.getBoolean("gender"))
            .birthDate(resultSet.getDate("birth_date"))
            .salary(resultSet.getDouble("salary"))
            .build();

        employees.add(employee);

      }

    } catch (SQLException e) {
      e.printStackTrace();

    } finally {
      try {
        con.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return employees;
  }

  @Override
  public Employee findById(int id) throws SQLException {
    Connection con = DBConnection.getConnection();
    if (con == null) {
      return null;
    }

    String query = "SELECT * FROM employee WHERE id=?;";
    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return Employee.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .gender(resultSet.getBoolean("gender"))
            .birthDate(resultSet.getDate("birth_date"))
            .salary(resultSet.getDouble("salary"))
            .build();


      }

    } catch (SQLException se) {
      se.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }

    return null;
  }

  @Override
  public void save(Employee employee) throws SQLException {
    Connection con = DBConnection.getConnection();
    if (con == null) {
      return;
    }

    if (employee.getId() > 0) { //update
      String query = "UPDATE employee SET name=?, gender=?, birth_date=?, salary=? WHERE id=?";
      try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

        preparedStatement.setString(1, employee.getName());
        preparedStatement.setBoolean(2, employee.isGender());
        preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirthDate())); // java.sql.Date
        preparedStatement.setDouble(4, employee.getSalary());  // java.sql.Double
        preparedStatement.setInt(5, employee.getId());
        preparedStatement.executeUpdate();


      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          con.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }

    } else {
      String query = "INSERT INTO employee (name, gender, birth_date, salary) VALUES (?,?,?,?)";
      try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

        preparedStatement.setString(1, employee.getName());
        preparedStatement.setBoolean(2, employee.isGender());
        preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirthDate())); // java.sql.Date
        preparedStatement.setDouble(4, employee.getSalary());  // java.sql.Double
        preparedStatement.executeUpdate();


      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          con.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    }
  }

  @Override
  public Employee deleteById(int id) throws SQLException {

    Connection con = DBConnection.getConnection();
    if (con == null) {
      return null;
    }

    String query = "DELETE FROM employee WHERE id=?;";
    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
      preparedStatement.setInt(1, id);

      preparedStatement.executeUpdate();
    } catch (SQLException se) {
      se.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    return null;
  } // end of deleteById()

}


