package authentication;

import shop.Data;
import account.User;

public class UserAuthenticator extends Data implements Authenticator{
    private User selectedUser;
    private String message;
    public void signUp(String userName, String pass, String name, String phoneNumber, String address){
        if(isUser(userName)){
            setMessage("USER NAME ALREADY EXISTS");
            return;
        }
        if(PhoneNumberValidator.validation(phoneNumber)){
            super.users.add(new User(userName, pass, name, phoneNumber, address));
            setMessage("SIGNED UP SUCCESSFULLY");
        }
        else
            setMessage("INVALID PHONE NUMBER");


    }
    public void signIn(String userName, String pass){
        if(isUser(userName)){
            if(selectedUser.getPassword().equals(pass)) {
                selectedUser.setLoginStatus(true);
                setMessage("LOGGED IN SUCCESSFULLY");
                return;
            }
            else{
                setMessage("WRONG PASSWORD");
                return;
            }
        }
        setMessage("USER NAME NOT FOUND");
    }
    public void logOut(String userName){
        if(isUser(userName)){
            selectedUser.setLoginStatus(false);
            setMessage("LOGGED OUT SUCCESSFULLY");
        }
    }
    public boolean isUser(String userName){
        for(User user : super.users){
            if(user.getUserName().equals(userName)){
                selectedUser = user;
                return true;
            }
        }
        return false;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
