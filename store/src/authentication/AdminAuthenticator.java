package authentication;

import account.Admin;
import account.User;
import shop.Data;


public class AdminAuthenticator extends Data implements Authenticator {
    private String message;

    @Override
    public void signUp(String userName, String pass, String name, String phoneNumber, String address){
        setMessage("THE STORE HAS ONLY ONE ADMIN");
    }
    @Override
    public void signIn(String userName, String pass) {
        if(Admin.getAdminPass().equals(pass) && userName.equals(Admin.getAdminID())){
            Admin.setLoginStatus(true);
            setMessage("LOGGED IN SUCCESSFULLY");
        }
        else
            setMessage("INCORRECT INPUTS");

    }

    @Override
    public void logOut(String userName) {
        Admin.setLoginStatus(false);
        setMessage("LOGGED OUT SUCCESSFULLY");
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
