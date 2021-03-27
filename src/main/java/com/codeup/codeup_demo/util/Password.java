package com.codeup.codeup_demo.util;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
    //    private static final int ROUNDS = 12;
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean check(String paramPassword, String hash) {
        return BCrypt.checkpw(paramPassword, hash);
    }
}
