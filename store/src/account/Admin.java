public class Admin {
    private String adminID = "Administrator";
    private String adminPass = "StoreKeeper";
    private boolean loginStatus;

    public void setAdminPass(String presentPass, String newPass) {
        if(presentPass.equals(this.getAdminPass()))
            this.adminPass = adminPass;
        else
            System.out.println("ACCESS DENIED");
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

}
