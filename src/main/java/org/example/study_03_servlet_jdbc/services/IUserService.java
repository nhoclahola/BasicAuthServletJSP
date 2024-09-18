package org.example.study_03_servlet_jdbc.services;

import org.example.study_03_servlet_jdbc.models.UserModel;

public interface IUserService
{
    public UserModel get(String username);

    void insert(UserModel user);

    public UserModel login(String username, String password);

    boolean register(String email, String password, String username, String
            fullname, String phone);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

    boolean changePassword(String email, String newPassword);
}
