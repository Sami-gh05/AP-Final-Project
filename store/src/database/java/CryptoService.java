package database.java;

public interface CryptoService {
    public String encrypt(String info);
    public String decrypt(String hashedString);
}
