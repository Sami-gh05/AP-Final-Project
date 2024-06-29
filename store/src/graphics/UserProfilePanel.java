package graphics;

import account.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfilePanel extends JPanel {
    private User user;

    private JLabel nameLabel;
    private JLabel usernameLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel pastPassLabel, newPassLabel;

    private JTextField nameField;
    private JTextField usernameField, presentPassField, pastPassField;
    private JTextField addressField;
    private JTextField phoneNumberField;

    private JButton editButton;
    private JButton increaseBalanceButton;
    private JButton exitButton;

    public UserProfilePanel(User user) {
        this.user = user;

        setLayout(new GridLayout(6, 2));

        nameLabel = new JLabel("Name:");
        usernameLabel = new JLabel("Username:");
        addressLabel = new JLabel("Address:");
        phoneNumberLabel = new JLabel("Phone Number:");
        pastPassLabel = new JLabel("Past password:");
        newPassLabel = new JLabel("New password:");

        nameField = new JTextField(user.getName());
        presentPassField = new JTextField(user.getPassword());
        pastPassField = new JTextField();
        usernameField = new JTextField(user.getUserName());
        addressField = new JTextField(user.getAddress());
        phoneNumberField = new JTextField(user.getPhoneNumber());

        editButton = new JButton("Edit Profile");
        increaseBalanceButton = new JButton("Increase Balance");
        exitButton = new JButton("Exit");

        // Add components to the panel
        add(nameLabel);
        add(nameField);
        add(usernameLabel);
        add(usernameField);
        add(addressLabel);
        add(addressField);
        add(phoneNumberLabel);
        add(phoneNumberField);
        add(pastPassLabel);
        add(pastPassField);
        add(newPassLabel);
        add(presentPassField);
        add(editButton);
        add(increaseBalanceButton);


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user.changeName(nameField.getText());
                user.changeAddress(addressField.getText());
                user.changePhoneNumber(phoneNumberField.getText());
                user.changePassword(pastPassField.getText(), presentPassField.getText());
                user.changeUserName(pastPassField.getText(), usernameField.getText());

            }
        });




    }
}




