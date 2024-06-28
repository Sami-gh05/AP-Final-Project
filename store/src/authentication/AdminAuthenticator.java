

public class AdminAuthenticator extends Data implements Authenticator {
    @Override
    public void signUp(String userName, String pass) {
        System.out.println("THERE CAN BE ONLY ONE ADMIN");
    }

    @Override
    public void SignIn(String userName, String pass) {
        if(admin.getAdminPass().equals(pass) && userName.equals(admin.getAdminID()))
            admin.setLoginStatus(true);
    }

    @Override
    public void logOut(String userName) {
        admin.setLoginStatus(false);
    }
}
