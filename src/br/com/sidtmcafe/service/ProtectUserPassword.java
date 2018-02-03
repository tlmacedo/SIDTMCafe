package br.com.sidtmcafe.service;

import br.com.sidtmcafe.service.PasswordUtils;

public class ProtectUserPassword {
    public static void main(String[] args) {
        String myPassword = "4879";

        String salt = PasswordUtils.getSalt(20);

        String mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, salt);

        System.out.println("My secure password = " + mySecurePassword);
        System.out.println("Salt value = " + salt);
    }
}
