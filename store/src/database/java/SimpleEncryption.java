package database.java;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;


public class SimpleEncryption implements CryptoService {
    private int shift; // Shift value for Caesar cipher

    public SimpleEncryption(int shift) {
        this.shift = shift;
    }

    // Encrypts a string using a Caesar cipher
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c + shift);
                if ((Character.isLowerCase(c) && shifted > 'z') || (Character.isUpperCase(c) && shifted > 'Z')) {
                    shifted -= 26; // Wrap around
                }
                encrypted.append(shifted);
            } else {
                encrypted.append(c); // Non-letter characters remain unchanged
            }
        }
        return encrypted.toString();
    }

    // Decrypts an encrypted string
    public String decrypt(String encrypted) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c - shift);
                if ((Character.isLowerCase(c) && shifted < 'a') || (Character.isUpperCase(c) && shifted < 'A')) {
                    shifted += 26; // Wrap around
                }
                decrypted.append(shifted);
            } else {
                decrypted.append(c); // Non-letter characters remain unchanged
            }
        }
        return decrypted.toString();
    }
}


