public class UserAuthenticator extends Data implements Authenticator{
    public void signUp(String userName, String pass){
        for(User user: super.users){
            if(user.getUserName().equals(userName)){
                System.out.println("User name already exists");
                return;
            }
        }
        super.users.add(new User(userName, pass));
        System.out.println("YOU WERE SIGNED UP SUCCESSFULLY");
    }
    public void SignIn(String userName, String pass){
        for(User user: super.users){
            if(user.getUserName().equals(userName)){
                if(user.getPassword().equals(pass)) {
                    user.setLoginStatus(true);
                    return;
                }
                else{
                    System.out.println("WRONG PASSWORD");
                    return;
                }
            }
        }
        System.out.println("User name not found");
    }
    public void logOut(String userName){
        for(User user: super.users){
            if(user.getUserName().equals(userName)){
                user.setLoginStatus(false);
                return;
            }
        }
    }

}
