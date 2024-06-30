package graphics;

import account.Admin;
import authentication.AdminAuthenticator;
import product.Cloth;
import product.Phone;
import product.Product;
import shop.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminMainPanel extends ProductsPanel {

    public AdminMainPanel(List<Product> products) {
        super(products);


        // Adding header to the north
        JPanel header = createHeader();
        root.add(header, BorderLayout.NORTH);

        // Adding product grid to the center
        JPanel productGrid = showProducts();
        root.add(productGrid, BorderLayout.CENTER);

        frame.add(root);
    }

    @Override
    public JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JButton editProductButton = new JButton("Add Products");
        editProductButton.addActionListener(e -> {
            new AddProductsPanel(super.frame, super.root);
        });

        JButton userListButton = new JButton("Users List");
        userListButton.addActionListener(e -> {
            super.frame.getContentPane().removeAll();
            super.frame.add(new UserListPanel(super.frame, super.root).getMainPanel());
            super.frame.revalidate();
            super.frame.repaint();
        });
        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(e -> {
            AdminAuthenticator authenticator = new AdminAuthenticator();
            authenticator.logOut(Admin.getAdminID());
            Data.fillDatabase();
            super.frame.dispose();
            new MainGUI();
        });
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            super.frame.dispose();
            new AdminMainPanel(Data.getProducts());
        });

        header.add(editProductButton);
        header.add(userListButton);
        header.add(logOutButton);
        header.add(refreshButton);
        return header;
    }

    // The following method implementations give the access to see, edit, or remove products (both cloth and phone) to the admin
    @Override
    public JPanel createProductBox(Cloth cloth) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel productName = new JLabel(cloth.getTitle());
        JLabel productPrice = new JLabel(String.valueOf(cloth.getPrice()));
        // Getting the rate as stars:
        String stars = "";
        for (int i = 0; i < cloth.getRate(); i++)
            stars += "★";
        JLabel productRating = new JLabel(stars);
        JLabel imageLabel = cloth.getImageLabel() != null ? cloth.getImageLabel() : new JLabel("No Image Available");
        JButton changeImageButton = new JButton("Change Image"); // Button to change image

        JLabel category = new JLabel(cloth.getLabel());
        JLabel size = new JLabel(cloth.getSize());
        JLabel color = new JLabel(cloth.getColor());
        JLabel sex = new JLabel(cloth.getSex());
        JLabel productCode = new JLabel(cloth.getProductCode());

        JTextField productNameField = new JTextField();
        JTextField productPriceField = new JTextField();
        JTextField sizeField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField sexField = new JTextField();

        JButton removeProductButton = new JButton("Remove");
        removeProductButton.addActionListener(e -> {
            Data.removeProduct(cloth);
            JOptionPane.showMessageDialog(removeProductButton, "Done");
        });

        JButton editProductButton = new JButton("Confirm Edit");
        editProductButton.addActionListener(e -> {
            try {
                if (!productNameField.getText().isEmpty()) {
                    cloth.setTitle(productNameField.getText());
                    productNameField.setText("");
                }
                if (!productPriceField.getText().isEmpty()) {
                    cloth.setPrice(Float.parseFloat(productPriceField.getText()));
                    productPriceField.setText("");
                }
                if (!colorField.getText().isEmpty()) {
                    cloth.setColor(colorField.getText());
                    colorField.setText("");
                }
                if (!sizeField.getText().isEmpty()) {
                    cloth.setSize(sizeField.getText());
                    sizeField.setText("");
                }
                if (!sexField.getText().isEmpty()) {
                    cloth.setSex(sexField.getText());
                    sexField.setText("");
                }
                JOptionPane.showMessageDialog(editProductButton, "Done");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editProductButton, "Invalid price format");
            }
        });

        // Action listener for "Change Image" button
        changeImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                java.io.File selectedFile = fileChooser.getSelectedFile();
                // Assuming selectedFile is an image file
                ImageIcon newImageIcon = new ImageIcon(selectedFile.getPath());
                imageLabel.setIcon(newImageIcon);
                cloth.setImageIcon(newImageIcon);
                cloth.setImageLabel(imageLabel); // Update cloth's image label
            }
        });


        box.add(changeImageButton);
        box.add(productName);
        box.add(productNameField);
        box.add(productPrice);
        box.add(productPriceField);
        box.add(size);
        box.add(sizeField);
        box.add(color);
        box.add(colorField);
        box.add(sex);
        box.add(sexField);
        box.add(productCode);
        box.add(category);
        box.add(productRating);
        box.add(removeProductButton);
        box.add(editProductButton);

        return box;
    }

    @Override
    public JPanel createProductBox(Phone phone) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel productName = new JLabel(phone.getTitle());
        JLabel productPrice = new JLabel(String.valueOf(phone.getPrice()));
        // Getting the rate as stars:
        String stars = "";
        for (int i = 0; i < phone.getRate(); i++)
            stars += "★";
        JLabel productRating = new JLabel(stars);
        JLabel imageLabel = phone.getImageLabel() != null ? phone.getImageLabel() : new JLabel("No Image Available");
        JButton changeImageButton = new JButton("Change Image");

        JLabel category = new JLabel(phone.getLabel());
        JLabel companyName = new JLabel(phone.getCompanyName());
        JLabel color = new JLabel(phone.getColor());
        JLabel model = new JLabel(phone.getModel());
        JLabel productCode = new JLabel(phone.getProductCode());

        JTextField productNameField = new JTextField();
        JTextField productPriceField = new JTextField();
        JTextField companyNameField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField modelField = new JTextField();

        JButton removeProductButton = new JButton("Remove");
        removeProductButton.addActionListener(e -> {
            Data.removeProduct(phone);
            JOptionPane.showMessageDialog(removeProductButton, "Done");
        });

        JButton editProductButton = new JButton("Confirm Edit");
        editProductButton.addActionListener(e -> {
            try {
                if (!productNameField.getText().isEmpty()) {
                    phone.setTitle(productNameField.getText());
                    productNameField.setText("");
                }
                if (!productPriceField.getText().isEmpty()) {
                    phone.setPrice(Float.parseFloat(productPriceField.getText()));
                    productPriceField.setText("");
                }
                if (!colorField.getText().isEmpty()) {
                    phone.setColor(colorField.getText());
                    colorField.setText("");
                }
                if (!companyNameField.getText().isEmpty()) {
                    phone.setCompanyName(companyNameField.getText());
                    companyNameField.setText("");
                }
                if (!modelField.getText().isEmpty()) {
                    phone.setModel(modelField.getText());
                    modelField.setText("");
                }
                JOptionPane.showMessageDialog(editProductButton, "Done");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editProductButton, "Invalid price format");
            }
        });

        // Action listener for "Change Image" button
        changeImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                java.io.File selectedFile = fileChooser.getSelectedFile();
                // Assuming selectedFile is an image file
                ImageIcon newImageIcon = new ImageIcon(selectedFile.getPath());
                imageLabel.setIcon(newImageIcon);
                phone.setImageIcon(newImageIcon);
                phone.setImageLabel(imageLabel);
            }
        });

        box.add(changeImageButton);
        box.add(productName);
        box.add(productNameField);
        box.add(productPrice);
        box.add(productPriceField);
        box.add(companyName);
        box.add(companyNameField);
        box.add(color);
        box.add(colorField);
        box.add(model);
        box.add(modelField);
        box.add(productCode);
        box.add(category);
        box.add(productRating);
        box.add(removeProductButton);
        box.add(editProductButton);

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
}

