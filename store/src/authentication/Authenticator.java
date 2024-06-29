package authentication;

public interface Authenticator {
    public void signUp(String userName, String pass, String name, String phoneNumber, String address);
    public void signIn(String userName, String pass);
    public void logOut(String userName);
}
