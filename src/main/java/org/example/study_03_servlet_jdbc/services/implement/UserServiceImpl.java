package org.example.study_03_servlet_jdbc.services.implement;

import org.example.study_03_servlet_jdbc.dao.IUserDao;
import org.example.study_03_servlet_jdbc.dao.implement.UserDaoImpl;
import org.example.study_03_servlet_jdbc.models.UserModel;
import org.example.study_03_servlet_jdbc.services.IUserService;

import java.util.Date;

public class UserServiceImpl implements IUserService
{
    IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel get(String username) {
        return userDao.findByUsername(username);
    }


    @Override
    public void insert(UserModel user)
    {

    }

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.get(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone)
    {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        userDao.insert(new UserModel(0, username, password, email, fullname, "avatar", 1, phone, new Date()));
        return true;
    }

    @Override
    public boolean checkExistEmail(String email)
    {
        return false;
    }

    @Override
    public boolean checkExistUsername(String username)
    {
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone)
    {
        return false;
    }
}
