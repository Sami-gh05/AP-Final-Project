package graphics;

import authentication.AdminAuthenticator;
import authentication.UserAuthenticator;
import shop.Data;

import javax.swing.*;
import java.awt.*;

public class SignUp_SignIn extends JFrame {
    private final JPanel root = new JPanel(new GridBagLayout());
    private final JButton loginButton1 = new JButton(), loginButton2 = new JButton(), loginButton3 = new JButton();
    private final JLabel usernameLabel = new JLabel("Username:");
    private final JTextField usernameField = new JTextField(20);
    private final JLabel passwordLabel = new JLabel("Password:");
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JButton backButton = new JButton("Back");
    private final JLabel nameLabel = new JLabel("Name:");
    private final JTextField nameField = new JTextField(20);
    private final JLabel phoneLabel = new JLabel("Phone:");
    private final JTextField phoneField = new JTextField(20);
    private final JLabel addressLabel = new JLabel("Address:");
    private final JTextField addressField = new JTextField(20);

    public SignUp_SignIn() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel welcomeLabel = new JLabel("Welcome to our store!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        root.add(welcomeLabel, gbc);

        JButton signUp = new JButton("User Sign Up");
        signUp.addActionListener(e -> showSignUpFields());
        gbc.gridy = 1;
        root.add(signUp, gbc);

        JButton signIn = new JButton("User Sign In");
        signIn.addActionListener(e -> showUserLoginFields("User Sign In"));
        gbc.gridy = 2;
        root.add(signIn, gbc);

        JButton admin = new JButton("Admin Login");
        admin.addActionListener(e -> showUserLoginFields("Admin login"));
        gbc.gridy = 3;
        root.add(admin, gbc);

        loginButton1.setVisible(false);
        loginButton2.setVisible(false);
        loginButton3.setVisible(false);

        loginButton1.setFocusable(false);
        loginButton2.setFocusable(false);
        loginButton3.setFocusable(false);
        backButton.setFocusable(false);


        backButton.addActionListener(e -> {showInitialFields();
            usernameField.setText("");
            passwordField.setText("");
            nameField.setText("");
            phoneField.setText("");
            addressField.setText("");
        });
        backButton.setVisible(false);
        add(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


        //making the panel
        this.setTitle("Sign Up/Sign In");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void showInitialFields() {
        root.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel welcomeLabel = new JLabel("Welcome to our store!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        root.add(welcomeLabel, gbc);

        JButton signUp = new JButton("User Sign Up");
        signUp.addActionListener(e -> showSignUpFields());
        gbc.gridy = 1;
        root.add(signUp, gbc);

        JButton signIn = new JButton("User Sign In");
        signIn.addActionListener(e -> showUserLoginFields("User Sign In"));
        gbc.gridy = 2;
        root.add(signIn, gbc);

        JButton admin = new JButton("Admin Login");
        admin.addActionListener(e -> showAdminLoginFields("Admin login"));
        gbc.gridy = 3;
        root.add(admin, gbc);

        admin.setFocusable(false);
        signIn.setFocusable(false);
        signUp.setFocusable(false);

        revalidate();
        repaint();
    }

    private void showUserLoginFields(String buttonText) {
        UserAuthenticator userAuthentication = new UserAuthenticator();

        root.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel welcomeLabel = new JLabel("Welcome to our store!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        root.add(welcomeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        root.add(usernameLabel, gbc);
        gbc.gridx = 1;
        root.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        root.add(passwordLabel, gbc);
        gbc.gridx = 1;
        root.add(passwordField, gbc);

        loginButton1.setText(buttonText);
        loginButton1.setVisible(true);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        root.add(loginButton1, gbc);

        //for user log up
        loginButton1.addActionListener(e -> {
            try{
                //it checks whether the user exists or not
                userAuthentication.signIn(  usernameField.getText(),
                        passwordField.getText());
            }
            catch (Exception exception){
                userAuthentication.setMessage("PLEASE FILL ALL THE GAPS CORRECTLY");
            }
            JOptionPane.showMessageDialog(this, userAuthentication.getMessage());
            if(userAuthentication.getMessage().equals("LOGGED IN SUCCESSFULLY")){
                new UserMainPanel(userAuthentication.getUser(usernameField.getText()), Data.getProducts());
                usernameField.setText("");
                passwordField.setText("");
                this.dispose();
            }
        });

        backButton.setVisible(true);
        gbc.gridy = 4;
        root.add(backButton, gbc);

        revalidate();
        repaint();
    }

    private void showAdminLoginFields(String buttonText) {
        AdminAuthenticator adminAuthentication = new AdminAuthenticator();

        root.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel welcomeLabel = new JLabel("Welcome to our store!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        root.add(welcomeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        root.add(usernameLabel, gbc);
        gbc.gridx = 1;
        root.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        root.add(passwordLabel, gbc);
        gbc.gridx = 1;
        root.add(passwordField, gbc);

        loginButton2.setText(buttonText);
        loginButton2.setVisible(true);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        root.add(loginButton2, gbc);

        //for admin log in
        loginButton2.addActionListener(e -> {
            try{
                adminAuthentication.signIn(  usernameField.getText(),
                        passwordField.getText());
            }
            catch (Exception exception){
                adminAuthentication.setMessage("PLEASE FILL ALL THE GAPS CORRECTLY");
            }
            JOptionPane.showMessageDialog(this, adminAuthentication.getMessage());
            if(adminAuthentication.getMessage().equals("LOGGED IN SUCCESSFULLY")){
                usernameField.setText("");
                passwordField.setText("");
                new AdminMainPanel(Data.getProducts());
                this.dispose();
            }
        });

        backButton.setVisible(true);
        gbc.gridy = 4;
        root.add(backButton, gbc);

        revalidate();
        repaint();
    }

    private void showSignUpFields() {
        UserAuthenticator authentication = new UserAuthenticator();

        root.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel welcomeLabel = new JLabel("Welcome to our store!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        root.add(welcomeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        root.add(usernameLabel, gbc);
        gbc.gridx = 1;
        root.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        root.add(passwordLabel, gbc);
        gbc.gridx = 1;
        root.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        root.add(nameLabel, gbc);
        gbc.gridx = 1;
        root.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        root.add(phoneLabel, gbc);
        gbc.gridx = 1;
        root.add(phoneField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        root.add(addressLabel, gbc);
        gbc.gridx = 1;
        root.add(addressField, gbc);

        loginButton3.setText("Sign Up");
        loginButton3.setVisible(true);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        root.add(loginButton3, gbc);

        //for user sign up
        loginButton3.addActionListener(e -> {
            try{
                //it checks whether the user is repetitive or not
                authentication.signUp(  usernameField.getText(),
                        passwordField.getText(),
                        nameField.getText(),
                        phoneField.getText(),
                        addressField.getText());
            }
            catch (Exception exception){
                authentication.setMessage("PLEASE FILL ALL THE GAPS CORRECTLY");
            }
            JOptionPane.showMessageDialog(this, authentication.getMessage());
            if(authentication.getMessage().equals("SIGNED UP SUCCESSFULLY")){
                usernameField.setText("");
                passwordField.setText("");
                nameField.setText("");
                phoneField.setText("");
                addressField.setText("");
                new UserMainPanel(authentication.getUser(usernameField.getText()), Data.getProducts());
                this.dispose();
            }
        });

        backButton.setVisible(true);
        gbc.gridy = 7;
        root.add(backButton, gbc);

        revalidate();
        repaint();
    }



    public Component getPanel() {
        return root;
    }
}