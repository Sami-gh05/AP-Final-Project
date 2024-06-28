public interface Authenticator {
    public void signUp(String userName, String pass);
    public void SignIn(String userName, String pass);
    public void logOut(String userName);
}
