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
    private String message;
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
    public String getPassword() {
        return password;
    }

    public void changeUserName(String userName) {
        if(loginStatus){
            this.userName = userName;
            setMessage("User name updated");
        }
    }

    public void changePassword(String presentPass, String newPass) {
        if(presentPass.equals(this.getPassword())){
            this.password = newPass;
            setMessage("Password updated");
        }
        else
            setMessage("Please enter correct password");
    }
    public void changeName(String name) {
        if(loginStatus){
            this.name = name;
            setMessage("Name updated");
        }

    }
    public void changeAddress(String address) {
        if(loginStatus){
            this.address = address;
            setMessage("Address updated");
        }

    }
    public void changePhoneNumber(String phoneNumber) {
        if(loginStatus){
            if(PhoneNumberValidator.validation(phoneNumber)){
                this.phoneNumber = phoneNumber;
                setMessage("Phone number updated");
            }
            else
                setMessage("Enter valid phone number");
        }
    }
    public void changeBalance(float amount){
        this.balance += amount;
        setMessage("Balance update");
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

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
