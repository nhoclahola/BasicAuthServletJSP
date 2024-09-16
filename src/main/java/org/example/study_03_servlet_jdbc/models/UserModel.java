package org.example.study_03_servlet_jdbc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    // khai bao cac truong trong db
    // constructor
    // getter, setter
    // override

    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String avatar;
    private int roleId;
    private String phone;
    private Date createdDate;
}
