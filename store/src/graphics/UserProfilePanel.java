package graphics;

import account.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfilePanel {
    private User user;
    private JFrame mainPanel;
    private JPanel root, infoPanel, previousPurchases;

    private JLabel nameLabel;
    private JLabel userNameLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel presentPassLabel, newPassLabel;
    private JLabel pageLabel, changeBalanceLabel, presentBalanceLabel;

    private JTextField nameField;
    private JTextField userNameField, presentPassField, newPassField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField changeBalanceField, presentBalanceField;

    private JButton comfirmButton, exitButton;

    public UserProfilePanel(User user, JFrame mainPanel, JPanel root) {
        this.user = user;
        this.mainPanel = mainPanel;
        this.root = root;
    }
    public JPanel getInfoPanel(){
        infoPanel = new JPanel();
        infoPanel.setLayout(null);

        nameLabel = new JLabel("Name:");
        userNameLabel = new JLabel("Username:");
        addressLabel = new JLabel("Address:");
        phoneNumberLabel = new JLabel("Phone Number:");
        presentPassLabel = new JLabel("Present password:");
        newPassLabel = new JLabel("New password:");
        presentBalanceLabel = new JLabel("Balance:");
        changeBalanceLabel = new JLabel("Balance change(+/-) amount:");

        nameField = new JTextField(user.getName());
        presentPassField = new JTextField();
        newPassField = new JTextField();
        userNameField = new JTextField(user.getUserName());
        addressField = new JTextField(user.getAddress());
        phoneNumberField = new JTextField(user.getPhoneNumber());
        presentBalanceField = new JTextField(String.valueOf(user.getBalance()) + "$");
        changeBalanceField = new JTextField();

        pageLabel = new JLabel("User Profile Info");
        infoPanel.add(pageLabel);

        comfirmButton = new JButton("Confirm");

        exitButton = new JButton("Exit");

        //setting the positions
        nameLabel.setBounds(25, 25, 100, 50);
        nameField.setBounds(325, 25, 100, 50);
        userNameLabel.setBounds(25, 100, 100, 50);
        userNameField.setBounds(325, 100, 100, 50);
        phoneNumberLabel.setBounds(25, 175, 100, 50);
        phoneNumberField.setBounds(325, 175, 100, 50);
        addressLabel.setBounds(25, 250, 100, 50);
        addressField.setBounds(325, 250, 100, 50);
        presentPassLabel.setBounds(25, 325, 200, 50);
        presentPassField.setBounds(325, 325, 100, 50);
        newPassLabel.setBounds(25, 400, 100, 50);
        newPassField.setBounds(325, 400, 100, 50);

        presentBalanceLabel.setBounds(25,475,100, 50 );
        presentBalanceField.setBounds(325,475,100, 50 );
        changeBalanceLabel.setBounds(25,550,250, 50 );
        changeBalanceField.setBounds(325,550,100, 50 );

        exitButton.setBounds(25, 625, 100, 50);
        comfirmButton.setBounds(325, 625, 100, 50);


        // Add components to the panel
        infoPanel.add(nameLabel);
        infoPanel.add(nameField);
        infoPanel.add(userNameLabel);
        infoPanel.add(userNameField);
        infoPanel.add(addressLabel);
        infoPanel.add(addressField);
        infoPanel.add(phoneNumberLabel);
        infoPanel.add(phoneNumberField);
        infoPanel.add(presentPassLabel);
        infoPanel.add(presentPassField);
        infoPanel.add(newPassLabel);
        infoPanel.add(newPassField);
        infoPanel.add(presentBalanceLabel);
        infoPanel.add(presentBalanceField);
        infoPanel.add(changeBalanceLabel);
        infoPanel.add(changeBalanceField);
        infoPanel.add(exitButton);
        infoPanel.add(comfirmButton);


        comfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nameField.getText().equals(user.getName())){
                    user.changeName(nameField.getText());
                    JOptionPane.showMessageDialog(nameLabel, user.getMessage());
                }
                if(!userNameField.getText().equals(user.getUserName())){
                    user.changeUserName(userNameField.getText());
                    JOptionPane.showMessageDialog(userNameLabel, user.getMessage());
                }
                if(!phoneNumberField.getText().equals(user.getPhoneNumber())){
                    user.changePhoneNumber(phoneNumberField.getText());
                    JOptionPane.showMessageDialog(phoneNumberLabel, user.getMessage());
                }
                if(!addressField.getText().equals(user.getAddress())){
                    user.changeAddress(addressField.getText());
                    JOptionPane.showMessageDialog(addressLabel, user.getMessage());
                }
                user.changePassword(presentPassField.getText(), newPassField.getText());
                JOptionPane.showMessageDialog(newPassLabel, user.getMessage());
                user.changeBalance(Float.parseFloat(changeBalanceField.getText()));
                JOptionPane.showMessageDialog(changeBalanceLabel, user.getMessage());

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.getContentPane().removeAll();
                mainPanel.add(root);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        return infoPanel;
    }

    /*public JPanel getPreviousPurchasesPanel(){
        //TODO needs scroll bar
    }*/
}




