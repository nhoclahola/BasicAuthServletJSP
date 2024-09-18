package org.example.study_03_servlet_jdbc.connection;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Singleton
public class DBConnectMySQL
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ltweb_test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static final HikariDataSource dataSource;

    static
    {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(DB_URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        // All of bellow config is HikariConfig's default (just to clarify)
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(10);
        config.setIdleTimeout(600000);

        dataSource = new HikariDataSource(config);
    }

    private DBConnectMySQL()
    {
    }

    public static Connection getConnection()
    {
        try
        {
            return dataSource.getConnection();
        }
        catch (SQLException e)
        {
            Logger.getLogger(DBConnectMySQL.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
