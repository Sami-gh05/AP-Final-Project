package graphics;
import account.User;
import database.java.DatabaseManager;
import database.java.ProductsDatabaseManager;
import database.java.UsersDatabaseManager;
import product.Cloth;
import product.Phone;
import product.Product;
import shop.Data;

import javax.swing.*;

public class MainGUI extends Data{
    private JFrame frame;
    private JPanel mainPanel;
    private SignUp_SignIn signUpSignIn;


    public MainGUI() {
        frame = new JFrame("Store Application");

        Data.fillData();





        //everything in the graphics start from here
        signUpSignIn = new SignUp_SignIn();

    }
    public static void main(String[] args){
        MainGUI main = new MainGUI();
    }



    public void refreshFrame() {
        frame.revalidate();
        frame.repaint();
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }


}
