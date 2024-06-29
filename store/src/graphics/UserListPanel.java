package graphics;
import account.User;
import product.Cloth;
import product.Phone;
import product.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserListPanel extends JFrame {
    private JPanel userPanel;
    private JPanel detailPanel;
    private CardLayout cardLayout;
    private List<User> users;

    public UserListPanel(List<User> users) {
        this.users = users;
        setTitle("User List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(userPanel);
        add(scrollPane, "UserList");

        detailPanel = new JPanel();
        detailPanel.setLayout(new BorderLayout());
        add(detailPanel, "DetailView");

        loadUserList();
    }

    private void loadUserList() {
        for (User user : users) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel userNameLabel = new JLabel("Username: " + user.getUserName());
            JLabel passwordLabel = new JLabel("Password: " + user.getPassword());

            panel.add(userNameLabel);
            panel.add(passwordLabel);

            JButton detailsButton = new JButton("View Details");
            detailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showUserDetails(user);
                }
            });
            panel.add(detailsButton);

            userPanel.add(panel);
        }

        userPanel.revalidate();
        userPanel.repaint();
    }

    private void showUserDetails(User user) {
        detailPanel.removeAll();

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name: " + user.getName());
        JLabel phoneLabel = new JLabel("Phone: " + user.getPhoneNumber());
        JLabel addressLabel = new JLabel("Address: " + user.getAddress());
        JLabel balanceLabel = new JLabel("Balance: " + user.getBalance());

        infoPanel.add(nameLabel);
        infoPanel.add(phoneLabel);
        infoPanel.add(addressLabel);
        infoPanel.add(balanceLabel);

        // Display shopping cart
        JLabel shoppingCartLabel = new JLabel("Shopping Cart:");
        infoPanel.add(shoppingCartLabel);
        for (Map.Entry<Product, Integer> entry : user.getShoppingCard().entrySet()) {
            JLabel productLabel = new JLabel(entry.getKey().getTitle() + " - Quantity: " + entry.getValue());
            infoPanel.add(productLabel);
        }

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "UserList");
            }
        });

        detailPanel.add(infoPanel, BorderLayout.CENTER);
        detailPanel.add(backButton, BorderLayout.SOUTH);

        cardLayout.show(getContentPane(), "DetailView");

        detailPanel.revalidate();
        detailPanel.repaint();
    }


}
