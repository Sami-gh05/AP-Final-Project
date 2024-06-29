package graphics;
import account.User;
import shop.Data;

import javax.swing.*;

public class MainGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private SignUp_SignIn signUpSignIn;
    private UserMainPanel userMainPanel;

    public MainGUI() {
        frame = new JFrame("Store Application");
        mainPanel = new JPanel();
        Data.fillData();
       //TODO


        SwingUtilities.invokeLater(() -> {
            SignUp_SignIn frame = new SignUp_SignIn();
            frame.setTitle("Sign Up/Sign In");
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

        //signUpSignIn = new SignUp_SignIn();
        mainPanel.add(signUpSignIn.getPanel());

        frame.setVisible(true);
    }





    public void showUserMainGUI(User user) {
        mainPanel.removeAll();
       userMainPanel = new UserMainPanel(this,user);
        mainPanel.add(userMainPanel.getPanel());
        frame.revalidate();
        frame.repaint();
    }

    public void refreshFrame() {
        frame.revalidate();
        frame.repaint();
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }


}
