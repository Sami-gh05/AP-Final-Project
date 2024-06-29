package authentication;

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
        if(admin.getAdminPass().equals(pass) && userName.equals(admin.getAdminID())){
            admin.setLoginStatus(true);
            setMessage("LOGGED IN SUCCESSFULLY");
        }

    }

    @Override
    public void logOut(String userName) {
        admin.setLoginStatus(false);
        setMessage("LOGGED OUT SUCCESSFULLY");
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
