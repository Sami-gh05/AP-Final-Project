package shop;

import account.Admin;
import account.User;
import database.java.DatabaseManager;
import database.java.ProductsDatabaseManager;
import database.java.UsersDatabaseManager;
import product.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Data {
    protected static List<User> users;
    protected static Admin admin;
    protected static List<Product> products;
    private static UsersDatabaseManager  usersDatabaseManager = new UsersDatabaseManager();
    private static ProductsDatabaseManager productsDatabaseManager = new ProductsDatabaseManager();


    public static void fillData(){
         users = usersDatabaseManager.readFromDatabase();
         products = productsDatabaseManager.readFromDatabase();
    }

    public static void fillDatabase(){

        usersDatabaseManager.writeToDatabase(users);
        productsDatabaseManager.writeToDatabase(products);

    }

    public static List<User> getUsers(){return users;}
    public static List<Product> getProducts(){return products;}


}
