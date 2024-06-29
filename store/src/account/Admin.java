package account;
public class Admin {
    private static String adminID = "Administrator", adminPass = "StoreKeeper";
    private static String message;
    private static boolean loginStatus;
    private static float storeBalance = 0;


    public static void setAdminPass(String presentPass, String newPass) {
        if(presentPass.equals(getAdminPass()))
            adminPass = newPass;
        else
            setMessage("WRONG PASSWORD");
    }

    public static String getAdminPass() {
        return adminPass;
    }

    public  static void setLoginStatus(boolean Status) {
        loginStatus = Status;
    }
    public  static boolean getLoginStatus(){
        return loginStatus;
    }

    public static String getAdminID() {
        return adminID;
    }

    public static void storeBalanceIncrease(float amount){
        storeBalance += amount;
    }

    public static void setMessage(String message) {
        Admin.message = message;
    }

    public static String getMessage() {
        return message;
    }
}
