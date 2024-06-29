package authentication;

import shop.Data;


public abstract class AdminAuthenticator extends Data implements Authenticator {
    private String message;

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
