package database.java;
import product.Product;
import product.Cloth;
import product.Phone;

import account.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class UsersDatabaseManager implements DatabaseManager<User> {
    private final String url = "jdbc:mysql://mysql-1a7e2ea6-joeroc-a519.d.aivencloud.com:10588/mamad";
    private final String user = "avnadmin";
    private final String password = "AVNS_qfvxNvrWtAOxfVSC9f3";

    @Override
    public void writeToDatabase(List<User> users) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement stmt = connection.createStatement();
            // Check if tables exist and create them if they don't
            createTablesIfNotExist(stmt);

            // Clear existing data and reset auto-increment
            stmt.executeUpdate("DELETE FROM previousPurchasesClothes");
            stmt.executeUpdate("DELETE FROM previousPurchasesPhones");
            stmt.executeUpdate("DELETE FROM shoppingCardClothes");
            stmt.executeUpdate("DELETE FROM shoppingCardPhones");
            stmt.executeUpdate("DELETE FROM Users");
            stmt.executeUpdate("ALTER TABLE Users AUTO_INCREMENT = 1");

            // Insert Users and Products
            String insertUserSQL = "INSERT INTO Users (userName, password) VALUES (?, ?)";
            String insertClothSQL = "INSERT INTO %s (name, price, size, color, sex, user_id, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String insertPhoneSQL = "INSERT INTO %s (name, price, companyName, model, color, user_id, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmtUser = connection.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmtClothPrev = connection.prepareStatement(String.format(insertClothSQL, "previousPurchasesClothes"));
            PreparedStatement pstmtPhonePrev = connection.prepareStatement(String.format(insertPhoneSQL, "previousPurchasesPhones"));
            PreparedStatement pstmtClothCard = connection.prepareStatement(String.format(insertClothSQL, "shoppingCardClothes"));
            PreparedStatement pstmtPhoneCard = connection.prepareStatement(String.format(insertPhoneSQL, "shoppingCardPhones"));

            for (User user : users) {
                // Insert User
                pstmtUser.setString(1, user.getUserName());
                pstmtUser.setString(2, user.getPassword());
                pstmtUser.executeUpdate();
                ResultSet rsUser = pstmtUser.getGeneratedKeys();
                rsUser.next();
                int userId = rsUser.getInt(1);

                // Insert Products from previousPurchases
                insertProducts(userId, user.getPreviousPurchases(), pstmtClothPrev, pstmtPhonePrev);

                // Insert Products from shoppingCard
                insertProducts(userId, user.getShoppingCard(), pstmtClothCard, pstmtPhoneCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertProducts(int userId, Map<Product, Integer> productsMap,
                                PreparedStatement pstmtCloth, PreparedStatement pstmtPhone) throws SQLException {
        for (Map.Entry<Product, Integer> entry : productsMap.entrySet()) {
            Product product = entry.getKey();
            int amount = entry.getValue();

            if (product instanceof Cloth) {
                Cloth cloth = (Cloth) product;
                pstmtCloth.setString(1, cloth.getTitle());
                pstmtCloth.setDouble(2, cloth.getPrice());
                pstmtCloth.setString(3, cloth.getSize());
                pstmtCloth.setString(4, cloth.getColor());
                pstmtCloth.setString(5, cloth.getSex());
                pstmtCloth.setInt(6, userId);  // Foreign key to Users table
                pstmtCloth.setInt(7, amount);  // Number of products
                pstmtCloth.executeUpdate();
            } else if (product instanceof Phone) {
                Phone phone = (Phone) product;
                pstmtPhone.setString(1, phone.getTitle());
                pstmtPhone.setDouble(2, phone.getPrice());
                pstmtPhone.setString(3, phone.getCompanyName());
                pstmtPhone.setString(4, phone.getModel());
                pstmtPhone.setString(5, phone.getColor());
                pstmtPhone.setInt(6, userId);  // Foreign key to Users table
                pstmtPhone.setInt(7, amount);  // Number of products
                pstmtPhone.executeUpdate();
            }
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

                User user = new User(userName, password);

                // Read Products for each User
                readProductsForUser(userId, user, connection);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void readProductsForUser(int userId, User user, Connection connection) throws SQLException {
        readSpecificProductForUser(userId, user, connection, "previousPurchasesClothes", Cloth.class, true);
        readSpecificProductForUser(userId, user, connection, "previousPurchasesPhones", Phone.class, true);
        readSpecificProductForUser(userId, user, connection, "shoppingCardClothes", Cloth.class, false);
        readSpecificProductForUser(userId, user, connection, "shoppingCardPhones", Phone.class, false);
    }

    private <T extends Product> void readSpecificProductForUser(int userId, User user, Connection connection, String tableName, Class<T> productClass, boolean isPrevious) throws SQLException {
        String selectProductSQL = "SELECT * FROM " + tableName + " WHERE user_id = ?";
        PreparedStatement pstmtProduct = connection.prepareStatement(selectProductSQL);
        pstmtProduct.setInt(1, userId);
        ResultSet rsProducts = pstmtProduct.executeQuery();
        while (rsProducts.next()) {
            String productName = rsProducts.getString("name");
            double productPrice = rsProducts.getDouble("price");
            int amount = rsProducts.getInt("amount");
            Product product = null;

            if (productClass.equals(Cloth.class)) {
                String size = rsProducts.getString("size");
                String color = rsProducts.getString("color");
                String sex = rsProducts.getString("sex");
                product = new Cloth(productName, productPrice, size, color, sex);
            } else if (productClass.equals(Phone.class)) {
                String companyName = rsProducts.getString("companyName");
                String model = rsProducts.getString("model");
                String color = rsProducts.getString("color");
                product = new Phone(productName, productPrice, companyName, model, color);
            }

            if (product != null) {
                if (isPrevious) {
                    user.addToPreviousPurchases(product, amount);
                } else {
                    user.addTOShoppingCard(product, amount);
                }
            }
        }
    }

    private void createTablesIfNotExist(Statement stmt) throws SQLException {
        String createUserTableSQL = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "userName VARCHAR(255) NOT NULL, " +
                "password VARCHAR(255) NOT NULL)";
        String createClothTableSQL = "CREATE TABLE IF NOT EXISTS %s (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "size VARCHAR(50), " +
                "color VARCHAR(50), " +
                "sex VARCHAR(50), " +
                "user_id INT, " +
                "amount INT, " +
                "FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE)";
        String createPhoneTableSQL = "CREATE TABLE IF NOT EXISTS %s (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "companyName VARCHAR(255), " +
                "model VARCHAR(255), " +
                "color VARCHAR(50), " +
                "user_id INT, " +
                "amount INT, " +
                "FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE)";
        stmt.executeUpdate(createUserTableSQL);
        stmt.executeUpdate(String.format(createClothTableSQL, "previousPurchasesClothes"));
        stmt.executeUpdate(String.format(createClothTableSQL, "shoppingCardClothes"));
        stmt.executeUpdate(String.format(createPhoneTableSQL, "previousPurchasesPhones"));
        stmt.executeUpdate(String.format(createPhoneTableSQL, "shoppingCardPhones"));
    }

    private boolean checkIfTablesExist(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'Users'");
        return rs.next();
    }
}

