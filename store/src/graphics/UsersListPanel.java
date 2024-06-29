package graphics;
import account.User;
import product.Cloth;
import product.Phone;
import product.Product;
import shop.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class UserListPanel{
    private JPanel mainPanel;
    private JPanel userPanel;
    private JPanel detailPanel;
    private CardLayout cardLayout;
    private List<User> users;
    //private AdminMainPanel
    public UserListPanel() {
        this.users = Data.getUsers();

        // Create the main panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Create the user panel and add it to the main panel
        userPanel = new JPanel();
        userPanel.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(userPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, "UserList");

        // Create the detail panel and add it to the main panel
        detailPanel = new JPanel();
        detailPanel.setLayout(new BorderLayout());
        mainPanel.add(detailPanel, "DetailView");

        // Add the main panel to the frame's content pane
        //getContentPane().add(mainPanel);

        // Load the user list
        loadUserList();
    }

    private void loadUserList() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        for (User user : users) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            panel.setBackground(Color.LIGHT_GRAY);

            JLabel userNameLabel = new JLabel("Username: " + user.getUserName());
            userNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            JLabel passwordLabel = new JLabel("Password: " + user.getPassword());
            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            JButton detailsButton = new JButton("View Details");
            detailsButton.setFont(new Font("Arial", Font.PLAIN, 12));
            detailsButton.setBackground(Color.WHITE);
            detailsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            detailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showUserDetails(user);
                }
            });

            GridBagConstraints innerGbc = new GridBagConstraints();
            innerGbc.gridx = 0;
            innerGbc.gridy = 0;
            innerGbc.insets = new Insets(5, 5, 5, 5);
            innerGbc.anchor = GridBagConstraints.WEST;
            panel.add(userNameLabel, innerGbc);

            innerGbc.gridy = 1;
            panel.add(passwordLabel, innerGbc);

            innerGbc.gridy = 2;
            innerGbc.anchor = GridBagConstraints.CENTER;
            panel.add(detailsButton, innerGbc);

            gbc.gridy++;
            userPanel.add(panel, gbc);
        }

        userPanel.revalidate();
        userPanel.repaint();
    }

    private void showUserDetails(User user) {
        detailPanel.removeAll();

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name: " + user.getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel phoneLabel = new JLabel("Phone: " + user.getPhoneNumber());
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel addressLabel = new JLabel("Address: " + user.getAddress());
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel balanceLabel = new JLabel("Balance: " + user.getBalance());
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(nameLabel);
        infoPanel.add(phoneLabel);
        infoPanel.add(addressLabel);
        infoPanel.add(balanceLabel);

        JPanel shoppingCartPanel = new JPanel();
        shoppingCartPanel.setLayout(new BoxLayout(shoppingCartPanel, BoxLayout.Y_AXIS));
        shoppingCartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel shoppingCartLabel = new JLabel("Shopping Cart:");
        shoppingCartLabel.setFont(new Font("Arial", Font.BOLD, 14));
        shoppingCartPanel.add(shoppingCartLabel);

        for (Map.Entry<Product, Integer> entry : user.getShoppingCard().entrySet()) {
            JLabel productLabel = new JLabel(entry.getKey().getTitle() + " - Quantity: " + entry.getValue());
            productLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            shoppingCartPanel.add(productLabel);
        }

        JPanel previousPurchasesPanel = new JPanel();
        previousPurchasesPanel.setLayout(new BoxLayout(previousPurchasesPanel, BoxLayout.Y_AXIS));
        previousPurchasesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel previousPurchasesLabel = new JLabel("Previous Purchases:");
        previousPurchasesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        previousPurchasesPanel.add(previousPurchasesLabel);

        for (Map.Entry<Product, Integer> entry : user.getPreviousPurchases().entrySet()) {
            JLabel productLabel = new JLabel(entry.getKey().getTitle() + " - Quantity: " + entry.getValue());
            productLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            previousPurchasesPanel.add(productLabel);
        }

        tabbedPane.add("Info", infoPanel);
        tabbedPane.add("Shopping Cart", shoppingCartPanel);
        tabbedPane.add("Previous Purchases", previousPurchasesPanel);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "UserList");
            }
        });

        detailPanel.add(tabbedPane, BorderLayout.CENTER);
        detailPanel.add(backButton, BorderLayout.SOUTH);

        cardLayout.show(mainPanel, "DetailView");

        detailPanel.revalidate();
        detailPanel.repaint();
    }


}
