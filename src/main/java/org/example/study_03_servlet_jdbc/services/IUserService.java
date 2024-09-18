package org.example.study_03_servlet_jdbc.services;

import org.example.study_03_servlet_jdbc.models.UserModel;

public interface IUserService
{
    UserModel get(String username);

    void insert(UserModel user);

    UserModel login(String username, String password);

    boolean register(String username, String password, String email, String fullName, int roleId, String phone);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

    boolean changePassword(String email, String newPassword);
}
