package org.example.study_03_servlet_jdbc.dao.implement;

import lombok.Getter;
import org.example.study_03_servlet_jdbc.connection.DBConnectMySQL;
import org.example.study_03_servlet_jdbc.dao.IUserDao;
import org.example.study_03_servlet_jdbc.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Singleton eager
public class UserDaoImpl implements IUserDao
{
    // Connection of this class is taken from HikariCP connection pool, so we don't need to close

    @Getter
    private static final UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {}

    @Override
    public UserModel findByUsername(String username)
    {
        String query = "SELECT * FROM users WHERE username = ? ";
        Connection conn = DBConnectMySQL.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query))
        {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    return UserModel.builder()
                            .id(rs.getInt("id"))
                            .username(rs.getString("username"))
                            .password(rs.getString("password"))
                            .email(rs.getString("email"))
                            .fullName(rs.getString("full_name"))
                            .avatar(rs.getString("avatar"))
                            .roleId(Integer.parseInt(rs.getString("role_id")))
                            .phone(rs.getString("phone"))
                            .createdDate(rs.getDate("created_date"))
                            .build();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkExistUsername(String username)
    {
        String query = "SELECT 1 FROM users WHERE username = ?";
        Connection conn = DBConnectMySQL.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query))
        {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery())
            {
                return rs.next();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkExistEmail(String email)
    {
        String query = "SELECT 1 FROM users WHERE email = ?";
        Connection conn = DBConnectMySQL.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query))
        {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery())
            {
                return rs.next();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void insert(UserModel user)
    {
        String sql = "INSERT INTO users(username, password, email, full_name, phone, avatar, role_id, created_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DBConnectMySQL.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAvatar());
            ps.setInt(7, user.getRoleId());
            ps.setString(8, user.getCreatedDate().toString());
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean changePassword(String email, String newEncodedPassword)
    {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        Connection conn = DBConnectMySQL.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, newEncodedPassword);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException exception)
        {
            return false;
        }
    }

    @Override
    public boolean checkExistPhone(String phone)
    {
        return false;
    }
}
