package org.example.study_03_servlet_jdbc.models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserModel implements Serializable
{
    private static final long serialVersionUID = 1L;

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
