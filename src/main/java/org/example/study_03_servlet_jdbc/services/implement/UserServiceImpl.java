package org.example.study_03_servlet_jdbc.services.implement;

import org.example.study_03_servlet_jdbc.dao.IUserDao;
import org.example.study_03_servlet_jdbc.dao.implement.UserDaoImpl;
import org.example.study_03_servlet_jdbc.models.UserModel;
import org.example.study_03_servlet_jdbc.services.IUserService;
import org.example.study_03_servlet_jdbc.utils.PasswordUtil;

public class UserServiceImpl implements IUserService
{
    IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel get(String username)
    {
        return userDao.findByUsername(username);
    }

    @Override
    public void insert(UserModel user)
    {

    }

    @Override
    public UserModel login(String username, String password)
    {
        UserModel user = this.get(username);
        // Check if the password matches the encoded password
        if (user != null && PasswordUtil.matchPassword(password, user.getPassword()))
            return user;
        return null;
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone)
    {
        // Controller has already checked if username and email are unique, so we don't need to do this again (performance boost :) )
        // Hash password by bcrypt
        String encodedPassword = PasswordUtil.encodePassword(password);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        userDao.insert(new UserModel(0, username, encodedPassword, email, fullname, "avatar", 3, phone, date));
        return true;
    }

    @Override
    public boolean checkExistUsername(String username)
    {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistEmail(String email)
    {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistPhone(String phone)
    {
        return false;
    }

    @Override
    public boolean changePassword(String email, String newPassword)
    {
        String newEncodedPassword = PasswordUtil.encodePassword(newPassword);
        return userDao.changePassword(email, newEncodedPassword);
    }
}
