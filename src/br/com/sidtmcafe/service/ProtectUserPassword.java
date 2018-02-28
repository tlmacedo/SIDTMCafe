package br.com.sidtmcafe.service;

public class ProtectUserPassword {
    public static void main(String[] args) {
        String myPassword = "4879";

        String salt = PasswordUtil.getSalt(20);

        String mySecurePassword = PasswordUtil.generateSecurePassword(myPassword, salt);

        System.out.println("My secure password = " + mySecurePassword);
        System.out.println("Salt value = " + salt);
    }
}
