package com.easytravel.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    public abstract class DatabaseConnect {
        private String URL = "jdbc:mysql://localhost:3306/easytravel";
        private String USER = "root";
        private String PASS = "";

        private Connection conn;
        protected Statement stmt;
        protected PreparedStatement preparedStatement;

        protected DatabaseConnect() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        protected void closeAllDBConnections() {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private Connection getConnection() throws SQLException {
            conn = DriverManager.getConnection(URL, USER, PASS);
            return conn;
        }

        private Statement getStatement() throws SQLException {
            stmt = getConnection().createStatement();
            return stmt;
        }

        private PreparedStatement getPreparedStatement(String sql) throws SQLException {
            preparedStatement = getConnection().prepareStatement(sql);
            return preparedStatement;
        }

        protected void getPreparedStatementForCustomCRUD(String sql) throws SQLException {
            getPreparedStatement(sql);
        }
        
        protected void getStatementForCustomInsert() throws SQLException {
            getStatement();
        }

        protected ResultSet getResultSet(String sql) throws SQLException {
            stmt = getStatement();
            return stmt.executeQuery(sql);
        }

        protected ResultSet getResultSetPreparedStatementById(String sql, Integer id) throws SQLException {
            preparedStatement = getPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        }
}
