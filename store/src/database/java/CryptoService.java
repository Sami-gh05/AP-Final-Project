package database.java;

public interface CryptoService {
    public String encrypt(String info) throws Exception;
    public String decrypt(String hashedString) throws Exception;
}
