package graphics;

import account.User;
import javax.swing.*;

public class UserMainPanel extends ProductsPanel{
    private User user;
    public UserMainPanel(User user){
        this.user = user;
    }
    @Override
    public JPanel createHeader(){
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JButton userPanelButton = new JButton("User Profile");
        userPanelButton.addActionListener(e -> {
            super.root.removeAll();
            super.root.add(new UserProfilePanel(user));
        });

        JButton shoppingCard = new JButton("Shopping card");
        shoppingCard.addActionListener(e -> {
            // TODO: Implement the logic to show the shopping card panel
        });

        // Add category choice field to header with a listener to filter products (our categories are in the product package)
        JComboBox<String> categoryChoice = getStringJComboBox();
        header.add(categoryChoice);



        header.add(userPanelButton);
        header.add(shoppingCard);
        return header;
    }

    private JComboBox<String> getStringJComboBox() {
        JComboBox<String> categoryChoice = new JComboBox<>(new String[]{"All", "Cloth", "Phone"});

        categoryChoice.addActionListener(e -> {
            String selectedCategory = (String) categoryChoice.getSelectedItem();
            if (selectedCategory.equals("All")) {
                super.root.removeAll();
                super.root.add(createProductGrid(selectedCategory));
            } else {
                super.root.removeAll();
                super.root.add(createProductGrid(selectedCategory));
            }
        });



        return categoryChoice;
    }

    public static void main(String[] args) {
        UserMainPanel userMainPanel = new UserMainPanel(new User("user", "password", "email", "address", "phone"));
        userMainPanel.createHeader();
    }

}
