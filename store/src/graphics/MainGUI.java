package graphics;
import account.User;
import shop.Data;

import javax.swing.*;

public class MainGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private SignUp_SignIn signUpSignIn;


    public MainGUI() {
        frame = new JFrame("Store Application");
        //mainPanel = new JPanel();
        Data.fillData();
       //TODO




        signUpSignIn = new SignUp_SignIn();
        //mainPanel.add(signUpSignIn.getPanel());
        //frame.setVisible(true);
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
