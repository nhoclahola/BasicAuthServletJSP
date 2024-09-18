package org.example.study_03_servlet_jdbc.configurations;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.study_03_servlet_jdbc.connection.DBConnectMySQL;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class AppContextListener implements ServletContextListener
{

    //Clean up Hikari and MySQL connection
    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        if (DBConnectMySQL.dataSource != null)
        {
            DBConnectMySQL.dataSource.close();
            System.out.println("HikariDataSource closed.");
        }
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements())
        {
            Driver driver = drivers.nextElement();
            try
            {
                DriverManager.deregisterDriver(driver);
                System.out.println("JDBC Driver " + driver + " has been deregistered.");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
