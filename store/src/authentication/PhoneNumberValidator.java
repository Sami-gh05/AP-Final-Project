package authentication;

public class PhoneNumberValidator {
    private static int length1 = 13, length2 = 11;
    private static String code1 = "+98", code2 = "0";
    private boolean isValidated;
    public PhoneNumberValidator(String phoneNumber){
        this.isValidated = validation(phoneNumber);
    }
    public static boolean validation(String phoneNumber){
        String phoneCode1 = phoneNumber.substring(0, 3);
        String phoneCode2 = phoneNumber.substring(0, 1);
        if(phoneCode1.equals(code1)) {
            if(phoneNumber.length() == length1)
                return true;
            else
                return false;
        }
        else if(phoneCode2.equals(code2)){
            if (phoneNumber.length() == length2)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    public String getMessage(){
        return "Phone number is not validated";
    }

    public boolean isValidated() {
        return isValidated;
    }
}
