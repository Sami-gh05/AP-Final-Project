package account;

import authentication.Authentication;
import product.Product;

import java.util.*;

public class User implements Account{
    private String userName;
    private String password;
    //The structure of following Map collections is keys as product type and value as the ordered number
    private Map<Product, Integer> previousPurchases;
    private Map<Product, Integer> shoppingCard;
    private boolean isLoggedIn;
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        previousPurchases = new HashMap<>();
        shoppingCard = new HashMap<>();
    }

    @Override
    public void SignIn(String userName, String pass) {
        Authentication.signUpOrSignIn.userLogIn(userName, pass);
    }

    @Override
    public void signUp(String userName, String pass) {
        Authentication.signUpOrSignIn.userRegister(userName, pass);
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
        if(this.isLoggedIn)
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