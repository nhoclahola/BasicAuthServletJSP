package org.example.study_03_servlet_jdbc.configurations;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.study_03_servlet_jdbc.connection.DBConnectMySQL;
import org.example.study_03_servlet_jdbc.services.IUserService;
import org.example.study_03_servlet_jdbc.services.implement.UserServiceImpl;

import java.sql.*;
import java.util.Enumeration;

@WebListener
public class AppContextListener implements ServletContextListener
{
    // Application Init
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Connection connection = DBConnectMySQL.getConnection();
        try (Statement statement = connection.createStatement())
        {
            // Create tables
            String createRolesTable = "CREATE TABLE IF NOT EXISTS `roles` (" +
                    "`role_id` int(11) NOT NULL AUTO_INCREMENT, " +
                    "`role_name` varchar(255) NOT NULL, " +
                    "PRIMARY KEY (`role_id`)) " +
                    "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
            String createUsersTable = "CREATE TABLE IF NOT EXISTS `users` (" +
                    "`id` int(11) NOT NULL AUTO_INCREMENT, " +
                    "`email` varchar(255) NOT NULL UNIQUE, " +
                    "`username` varchar(255) NOT NULL UNIQUE, " +
                    "`password` varchar(255) NOT NULL, " +
                    "`avatar` varchar(255) DEFAULT NULL, " +
                    "`role_id` int(11) NOT NULL, " +
                    "`phone` varchar(255) NOT NULL, " +
                    "`created_date` date NOT NULL, " +
                    "`full_name` varchar(255) DEFAULT NULL, " +
                    "PRIMARY KEY (`id`), " +
                    "KEY `user_role` (`role_id`), " +
                    "FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)) " +
                    "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
            statement.executeUpdate(createRolesTable);
            statement.executeUpdate(createUsersTable);

            // Create roles
            String insertRoles = "INSERT IGNORE INTO `roles` (`role_id`, `role_name`) VALUES " +
                    "(1, 'ADMIN'), " +
                    "(2, 'MANAGER'), " +
                    "(3, 'USER');";
            statement.executeUpdate(insertRoles);
            System.out.println("Database tables checked and created if necessary.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        IUserService service = new UserServiceImpl();
        if (!service.checkExistUsername("admin"))
        {
            service.register("admin", "admin", "admin@gmail.com", "ADMIN", 1, "000");
            System.out.println("Admin user has been created with default password");
        }
        if (!service.checkExistUsername("manager"))
        {
            service.register("manager", "manager", "manager@gmail.com", "MANAGER", 2, "000");
            System.out.println("Manager user has been created with default password");
        }
    }

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
