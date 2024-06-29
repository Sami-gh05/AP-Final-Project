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

        header.add(userPanelButton);
        header.add(shoppingCard);
        return header;
    }

}
