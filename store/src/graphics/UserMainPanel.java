package graphics;

import account.User;
import authentication.UserAuthenticator;
import product.Cloth;
import product.Phone;
import product.Product;
import shop.Data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserMainPanel extends ProductsPanel{
    private User user;
    public UserMainPanel(User user, List<Product> products){
        super(products);
        this.user = user;
    }

    public UserMainPanel(User user) {

        super(Data.getProducts());
        this.user = user;

    }

    @Override
    public JPanel createHeader(){
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JButton userPanelButton = new JButton("User Profile");
        userPanelButton.addActionListener(e -> {
            super.frame.getContentPane().removeAll();
            super.frame.add(new UserProfilePanel(user, super.frame, super.root).getInfoPanel());
            super.frame.revalidate();
            super.frame.repaint();
        });

        JButton shoppingCard = new JButton("Shopping card");
        shoppingCard.addActionListener(e -> {
            super.frame.getContentPane().removeAll();
            super.frame.add(new UserShoppingCardPanel(user).getMainPanel());
            super.frame.revalidate();
            super.frame.repaint();
        });

        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(e -> {
            UserAuthenticator authenticator = new UserAuthenticator();
            authenticator.logOut(user.getUserName());
            super.frame.dispose();
            new SignUp_SignIn();
        });

        // Add comboBox for filtering products
        header.add(createFilterPanel());


        header.add(userPanelButton);
        header.add(shoppingCard);
        header.add(logOutButton);
        return header;
    }


    // ComboBox for filtering products
    public JPanel createFilterPanel(){
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));

        JLabel filterLabel = new JLabel("Filter by category: ");

        // Create a Set to store unique categories
        Set<String> categories = new HashSet<>();

        // Add "All" to the set
        categories.add("All");

        // Iterate over the product list
        for (Product product : super.products) {
            // Add the category of each product to the set
            categories.add(product.getCategory());
        }


        // Convert the set to an array
        String[] categoriesArray = categories.toArray(new String[0]);

        // Create a JComboBox with the categories array
        JComboBox<String> filterComboBox = new JComboBox<>(categoriesArray);
        filterComboBox.addActionListener(e -> {
            String selectedCategory = (String) filterComboBox.getSelectedItem();
            List<Product> filteredProducts = new ArrayList<>();
            assert selectedCategory != null;
            // Iterate over the product list
            for (Product product : super.products) {
                // If the product's category matches the selected category
                if (product.getCategory().equals(selectedCategory)) {
                    // Add the product to the filtered products list
                    filteredProducts.add(product);
                } else if (selectedCategory.equals("All")) {
                    // If the selected category is "All", add all products to the filtered products list
                    filteredProducts.add(product);
                }
            }
            productGrid.removeAll();
            productGrid.add(showProducts(filteredProducts));
            productGrid.revalidate();
            productGrid.repaint();
        });

        filterPanel.add(filterLabel);
        filterPanel.add(filterComboBox);
        return filterPanel;
    }

    //The following implementations give the access to see, rate and add products to the shopping card to users
    @Override
    public JPanel createProductBox(Cloth cloth) {
        JPanel box = new JPanel(new BorderLayout());

        // Create ImageIcon from the cloth object
        ImageIcon imageIcon = cloth.getImageIcon();
        JLabel imageLabel = new JLabel();
        if (imageIcon != null) {
            imageLabel.setIcon(imageIcon);
        } else {
            imageLabel.setText("No Image Available");
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
        }
        imageLabel.setPreferredSize(new Dimension(200, 200)); // Set preferred size for the image
        box.add(imageLabel, BorderLayout.NORTH);

        // Details panel with vertical layout
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        detailsPanel.add(new JLabel(cloth.getTitle()));
        detailsPanel.add(new JLabel(String.valueOf(cloth.getPrice())));
        detailsPanel.add(new JLabel(cloth.getSize()));
        detailsPanel.add(new JLabel(cloth.getColor()));
        detailsPanel.add(new JLabel(cloth.getSex()));
        detailsPanel.add(new JLabel(cloth.getProductCode()));
        detailsPanel.add(new JLabel(cloth.getLabel()));

        // Add other components as needed

        box.add(detailsPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            // Implement add to cart functionality here
            JOptionPane.showMessageDialog(addToCartButton, "Added to your Shopping Cart");
        });
        buttonsPanel.add(addToCartButton);

        JButton rateButton = new JButton("Rate");
        rateButton.addActionListener(e -> {
            // Implement rate functionality here
            JOptionPane.showMessageDialog(rateButton, "Rated successfully");
        });
        buttonsPanel.add(rateButton);

        box.add(buttonsPanel, BorderLayout.SOUTH);

        return box;
    }

    @Override
    public JPanel createProductBox(Phone phone) {
        JPanel box = new JPanel(new BorderLayout());

        // Create ImageIcon from the phone object
        ImageIcon imageIcon = phone.getImageIcon();
        JLabel imageLabel = new JLabel();
        if (imageIcon != null) {
            imageLabel.setIcon(imageIcon);
        } else {
            imageLabel.setText("No Image Available");
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
        }
        imageLabel.setPreferredSize(new Dimension(200, 200)); // Set preferred size for the image
        box.add(imageLabel, BorderLayout.NORTH);

        // Details panel with vertical layout
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        detailsPanel.add(new JLabel(phone.getTitle()));
        detailsPanel.add(new JLabel(String.valueOf(phone.getPrice())));
        detailsPanel.add(new JLabel(phone.getCompanyName()));
        detailsPanel.add(new JLabel(phone.getColor()));
        detailsPanel.add(new JLabel(phone.getModel()));
        detailsPanel.add(new JLabel(phone.getProductCode()));
        detailsPanel.add(new JLabel(phone.getLabel()));

        // Add other components as needed

        box.add(detailsPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            // Implement add to cart functionality here
            JOptionPane.showMessageDialog(addToCartButton, "Added to your Shopping Cart");
        });
        buttonsPanel.add(addToCartButton);

        JButton rateButton = new JButton("Rate");
        rateButton.addActionListener(e -> {
            // Implement rate functionality here
            JOptionPane.showMessageDialog(rateButton, "Rated successfully");
        });
        buttonsPanel.add(rateButton);

        box.add(buttonsPanel, BorderLayout.SOUTH);

        return box;
    }
    public JPanel showProducts() {
        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columns

        if (products != null) {
            for (Product product : Data.getProducts()) {
                if (product instanceof Cloth) {
                    Cloth cloth = (Cloth) product;
                    allProductsPanel.add(createProductBox(cloth));
                } else if (product instanceof Phone) {
                    Phone phone = (Phone) product;
                    allProductsPanel.add(createProductBox(phone));
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(allProductsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public JPanel getMainPanel(){
        root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.add(createHeader());
        root.add(showProducts(super.products));
        return root;
    }
}
