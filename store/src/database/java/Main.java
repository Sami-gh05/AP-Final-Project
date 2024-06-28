package database.java;

import product.Product;
import product.Cloth;
import product.Phone;

import account.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create some products
        Cloth cloth1 = new Cloth("T-Shirt", 19.99f, "M", "Red", "Unisex");
        Cloth cloth2 = new Cloth("Jeans", 49.99f, "L", "Blue", "Male");
        Phone phone1 = new Phone("Smartphone", 699.99f, "BrandA", "ModelX", "Black");
        Phone phone2 = new Phone("Feature Phone", 49.99f, "BrandB", "ModelY", "White");

        // Create users and add products to their previous purchases and shopping cart
        User user1 = new User("john_doe", "password123");
        user1.addToPreviousPurchases(cloth1, 1);
        user1.addToPreviousPurchases(phone1, 2);
        user1.addTOShoppingCard(cloth2, 3);
        user1.addTOShoppingCard(phone2, 1);

        User user2 = new User("jane_doe", "password456");
        user2.addToPreviousPurchases(cloth2, 2);
        user2.addToPreviousPurchases(phone2, 1);
        user2.addTOShoppingCard(cloth1, 2);
        user2.addTOShoppingCard(phone1, 1);

        // Create a list of users
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        // Create an instance of DatabaseManager
        DatabaseManager dbManager = new UsersDatabaseManager();

        // Write users to the database
        dbManager.writeToDatabase(users);

        // Read users from the database
        List<User> retrievedUsers = dbManager.readFromDatabase();

        // Print retrieved users
        for (User user : retrievedUsers) {
            System.out.println("Username: " + user.getUserName());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Previous Purchases:");
            for (Map.Entry<Product, Integer> entry : user.getPreviousPurchases().entrySet()) {
                Product product = entry.getKey();
                int amount = entry.getValue();
                System.out.println("  - " + product.getTitle() + ": " + amount + " units");
            }
            System.out.println("Shopping Cart:");
            for (Map.Entry<Product, Integer> entry : user.getShoppingCard().entrySet()) {
                Product product = entry.getKey();
                int amount = entry.getValue();
                System.out.println("  - " + product.getTitle() + ": " + amount + " units");
            }
            System.out.println();
        }
    }
}
