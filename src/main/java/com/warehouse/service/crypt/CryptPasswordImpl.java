package com.warehouse.service.crypt;

import com.warehouse.service.crypt.template.CryptPassword;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptPasswordImpl implements CryptPassword {

    public String cryptPassword(String password) {
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashPassword;
    }

    public  boolean checkPassword(String password, String hashPassword) {
        boolean checkPassword = BCrypt.checkpw(password, hashPassword);
        return checkPassword;
    }

}
