package graphics;

import account.User;
import product.Product;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class UserShoppingCardPanel {
    private JPanel mainPanel;
    private User user;
    private JLabel totalCostLabel;
    private JButton buyButton;
    private JButton clearButton;
    private JButton backButton;
    private JPanel cardPanel;
    private JPanel buttonPanel;
    private JPanel totalCostPanel;
    private JPanel root;
    private float totalCost = 0;

    public UserShoppingCardPanel(User user) {
        this.user = user;
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.add(createHeader());
        root.add(createCardPanel());
        root.add(createButtonPanel());
        mainPanel.add(root);
    }

    private Component createCardPanel() {
        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

        for (Map.Entry<Product, Integer> entry : user.getShoppingCard().entrySet()) {
            Product product = entry.getKey();
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.X_AXIS));

            JLabel productName = new JLabel(product.getName());
            JLabel productPrice = new JLabel(String.valueOf(product.getPrice()));
            totalCost += (float) product.getPrice();
            totalCostLabel = new JLabel("Total Cost: " + totalCost);

            productPanel.add(productName);
            productPanel.add(productPrice);
            cardPanel.add(productPanel);
        }

        totalCostPanel = new JPanel();
        totalCostPanel.setLayout(new BoxLayout(totalCostPanel, BoxLayout.X_AXIS));
        totalCostPanel.add(totalCostLabel);
        cardPanel.add(totalCostPanel);

        return cardPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel headerLabel = new JLabel("Shopping Card");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        header.add(headerLabel);
        return header;
    }

    public JPanel createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buyButton = new JButton("Buy");
        buyButton.addActionListener(e -> {
            user.buyProducts();
            totalCost = 0;
            totalCostLabel.setText("Total Cost: " + totalCost);
            cardPanel.removeAll();
            refreshFrame();
        });

        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            user.clearShoppingCard();
            totalCost = 0;
            totalCostLabel.setText("Total Cost: " + totalCost);
            cardPanel.removeAll();
            refreshFrame();
        });

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            root.removeAll();
            root.add(new UserMainPanel(user).getMainPanel());
            refreshFrame();
        });

        buttonPanel.add(buyButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        return buttonPanel;


}

    private void refreshFrame() {
        root.revalidate();
        root.repaint();
    }
}
