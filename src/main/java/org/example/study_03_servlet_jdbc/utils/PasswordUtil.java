package org.example.study_03_servlet_jdbc.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil
{
    public static String encodePassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
