package com.warehouse.service.crypt.template;

public interface CryptPassword {
    /**
     * Шифрует наш пароль.
     * @param password
     * @return зашифрованный пароль.
     */
    String cryptPassword(String password);
    /**
     * Проверяет наш оригинальный пароль с зашифрованным.
     * @param password
     * @return true/false
     */
    boolean checkPassword(String password, String hashPassword);


}
