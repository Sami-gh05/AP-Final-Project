package graphics;

import javax.swing.*;
import java.awt.*;

public class SignUp_SignIn extends JFrame {
    private final JPanel root = new JPanel(new GridBagLayout());
    private final JButton loginButton = new JButton();
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

        JButton signUp = new JButton("Sign Up");
        signUp.addActionListener(e -> showSignUpFields());
        gbc.gridy = 1;
        root.add(signUp, gbc);

        JButton signIn = new JButton("Sign In");
        signIn.addActionListener(e -> showLoginFields("Sign In"));
        gbc.gridy = 2;
        root.add(signIn, gbc);

        JButton admin = new JButton("Admin Login");
        admin.addActionListener(e -> showLoginFields("Admin login"));
        gbc.gridy = 3;
        root.add(admin, gbc);

        loginButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged in successfully!");
            new ProductsPanel();
            this.dispose();
        });
        loginButton.setVisible(false);

        backButton.addActionListener(e -> showInitialFields());
        backButton.setVisible(false);

        add(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
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

        JButton signUp = new JButton("Sign Up");
        signUp.addActionListener(e -> showSignUpFields());
        gbc.gridy = 1;
        root.add(signUp, gbc);

        JButton signIn = new JButton("Sign In");
        signIn.addActionListener(e -> showLoginFields("Sign In"));
        gbc.gridy = 2;
        root.add(signIn, gbc);

        JButton admin = new JButton("Admin Login");
        admin.addActionListener(e -> showLoginFields("Admin login"));
        gbc.gridy = 3;
        root.add(admin, gbc);

        revalidate();
        repaint();
    }

    private void showLoginFields(String buttonText) {
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

        loginButton.setText(buttonText);
        loginButton.setVisible(true);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        root.add(loginButton, gbc);

        backButton.setVisible(true);
        gbc.gridy = 4;
        root.add(backButton, gbc);

        revalidate();
        repaint();
    }

    private void showSignUpFields() {
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

        loginButton.setText("Sign Up");
        loginButton.setVisible(true);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        root.add(loginButton, gbc);

        backButton.setVisible(true);
        gbc.gridy = 7;
        root.add(backButton, gbc);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignUp_SignIn frame = new SignUp_SignIn();
            frame.setTitle("Sign Up/Sign In");
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public Component getPanel() {
        return root;
    }
}