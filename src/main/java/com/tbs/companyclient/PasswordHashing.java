package com.tbs.companyclient;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 *
 * @author tom
 */
public class PasswordHashing {
  
  public static String generatePassword(String password) {
    // salt 32 bytes, Hash length 64 bytes
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
    char[] passwordArray = password.toCharArray();
    String hash = argon2.hash(3, // Number of iterations
      64 * 1024, // 64mb
      16, // how many parallel threads to use
      passwordArray);
    return hash;
  }
  
  public static boolean checkPassword(String hash, String password) {
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
    return argon2.verify(hash, password);
  }
  
  public static void main(String[] args) {
    String password = "pasword123";
    String hash = generatePassword(password);
    boolean success = checkPassword(hash, password);
    System.out.println("Hash + salt of the password: " + hash);
    System.out.println("Password verification success: " + success);
  }
}
