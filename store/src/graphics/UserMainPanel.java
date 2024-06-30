package graphics;

import account.User;
import authentication.UserAuthenticator;
import product.Cloth;
import product.Phone;
import product.Product;
import shop.Data;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserMainPanel extends ProductsPanel{
    private User user;
    public UserMainPanel(User user, List<Product> products){
        super(products);
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
            // TODO: Implement the logic to show the shopping card panel
        });

        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(e -> {
            UserAuthenticator authenticator = new UserAuthenticator();
            authenticator.logOut(user.getUserName());
            super.frame.dispose();
            new SignUp_SignIn();
        });



        header.add(userPanelButton);
        header.add(shoppingCard);
        header.add(logOutButton);
        return header;
    }
    //The following implementations give the access to see, rate and add products to the shopping card to users
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

        JLabel buyAmountLabel = new JLabel("Enter the number you want");
        JTextField buyAmountField = new JTextField();
        JLabel rateLabel = new JLabel("Rate from 1 to 5");
        JTextField rateField = new JTextField();
        JButton rateButton = new JButton("Rate");
        rateButton.addActionListener(e -> {
            cloth.updateRate(Integer.parseInt(rateField.getText()));
            JOptionPane.showMessageDialog(rateButton, "Rated successfully");
            rateField.setText("");
        });

        JButton addToCard = new JButton("Add");
        addToCard.addActionListener(e -> {
            user.addTOShoppingCard(cloth, Integer.parseInt(buyAmountField.getText()));
            JOptionPane.showMessageDialog(addToCard, "Added to your ShoppingCard");
            buyAmountField.setText("");
        });

        box.add(image);
        box.add(productName);
        box.add(productPrice);
        box.add(size);
        box.add(color);
        box.add(sex);
        box.add(productCode);
        box.add(category);
        box.add(productRating);
        box.add(rateLabel);
        box.add(rateField);
        box.add(rateButton);
        box.add(buyAmountLabel);
        box.add(buyAmountField);
        box.add(addToCard);
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

        JLabel buyAmountLabel = new JLabel("Enter the number you want");
        JTextField buyAmountField = new JTextField();
        JLabel rateLabel = new JLabel("Rate from 1 to 5");
        JTextField rateField = new JTextField();
        JButton rateButton = new JButton("Rate");
        rateButton.addActionListener(e -> {
            phone.updateRate(Integer.parseInt(rateField.getText()));
            JOptionPane.showMessageDialog(rateButton, "Rated successfully");
            rateField.setText("");
        });

        JButton addToCard = new JButton("Add");
        addToCard.addActionListener(e -> {
            user.addTOShoppingCard(phone, Integer.parseInt(buyAmountField.getText()));
            JOptionPane.showMessageDialog(addToCard, "Added to your ShoppingCard");
            buyAmountField.setText("");
        });

        box.add(image);
        box.add(productName);
        box.add(productPrice);
        box.add(companyName);
        box.add(color);
        box.add(model);
        box.add(productCode);
        box.add(category);
        box.add(productRating);
        box.add(rateLabel);
        box.add(rateField);
        box.add(rateButton);
        box.add(buyAmountLabel);
        box.add(buyAmountField);
        box.add(addToCard);
        return box;
    }
}
