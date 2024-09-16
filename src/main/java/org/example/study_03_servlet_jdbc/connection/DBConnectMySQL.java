package org.example.study_03_servlet_jdbc.connection;

import com.mysql.cj.jdbc.Driver;

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
    private static Connection connection = null;

    static {
        // Close Connection when program stopped
        Runtime.getRuntime().addShutdownHook(new Thread(DBConnectMySQL::closeConnection));
    }

    private DBConnectMySQL()
    {
    }

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                // Only register the driver and create the connection once
                DriverManager.registerDriver(new Driver());
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            }
            catch (SQLException e)
            {
                Logger.getLogger(DBConnectMySQL.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return connection;
    }

    public static void closeConnection()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
                System.out.println("Connection closed.");
            }
            catch (SQLException e)
            {
                Logger.getLogger(DBConnectMySQL.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
