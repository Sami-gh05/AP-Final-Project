package account;

import authentication.PhoneNumberValidator;
import product.Product;

import java.util.*;

public class User {
    private String userName, password; //these two fields are initialized when signing up
    private String name, phoneNumber, address; //these three fields will be initialized and edited in ProfilePanel after signing upfiel
    private float balance;
    //The structure of following Map collections is keys as product type and value as the number of orders
    private Map<Product, Integer> previousPurchases;
    private Map<Product, Integer> shoppingCard;
    private boolean loginStatus;
    public User(String userName, String password, String name, String phoneNumber, String address){
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;

        previousPurchases = new HashMap<>();
        shoppingCard = new HashMap<>();
    }


    public String getUserName() {
        return userName;
    }

    public void changeUserName(String password, String userName) {
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

    public void changePassword(String presentPass, String newPass) {
        if(this.password.equals(presentPass))
            this.password = newPass;
        else
            System.out.println("ACCESS DENIED");
    }
    public void changeName(String name) {
        if(loginStatus)
            this.name = name;
        else
            System.out.println("ACCESS DENIED");
    }
    public void changeAddress(String address) {
        if(loginStatus)
            this.address = address;
        else
            System.out.println("ACCESS DENIED");
    }
    public void changePhoneNumber(String phoneNumber) {
        if(loginStatus){
            if(PhoneNumberValidator.validation(phoneNumber))
                this.phoneNumber = phoneNumber;
            else
                System.out.println("Invalid phone Number");
        }
        else
            System.out.println("ACCESS DENIED");
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Map<Product, Integer> getShoppingCard() {

        return shoppingCard;

    }

    public Map<Product, Integer> getPreviousPurchases() {

        return previousPurchases;

    }


    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
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
