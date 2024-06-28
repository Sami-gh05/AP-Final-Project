package authentication;

import account.User;

import java.util.HashSet;
import java.util.Set;


public class Authentication {
    public static class signUpOrSignIn{
        private static Set<User> users = new HashSet<>();


        public static void userRegister(String userName, String passWord){
            //TODO
        }
        public static void userLogIn(String userName, String passWord){
            //TODO
        }

        public static void AdminLogIN(String userName, String passWord){
            //TODO
        }
    }

}
