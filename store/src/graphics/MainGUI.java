package graphics;
import account.User;

import javax.swing.*;

public class MainGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private SignUp_SignIn signUpSignIn;
    private UserMainPanel userMainPanel;

    public MainGUI() {
        frame = new JFrame("Store Application");
        mainPanel = new JPanel();
       //TODO

        signUpSignIn = new SignUp_SignIn(this);
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
