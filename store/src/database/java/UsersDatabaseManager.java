package database.java;

import product.Cloth;
import product.Phone;
import account.User;
import product.Product;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDatabaseManager implements DatabaseManager<User> {
    private final String url = "jdbc:mysql://mysql-1a7e2ea6-joeroc-a519.d.aivencloud.com:10588/mamad";
    private final String user = "avnadmin";
    private final String password = "AVNS_qfvxNvrWtAOxfVSC9f3";
    CryptoService cryptoService;

    public UsersDatabaseManager(){
        cryptoService = new SimpleEncryption(3);
    }

    @Override
    public void writeToDatabase(List<User> users) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement stmt = connection.createStatement();
            // Check if tables exist and create them if they don't
            createTablesIfNotExist(stmt);

            // Clear existing data
            clearExistingData(stmt);

            // Insert Users and Products
            String insertUserSQL = "INSERT INTO Users (userName, password, name, address, phoneNumber) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmtUser = connection.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);

            for (User user : users) {
                // Insert User
                pstmtUser.setString(1, user.getUserName());
                pstmtUser.setString(2, cryptoService.encrypt(user.getPassword()));
                pstmtUser.setString(3, user.getName());
                pstmtUser.setString(4, user.getAddress());
                pstmtUser.setString(5, user.getPhoneNumber());
                pstmtUser.executeUpdate();
                ResultSet rsUser = pstmtUser.getGeneratedKeys();
                rsUser.next();
                int userId = rsUser.getInt(1);

                // Insert Products from previousPurchases
                insertPhones(userId, user.getPreviousPurchases(), "PhonePreviousPurchases", connection);
                insertClothes(userId, user.getPreviousPurchases(), "ClothPreviousPurchases", connection);

                // Insert Products from shoppingCard
                insertPhones(userId, user.getShoppingCard(), "PhoneShoppingCart", connection);
                insertClothes(userId, user.getShoppingCard(), "ClothShoppingCart", connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> readFromDatabase() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement stmt = connection.createStatement();
            // Check if tables exist
            if (!checkIfTablesExist(stmt)) {
                return users;  // Return empty list if tables do not exist
            }

            // Read Users
            String selectUserSQL = "SELECT * FROM Users";
            ResultSet rsUsers = stmt.executeQuery(selectUserSQL);
            while (rsUsers.next()) {
                int userId = rsUsers.getInt("id");
                String userName = rsUsers.getString("userName");
                String password = rsUsers.getString("password");
                String name = rsUsers.getString("name");
                String address = rsUsers.getString("address");
                String phoneNumber = rsUsers.getString("phoneNumber");

                User user = new User(userName, cryptoService.decrypt(password), name, address, phoneNumber);

                // Read Products for each User
                readPhonesForUser(userId, user, "PhonePreviousPurchases", connection);
                readClothesForUser(userId, user, "ClothPreviousPurchases", connection);
                readPhonesForUser(userId, user, "PhoneShoppingCart", connection);
                readClothesForUser(userId, user, "ClothShoppingCart", connection);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private void insertPhones(int userId, Map<Product, Integer> productsMap, String tableName, Connection connection) throws SQLException {
        String insertPhoneSQL = "INSERT INTO " + tableName + " (name, price, companyName, model, color, image, userId, amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmtPhone = connection.prepareStatement(insertPhoneSQL);
        for (Map.Entry<Product, Integer> entry : productsMap.entrySet()) {
            if (entry.getKey() instanceof Phone) {
                Phone phone = (Phone) entry.getKey();
                int amount = entry.getValue();
                phone.fillByteWithIcon();

                pstmtPhone.setString(1, phone.getTitle());
                pstmtPhone.setDouble(2, phone.getPrice());
                pstmtPhone.setString(3, phone.getCompanyName());
                pstmtPhone.setString(4, phone.getModel());
                pstmtPhone.setString(5, phone.getColor());
                pstmtPhone.setBytes(6, phone.getImage());
                pstmtPhone.setInt(7, userId);
                pstmtPhone.setInt(8, amount);
                pstmtPhone.executeUpdate();
            }
        }
    }

    private void insertClothes(int userId, Map<Product, Integer> productsMap, String tableName, Connection connection) throws SQLException {
        String insertClothSQL = "INSERT INTO " + tableName + " (name, price, size, color, sex, image, userId, amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmtCloth = connection.prepareStatement(insertClothSQL);
        for (Map.Entry<Product, Integer> entry : productsMap.entrySet()) {
            if (entry.getKey() instanceof Cloth) {
                Cloth cloth = (Cloth) entry.getKey();
                int amount = entry.getValue();
                cloth.fillByteWithIcon();

                pstmtCloth.setString(1, cloth.getTitle());
                pstmtCloth.setDouble(2, cloth.getPrice());
                pstmtCloth.setString(3, cloth.getSize());
                pstmtCloth.setString(4, cloth.getColor());
                pstmtCloth.setString(5, cloth.getSex());
                pstmtCloth.setBytes(6, cloth.getImage());
                pstmtCloth.setInt(7, userId);
                pstmtCloth.setInt(8, amount);
                pstmtCloth.executeUpdate();
            }
        }
    }

    private void readPhonesForUser(int userId, User user, String tableName, Connection connection) throws SQLException {
        String selectPhoneSQL = "SELECT * FROM " + tableName + " WHERE userId = ?";
        PreparedStatement pstmtPhone = connection.prepareStatement(selectPhoneSQL);
        pstmtPhone.setInt(1, userId);
        ResultSet rsPhones = pstmtPhone.executeQuery();
        while (rsPhones.next()) {
            String name = rsPhones.getString("name");
            double price = rsPhones.getDouble("price");
            String companyName = rsPhones.getString("companyName");
            String model = rsPhones.getString("model");
            String color = rsPhones.getString("color");
            byte[] image = rsPhones.getBytes("image");
            int amount = rsPhones.getInt("amount");

            Phone phone = new Phone(name, (float) price, companyName, model, color);
            phone.setImage(image);
            phone.fillIconWithByte();

            if (tableName.equals("PhonePreviousPurchases")) {
                user.addToPreviousPurchases(phone, amount);
            } else if (tableName.equals("PhoneShoppingCart")) {
                user.addTOShoppingCard(phone, amount);
            }
        }
    }

    private void readClothesForUser(int userId, User user, String tableName, Connection connection) throws SQLException {
        String selectClothSQL = "SELECT * FROM " + tableName + " WHERE userId = ?";
        PreparedStatement pstmtCloth = connection.prepareStatement(selectClothSQL);
        pstmtCloth.setInt(1, userId);
        ResultSet rsClothes = pstmtCloth.executeQuery();
        while (rsClothes.next()) {
            String name = rsClothes.getString("name");
            double price = rsClothes.getDouble("price");
            String size = rsClothes.getString("size");
            String color = rsClothes.getString("color");
            String sex = rsClothes.getString("sex");
            byte[] image = rsClothes.getBytes("image");
            int amount = rsClothes.getInt("amount");

            Cloth cloth = new Cloth(name, (float) price, size, color, sex);
            cloth.setImage(image);
            cloth.fillIconWithByte();

            if (tableName.equals("ClothPreviousPurchases")) {
                user.addToPreviousPurchases(cloth, amount);
            } else if (tableName.equals("ClothShoppingCart")) {
                user.addTOShoppingCard(cloth, amount);
            }
        }
    }

    private void createTablesIfNotExist(Statement stmt) throws SQLException {
        String createUserTableSQL = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "userName VARCHAR(255) NOT NULL, " +
                "password VARCHAR(255) NOT NULL, " +
                "name VARCHAR(255), " +
                "address VARCHAR(255), " +
                "phoneNumber VARCHAR(255))";

        String createPhonePreviousPurchasesTableSQL = "CREATE TABLE IF NOT EXISTS PhonePreviousPurchases (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "companyName VARCHAR(255), " +
                "model VARCHAR(255), " +
                "color VARCHAR(50), " +
                "image BLOB, " +
                "userId INT, " +
                "amount INT, " +
                "FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE)";

        String createClothPreviousPurchasesTableSQL = "CREATE TABLE IF NOT EXISTS ClothPreviousPurchases (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "size VARCHAR(50), " +
                "color VARCHAR(50), " +
                "sex VARCHAR(50), " +
                "image BLOB, " +
                "userId INT, " +
                "amount INT, " +
                "FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE)";

        String createPhoneShoppingCartTableSQL = "CREATE TABLE IF NOT EXISTS PhoneShoppingCart (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "companyName VARCHAR(255), " +
                "model VARCHAR(255), " +
                "color VARCHAR(50), " +
                "image BLOB, " +
                "userId INT, " +
                "amount INT, " +
                "FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE)";

        String createClothShoppingCartTableSQL = "CREATE TABLE IF NOT EXISTS ClothShoppingCart (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "size VARCHAR(50), " +
                "color VARCHAR(50), " +
                "sex VARCHAR(50), " +
                "image BLOB, " +
                "userId INT, " +
                "amount INT, " +
                "FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE)";

        stmt.executeUpdate(createUserTableSQL);
        stmt.executeUpdate(createPhonePreviousPurchasesTableSQL);
        stmt.executeUpdate(createClothPreviousPurchasesTableSQL);
        stmt.executeUpdate(createPhoneShoppingCartTableSQL);
        stmt.executeUpdate(createClothShoppingCartTableSQL);
    }

    private boolean checkIfTablesExist(Statement stmt) throws SQLException {
        ResultSet rsUsers = stmt.executeQuery("SHOW TABLES LIKE 'Users'");
        boolean usersExist = rsUsers.next();
        ResultSet rsPhonePrevPurchases = stmt.executeQuery("SHOW TABLES LIKE 'PhonePreviousPurchases'");
        boolean phonePrevPurchasesExist = rsPhonePrevPurchases.next();
        ResultSet rsClothPrevPurchases = stmt.executeQuery("SHOW TABLES LIKE 'ClothPreviousPurchases'");
        boolean clothPrevPurchasesExist = rsClothPrevPurchases.next();
        ResultSet rsPhoneShoppingCart = stmt.executeQuery("SHOW TABLES LIKE 'PhoneShoppingCart'");
        boolean phoneShoppingCartExist = rsPhoneShoppingCart.next();
        ResultSet rsClothShoppingCart = stmt.executeQuery("SHOW TABLES LIKE 'ClothShoppingCart'");
        boolean clothShoppingCartExist = rsClothShoppingCart.next();
        return usersExist && phonePrevPurchasesExist && clothPrevPurchasesExist && phoneShoppingCartExist && clothShoppingCartExist;
    }

    private void clearExistingData(Statement stmt) throws SQLException {
        stmt.executeUpdate("DELETE FROM PhonePreviousPurchases");
        stmt.executeUpdate("DELETE FROM ClothPreviousPurchases");
        stmt.executeUpdate("DELETE FROM PhoneShoppingCart");
        stmt.executeUpdate("DELETE FROM ClothShoppingCart");
        stmt.executeUpdate("DELETE FROM Users");
        stmt.executeUpdate("ALTER TABLE PhonePreviousPurchases AUTO_INCREMENT = 1");
        stmt.executeUpdate("ALTER TABLE ClothPreviousPurchases AUTO_INCREMENT = 1");
        stmt.executeUpdate("ALTER TABLE PhoneShoppingCart AUTO_INCREMENT = 1");
        stmt.executeUpdate("ALTER TABLE ClothShoppingCart AUTO_INCREMENT = 1");
        stmt.executeUpdate("ALTER TABLE Users AUTO_INCREMENT = 1");
    }

}
