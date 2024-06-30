package graphics;
import account.User;
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
        //mainPanel = new JPanel();
        //Data.fillData();
        //TODO


        User user1 = new User("sam.gh", "8490", "Sam", "09372330129", "Tehran");
        User user2 = new User("yas.gh", "1234", "Yas", "+989037026479", "Pardis");

        users.add(user1);
        users.add(user2);

        Product product1 = new Cloth("cloth1", 100, "100", "red", "M");
        products.add(product1);
        Product product2 = new Phone("phone1", 100, "100", "red", "M");
        products.add(product2);
        Product product3 = new Cloth("cloth2", 100, "100", "red", "M");
        products.add(product3);
        Product product4 = new Phone("phone2", 100, "100", "red", "M");
        products.add(product4);


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
