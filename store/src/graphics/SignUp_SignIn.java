package graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp_SignIn {
    private MainGUI mainGUI;
    private JPanel panel;

    //TODO buttons and textfields and sub panels.

    public SignUp_SignIn(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        panel = new JPanel();

        //TODO

    }

    /* suitable actionListener using authentication class,
       and then, according to user or admin, use mainGUI.showUserMainGUI or mainGUI.showAdminMainGUI
    */


    public JPanel getPanel() {
        return panel;
    }
}
