package database.java;

import product.Cloth;
import product.Phone;
import product.Product;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ProductsDatabaseManager implements DatabaseManager<Product> {
    private final String url = "jdbc:mysql://mysql-1a7e2ea6-joeroc-a519.d.aivencloud.com:10588/mamad";
    private final String user = "avnadmin";
    private final String password = "AVNS_qfvxNvrWtAOxfVSC9f3";


    @Override
    public void writeToDatabase(List<Product> products) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement stmt = connection.createStatement();
            // Check if tables exist and create them if they don't
            createTablesIfNotExist(stmt);

            // Clear existing data
            clearExistingData(stmt);

            // Insert Products
            String insertPhoneSQL = "INSERT INTO phones (name, price, companyName, model, color, image) VALUES (?, ?, ?, ?, ?, ?)";
            String insertClothSQL = "INSERT INTO clothes (name, price, size, color, sex, image) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmtPhone = connection.prepareStatement(insertPhoneSQL);
            PreparedStatement pstmtCloth = connection.prepareStatement(insertClothSQL);

            for (Product product : products) {
                if (product instanceof Phone) {
                    Phone phone = (Phone) product;
                    pstmtPhone.setString(1, phone.getTitle());
                    pstmtPhone.setDouble(2, phone.getPrice());
                    pstmtPhone.setString(3, phone.getCompanyName());
                    pstmtPhone.setString(4, phone.getModel());
                    pstmtPhone.setString(5, phone.getColor());
                    pstmtPhone.setBytes(6, phone.getImage());
                    pstmtPhone.executeUpdate();
                } else if (product instanceof Cloth) {
                    Cloth cloth = (Cloth) product;
                    pstmtCloth.setString(1, cloth.getTitle());
                    pstmtCloth.setDouble(2, cloth.getPrice());
                    pstmtCloth.setString(3, cloth.getSize());
                    pstmtCloth.setString(4, cloth.getColor());
                    pstmtCloth.setString(5, cloth.getSex());
                    pstmtCloth.setBytes(6, cloth.getImage());
                    pstmtCloth.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> readFromDatabase() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement stmt = connection.createStatement();
            // Check if tables exist
            if (!checkIfTablesExist(stmt)) {
                return products;  // Return empty list if tables do not exist
            }

            // Read Phones
            String selectPhoneSQL = "SELECT * FROM phones";
            ResultSet rsPhones = stmt.executeQuery(selectPhoneSQL);
            while (rsPhones.next()) {
                String name = rsPhones.getString("name");
                double price = rsPhones.getDouble("price");
                String companyName = rsPhones.getString("companyName");
                String model = rsPhones.getString("model");
                String color = rsPhones.getString("color");
                byte[] image = rsPhones.getBytes("image");
                Phone phone = new Phone(name, (float) price, companyName, model, color);
                phone.setImage(image);
                products.add(phone);
            }

            // Read Clothes
            String selectClothSQL = "SELECT * FROM clothes";
            ResultSet rsClothes = stmt.executeQuery(selectClothSQL);
            while (rsClothes.next()) {
                String name = rsClothes.getString("name");
                double price = rsClothes.getDouble("price");
                String size = rsClothes.getString("size");
                String color = rsClothes.getString("color");
                String sex = rsClothes.getString("sex");
                byte[] image = rsClothes.getBytes("image");
                Cloth cloth = new Cloth(name, (float) price, size, color, sex);
                cloth.setImage(image);
                products.add(cloth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private void createTablesIfNotExist(Statement stmt) throws SQLException {
        String createPhoneTableSQL = "CREATE TABLE IF NOT EXISTS phones (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "companyName VARCHAR(255), " +
                "model VARCHAR(255), " +
                "color VARCHAR(50), " +
                "image BLOB)";
        String createClothTableSQL = "CREATE TABLE IF NOT EXISTS clothes (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "size VARCHAR(50), " +
                "color VARCHAR(50), " +
                "sex VARCHAR(50), " +
                "image BLOB)";
        stmt.executeUpdate(createPhoneTableSQL);
        stmt.executeUpdate(createClothTableSQL);
    }

    private boolean checkIfTablesExist(Statement stmt) throws SQLException {
        ResultSet rsPhones = stmt.executeQuery("SHOW TABLES LIKE 'phones'");
        boolean phonesExist = rsPhones.next();
        ResultSet rsClothes = stmt.executeQuery("SHOW TABLES LIKE 'clothes'");
        boolean clothesExist = rsClothes.next();
        return phonesExist && clothesExist;
    }

    private void clearExistingData(Statement stmt) throws SQLException {
        stmt.executeUpdate("DELETE FROM phones");
        stmt.executeUpdate("DELETE FROM clothes");
        stmt.executeUpdate("ALTER TABLE phones AUTO_INCREMENT = 1");
        stmt.executeUpdate("ALTER TABLE clothes AUTO_INCREMENT = 1");
    }

    private byte[] imageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }

    private BufferedImage byteArrayToImage(byte[] imageData) {
        try {
            InputStream is = new ByteArrayInputStream(imageData);
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
