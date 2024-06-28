package graphics;

import account.User;

import javax.swing.*;

public class UserProfilePanel {

    private JPanel panel;
    private JButton exitButton;
    private UserMainPanel userMainPanel;
    private User user;

    public UserProfilePanel(UserMainPanel userMainPanel, User user) {
        panel = new JPanel();
        this.user = user;
        this.userMainPanel = userMainPanel;
        /*
        exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userMainPanel.showUserMainPanel();
            }
        });

         */


    }

    public JPanel getPanel() {
        return panel;
    }
}
