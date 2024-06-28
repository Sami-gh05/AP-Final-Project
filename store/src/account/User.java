package account;

import authentication.Authentication;
import product.Product;

import java.util.*;

import java.util.*;

public class User {
    private String userName, password; //these two fields are initialized when signing up
    private String name, phoneNumber, address; //these three fields will be initialized and edited in ProfilePanel after signing upfiel
    private float balance;
    //The structure of following Map collections is keys as product type and value as the number of orders
    private Map<Product, Integer> previousPurchases;
    private Map<Product, Integer> shoppingCard;
    private boolean loginStatus;
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        previousPurchases = new HashMap<>();
        shoppingCard = new HashMap<>();
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String password,String userName) {
        if(this.password.equals(password))
            this.userName = userName;
        else
            System.out.println("ACCESS DENIED");
    }

    public String getPassword() {
        if(loginStatus)
            return password;
        else
            return "ACCESS DENIED";
    }

    public void setPassword(String presentPass, String newPass) {
        if(this.password.equals(presentPass))
            this.password = newPass;
        else
            System.out.println("ACCESS DENIED");
    }

    public Map<Product, Integer> getShoppingCard() {

        return shoppingCard;

    }

    public Map<Product, Integer> getPreviousPurchases() {

        return previousPurchases;

    }

    public void setLoginStatus(boolean looginStatus) {
        this.loginStatus = looginStatus;
    }
    public boolean getLoginStatus(){
        return loginStatus;
    }

    public float getBalance() {
        return balance;
    }
    public void balanceIncrease(float amount){
        this.balance += amount;
    }
    public void balanceDecrease(float amount){
        this.balance -= amount;
    }
    //adding a product into shopping card:
    public void addTOShoppingCard(Product product, int amount){
        this.shoppingCard.put(product, amount);
    }
    //adding a product into previous purchases:
    public void addToPreviousPurchases(Product product,int amount){
        this.previousPurchases.put(product, amount);
    }
    //removing a product from shopping card:
    public void removeFromShoppingCard(){
        //TODO
    }
    //removing a product from previous purchases:
    public void removeFromPreviousPurchases(){
        //TODO
    }


}