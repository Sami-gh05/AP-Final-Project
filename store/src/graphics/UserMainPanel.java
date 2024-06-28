package graphics;

import account.User;

import javax.swing.*;

public class UserMainPanel {
    private JPanel panel;
    private User user;
    private MainGUI mainGUI;
    private UserProfilePanel userProfilePanel;
    private UserShoppingCardPanel userShoppingCardPanel;

    //TODO

    public UserMainPanel(MainGUI mainGUI,User user) {
        panel = new JPanel();
        this.user = user;
        this.mainGUI = mainGUI;

        //TODO

    }

    //TODO

    public JPanel getPanel() {
        return panel;
    }

    public void showUserProfilePanel() {

        //this method should use in an actionlistener that we want to show user profile.


        if (userProfilePanel == null) {
            userProfilePanel = new UserProfilePanel(this, user);
        }
        mainGUI.getMainPanel().removeAll();
        mainGUI.getMainPanel().add(userProfilePanel.getPanel());
        mainGUI.refreshFrame();
    }


    //TODO : make showusershoppingpanel method and set the class of that up


    public void showUserMainPanel() {
        mainGUI.getMainPanel().removeAll();
        mainGUI.getMainPanel().add(this.getPanel());
        mainGUI.refreshFrame();
    }
}
