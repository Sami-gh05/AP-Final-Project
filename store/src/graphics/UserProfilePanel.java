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

    private JTextField nameField;
    private JTextField usernameField;
    private JTextField addressField;
    private JTextField phoneNumberField;

    private JButton editButton;
    private JButton increaseBalanceButton;
    private JButton exitButton;

    public UserProfilePanel(User user) {
        this.user = user;

        setLayout(new GridLayout(5, 2));

        nameLabel = new JLabel("Name:");
        usernameLabel = new JLabel("Username:");
        addressLabel = new JLabel("Address:");
        phoneNumberLabel = new JLabel("Phone Number:");

        nameField = new JTextField(user.getName());
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
        add(editButton);
        add(increaseBalanceButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user.setName(nameField.getText());
                user.setAddress(addressField.getText());
                user.setPhoneNumber(phoneNumberField.getText());

            }
        });




    }
}




