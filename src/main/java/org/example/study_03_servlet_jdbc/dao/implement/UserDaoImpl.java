package org.example.study_03_servlet_jdbc.dao.implement;

import org.example.study_03_servlet_jdbc.connection.DBConnectMySQL;
import org.example.study_03_servlet_jdbc.dao.IUserDao;
import org.example.study_03_servlet_jdbc.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements IUserDao
{
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public UserModel findByUsername(String username)
    {
        String sql = "SELECT * FROM users WHERE username = ? ";
        try
        {
            conn = DBConnectMySQL.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next())
            {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setFullName(rs.getString("fullname"));
                user.setPassWord(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(Integer.parseInt(rs.getString("role_id")));
                user.setPhone(rs.getString("phone"));
                user.setCreatedDate(rs.getDate("created_date"));
                return user;
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
        String sql = "SELECT * FROM users WHERE username = ? ";
        try
        {
            conn = DBConnectMySQL.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public void insert(UserModel user)
    {
        String sql = "INSERT INTO users(username, password, email, fullname, phone) VALUES(?, ?, ?, ?, ?)";
        try
        {
            conn = DBConnectMySQL.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getPhone());
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
