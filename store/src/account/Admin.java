package account;
public class Admin {
    private static String adminID = "Administrator", adminPass = "StoreKeeper";
    private static String message;
    private boolean loginStatus;
    private static float storeBalance = 0;


    public void setAdminPass(String presentPass, String newPass) {
        if(presentPass.equals(this.getAdminPass()))
            adminPass = newPass;
        else
            setMessage("WRONG PASSWORD");
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
    public boolean getLoginStatus(){
        return loginStatus;
    }

    public String getAdminID() {
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
