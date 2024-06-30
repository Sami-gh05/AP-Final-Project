package graphics;

import account.Admin;
import authentication.AdminAuthenticator;
import product.Cloth;
import product.Phone;
import product.Product;
import shop.Data;

import javax.swing.*;
import java.util.List;

public class AdminMainPanel extends ProductsPanel{
    private List<Product> products;
    public AdminMainPanel(List<Product> products){
        super(products);
    }
    @Override
    public JPanel createHeader(){
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JButton editProductButton = new JButton("Add Products");
        editProductButton.addActionListener(e -> {
            new AddProductsPanel(super.frame, super.root);
        });

        JButton userListButton = new JButton("Users List");
        userListButton.addActionListener(e -> {
            super.frame.getContentPane().removeAll();
            super.frame.add(new UserListPanel().getMainPanel());
            super.frame.revalidate();
            super.frame.repaint();
        });
        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(e -> {
            AdminAuthenticator authenticator = new AdminAuthenticator();
            authenticator.logOut(Admin.getAdminID());
            super.frame.dispose();
            new SignUp_SignIn();
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
    //The following method implementations give the access to see, edit or remove products(both cloth and phone) to admin
    public JPanel createProductBox(Cloth cloth) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel productName = new JLabel(cloth.getTitle());
        JLabel productPrice = new JLabel(String.valueOf(cloth.getPrice()));
        //getting the rate as stars:
        String stars = "";
        for(int i = 0; i < cloth.getRate(); i++)
            stars += "★";
        JLabel productRating = new JLabel(stars);
        JLabel image = cloth.getImageLabel();
        JLabel category = new JLabel(cloth.getLabel());
        JLabel size = new JLabel(cloth.getSize());
        JLabel color = new JLabel(cloth.getColor());
        JLabel sex = new JLabel(cloth.getSex());
        JLabel productCode = new JLabel(cloth.getProductCode());

        JTextField productNameField = new JTextField();
        JTextField productPriceField = new JTextField();
        //TODO implement image edit
        JTextField sizeField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField sexField = new JTextField();

        JButton removeProductButton = new JButton("Remove");
        removeProductButton.addActionListener(e -> {
            Data.removeProduct(cloth);
            JOptionPane.showMessageDialog(removeProductButton, "Done");
        });

        JButton editProductButton = new JButton("Confirm edit");
        editProductButton.addActionListener(e -> {
            cloth.setTitle(productNameField.getText());
            productNameField.setText("");
            cloth.setPrice(Float.parseFloat(productPriceField.getText()));
            productPriceField.setText("");
            cloth.setColor(colorField.getText());
            colorField.setText("");
            cloth.setSize(sizeField.getText());
            sizeField.setText("");
            cloth.setSex(sexField.getText());
            sexField.setText("");
            JOptionPane.showMessageDialog(editProductButton, "Done");
        });



        box.add(image);
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
    public JPanel createProductBox(Phone phone) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel productName = new JLabel(phone.getTitle());
        JLabel productPrice = new JLabel(String.valueOf(phone.getPrice()));
        //getting the rate as stars:
        String stars = "";
        for(int i = 0; i < phone.getRate(); i++)
            stars += "★";
        JLabel productRating = new JLabel(stars);
        JLabel image = phone.getImageLabel();
        JLabel category = new JLabel(phone.getLabel());
        JLabel companyName = new JLabel(phone.getCompanyName());
        JLabel color = new JLabel(phone.getColor());
        JLabel model = new JLabel(phone.getModel());
        JLabel productCode = new JLabel(phone.getProductCode());

        JTextField productNameField = new JTextField();
        JTextField productPriceField = new JTextField();
        //TODO implement image edit
        JTextField companyNameField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField modelField = new JTextField();

        JButton removeProductButton = new JButton("Remove");
        removeProductButton.addActionListener(e -> {
            Data.removeProduct(phone);
            JOptionPane.showMessageDialog(removeProductButton, "Done");
        });

        JButton editProductButton = new JButton("Confirm edit");
        editProductButton.addActionListener(e -> {
            phone.setTitle(productNameField.getText());
            productNameField.setText("");
            phone.setPrice(Float.parseFloat(productPriceField.getText()));
            productPriceField.setText("");
            phone.setColor(colorField.getText());
            colorField.setText("");
            phone.setCompanyName(companyNameField.getText());
            companyNameField.setText("");
            phone.setModel(modelField.getText());
            modelField.setText("");
            JOptionPane.showMessageDialog(editProductButton, "Done");
        });



        box.add(image);
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
}
