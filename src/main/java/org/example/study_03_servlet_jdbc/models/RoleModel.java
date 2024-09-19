package org.example.study_03_servlet_jdbc.models;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoleModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int roleId;
    private String roleName;
}
