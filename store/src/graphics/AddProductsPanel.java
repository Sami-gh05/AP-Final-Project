package graphics;

import product.Cloth;
import product.Phone;
import shop.Data;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AddProductsPanel implements ActionListener {
    private JFrame adminMainPanel;
    private JPanel root;
    private JButton addProductButton;
    private JButton addPhoneButton;
    private JButton addClothButton;
    private JButton confirmClothButton;
    private JButton confirmPhoneButton;
    private JButton backButton, backToMainButton;
    private JButton exitButton;
    private JButton deleteImageButton;

    // Fields for adding cloth
    private JTextField titleField, priceField, sexField, sizeField, colorField;
    private JTextField phoneTitleField, phonePriceField, phoneCompanyNameField, phoneModelField, phoneColorField;
    private JLabel imageLabel, errorLabel;

    public AddProductsPanel(JFrame adminMainPanel, JPanel root) {
        this.adminMainPanel = adminMainPanel;
        this.root = root;
        mainEditPanel();
    }

    public void mainEditPanel() {
        JPanel mainPanel = new JPanel();
        addProductButton = createButton("Add Products");
        addProductButton.setBounds(100, 200, 200, 10);
        addProductButton.addActionListener(this);
        backToMainButton = createButton("Back to main");
        backToMainButton.setBounds(300, 200, 200, 10);
        backToMainButton.addActionListener(this);
        mainPanel.add(addProductButton);
        mainPanel.add(backToMainButton);
        this.adminMainPanel.getContentPane().removeAll();
        this.adminMainPanel.add(mainPanel);
        this.adminMainPanel.revalidate();
        this.adminMainPanel.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addProductButton) {
            addProductEntry();
        } else if (e.getSource() == addClothButton) {
            addClothe();
        } else if (e.getSource() == addPhoneButton) {
            addPhone();
        } else if (e.getSource() == confirmClothButton) {
            if (clothAllFieldsFilled()) {
                errorLabel.setText("");
                Cloth cloth = new Cloth(titleField.getText(), Double.parseDouble(priceField.getText()), sizeField.getText(), colorField.getText(), sexField.getText());
                cloth.setImageLabel(imageLabel);
                Data.addProduct(cloth);
                addProductEntry();
            } else {
                errorLabel.setText("Fill all the needed blanks");
            }
        } else if (e.getSource() == confirmPhoneButton) {
            if (phoneAllFieldsFilled()) {
                errorLabel.setText("");
                Phone phone = new Phone(phoneTitleField.getText(), Double.parseDouble(phonePriceField.getText()), phoneCompanyNameField.getText(), phoneModelField.getText(), phoneColorField.getText());
                phone.setImageLabel(imageLabel);
                Data.addProduct(phone);
                addProductEntry();
            } else {
                errorLabel.setText("Fill all the needed blanks");
            }
        } else if (e.getSource() == deleteImageButton) {
            imageLabel.setIcon(null);
        } else if (e.getSource() == backButton) {
            addProductEntry(); // Return to add product entry panel
        } else if (e.getSource() == exitButton) {
            adminMainPanel.setVisible(true);
            adminMainPanel.revalidate();
            adminMainPanel.repaint();
        }
        else if(e.getSource() == backToMainButton){
            adminMainPanel.getContentPane().removeAll();
            adminMainPanel.add(root);
            adminMainPanel.revalidate();
            adminMainPanel.repaint();
        }
    }

    public void addProductEntry() {
        JPanel addProductEntryPanel = new JPanel();
        addClothButton = createButton("Cloth");
        addPhoneButton = createButton("Phone");
        addClothButton.addActionListener(this);
        addPhoneButton.addActionListener(this);

        exitButton = createButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainEditPanel();
            }
        });

        addProductEntryPanel.add(addClothButton);
        addProductEntryPanel.add(addPhoneButton);
        addProductEntryPanel.add(exitButton);

        this.adminMainPanel.getContentPane().removeAll();
        this.adminMainPanel.add(addProductEntryPanel);
        this.adminMainPanel.revalidate();
        this.adminMainPanel.repaint();
    }

    public void addPhone() {
        JPanel phonePanel = createProductPanel("Phone");
        confirmPhoneButton = createButton("Confirm");
        confirmPhoneButton.addActionListener(this);
        backButton = createButton("Back");
        backButton.addActionListener(this);

        phonePanel.add(backButton);
        phonePanel.add(confirmPhoneButton);

        this.adminMainPanel.getContentPane().removeAll();
        this.adminMainPanel.add(phonePanel);
        this.adminMainPanel.revalidate();
        this.adminMainPanel.repaint();
    }

    public void addClothe() {
        JPanel clothPanel = createProductPanel("Cloth");
        confirmClothButton = createButton("Confirm");
        confirmClothButton.addActionListener(this);
        backButton = createButton("Back");
        backButton.addActionListener(this);

        clothPanel.add(backButton);
        clothPanel.add(confirmClothButton);

        this.adminMainPanel.getContentPane().removeAll();
        this.adminMainPanel.add(clothPanel);
        this.adminMainPanel.revalidate();
        this.adminMainPanel.repaint();
    }

    private JPanel createProductPanel(String productType) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(new LineBorder(Color.GRAY, 1));
        imageLabel.setBackground(Color.LIGHT_GRAY);
        imageLabel.setOpaque(true);
        mainPanel.add(imageLabel);

        JPanel imageButtonsPanel = new JPanel();
        imageButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        imageButtonsPanel.setBackground(Color.WHITE);
        mainPanel.add(imageButtonsPanel);

        JButton addImageButton = createButton("Add Image");
        addImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage image = ImageIO.read(selectedFile);
                        ImageIcon imageIcon = new ImageIcon(resizeImage(image, 150, 150));
                        imageLabel.setIcon(imageIcon);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        imageButtonsPanel.add(addImageButton);

        deleteImageButton = createButton("Delete Image");
        deleteImageButton.addActionListener(this);
        imageButtonsPanel.add(deleteImageButton);

        JPanel fieldsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        fieldsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        fieldsPanel.setBackground(Color.WHITE);
        mainPanel.add(fieldsPanel);

        if (productType.equals("Cloth")) {
            titleField = addField(fieldsPanel, "Title");
            priceField = addField(fieldsPanel, "Price");
            sexField = addField(fieldsPanel, "Sex");
            sizeField = addField(fieldsPanel, "Size");
            colorField = addField(fieldsPanel, "Color");

        } else if (productType.equals("Phone")) {
            phoneTitleField = addField(fieldsPanel, "Title");
            phonePriceField = addField(fieldsPanel, "Price");
            phoneCompanyNameField = addField(fieldsPanel, "Company Name");
            phoneModelField = addField(fieldsPanel, "Model");
            phoneColorField = addField(fieldsPanel, "Color");
        }

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(errorLabel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setBackground(Color.WHITE);
        mainPanel.add(buttonsPanel);

        return mainPanel;
    }

    private boolean clothAllFieldsFilled() {
        return !titleField.getText().isEmpty() &&
                !priceField.getText().isEmpty() &&
                !sexField.getText().isEmpty() &&
                !sizeField.getText().isEmpty() &&
                !colorField.getText().isEmpty() &&
                imageLabel.getIcon() != null;
    }

    private boolean phoneAllFieldsFilled() {
        return !phoneTitleField.getText().isEmpty() &&
                !phonePriceField.getText().isEmpty() &&
                !phoneCompanyNameField.getText().isEmpty() &&
                !phoneModelField.getText().isEmpty() &&
                !phoneColorField.getText().isEmpty() &&
                imageLabel.getIcon() != null;
    }

    private Image resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
    }

    private JTextField addField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(textField);
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 30));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}