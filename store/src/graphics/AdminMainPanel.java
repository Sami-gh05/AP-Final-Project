package graphics;

import javax.swing.*;

public class AdminMainPanel extends ProductsPanel{
    @Override
    public JPanel createHeader(){
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JButton editProductButton = new JButton("Edit Products");
        editProductButton.addActionListener(e -> {
            //TODO
        });

        JButton userLoistButton = new JButton("Users List");
        userLoistButton.addActionListener(e -> {
            super.root.removeAll();
            super.root.add(new UserListPanel().getMainPanel());
        });

        header.add(editProductButton);
        header.add(userLoistButton);
        return header;
    }
}
