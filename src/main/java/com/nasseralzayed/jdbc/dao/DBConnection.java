package com.nasseralzayed.jdbc.dao;
//dao = data access object

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private static final String HOST = "127.0.0.1";
  private static final int PORT = 3306;
  private static final String DATABASE = "db_queries";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "root";
  //private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
  //private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

  private static Connection connection;

  public static Connection getConnection() throws SQLException {
    try {
      connection = DriverManager.getConnection(
          String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DATABASE), USERNAME, PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }
}




