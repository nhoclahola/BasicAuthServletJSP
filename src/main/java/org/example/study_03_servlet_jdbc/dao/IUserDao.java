package org.example.study_03_servlet_jdbc.dao;

import org.example.study_03_servlet_jdbc.models.UserModel;

public interface IUserDao
{
    UserModel findByUsername(String username);
    boolean checkExistUsername(String username);

    public boolean checkExistEmail(String email);

    void insert(UserModel user);
}
