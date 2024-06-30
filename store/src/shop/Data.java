package shop;

import account.Admin;
import account.User;
import database.java.ProductsDatabaseManager;
import database.java.UsersDatabaseManager;
import product.Cloth;
import product.Phone;
import product.Product;

import java.util.ArrayList;
import java.util.List;


public class Data {
    protected static List<User> users = new ArrayList<>();
    protected static Admin admin;
    protected static List<Product> products = new ArrayList<>();
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

    public static void addProduct(Product product)
    {
        products.add(product);
    }

    public static void removeProduct(Product product) {
        products.remove(product);
    }
    public static void addUser(User user){
        users.add(user);
    }
}
