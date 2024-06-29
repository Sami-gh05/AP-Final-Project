package graphics;

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

public class ProductsEditPanel implements ActionListener {
    private AdminMainPanel adminMainPanel;
    private JButton addProductButton;
    private JButton addPhoneButton;
    private JButton addClothButton;
    private JButton confirmClothButton;
    private JButton confirmPhoneButton;
    private JButton backButton;
    private JButton exitButton;
    private JButton deleteImageButton;

    // Fields for adding cloth
    private JTextField titleField, priceField, sexField, sizeField, colorField, productCodeField;
    private JTextField phoneTitleField, phonePriceField, phoneProductCodeField, phoneCompanyNameField, phoneModelField, phoneColorField;
    private JLabel imageLabel, errorLabel;

    public ProductsEditPanel(AdminMainPanel adminMainPanel) {
        this.adminMainPanel = adminMainPanel;
        mainEditPanel();
    }

    public void mainEditPanel() {
        JPanel mainPanel = new JPanel();
        addProductButton = createButton("+");
        addProductButton.setBounds(100, 0, 20, 10);
        addProductButton.addActionListener(this);
        mainPanel.add(addProductButton);
        this.adminMainPanel.removeAll();
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
            } else {
                errorLabel.setText("Fill all the needed blanks");
            }
        } else if (e.getSource() == confirmPhoneButton) {
            if (phoneAllFieldsFilled()) {
                errorLabel.setText("");
            } else {
                errorLabel.setText("Fill all the needed blanks");
            }
        } else if (e.getSource() == deleteImageButton) {
            imageLabel.setIcon(null);
        } else if (e.getSource() == backButton) {
            addProductEntry(); // Return to add product entry panel
        } else if (e.getSource() == exitButton) {
            adminMainPanel.setVisible(true); // Show admin main panel
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
                adminMainPanel.add(adminMainPanel.createHeader()); // Show admin main panel
                adminMainPanel.revalidate();
                adminMainPanel.repaint();
            }
        });

        addProductEntryPanel.add(addClothButton);
        addProductEntryPanel.add(addPhoneButton);
        addProductEntryPanel.add(exitButton);

        this.adminMainPanel.removeAll();
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

        this.adminMainPanel.removeAll();
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

        this.adminMainPanel.removeAll();
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
            productCodeField = addField(fieldsPanel, "Product Code");
        } else if (productType.equals("Phone")) {
            phoneTitleField = addField(fieldsPanel, "Title");
            phonePriceField = addField(fieldsPanel, "Price");
            phoneProductCodeField = addField(fieldsPanel, "Product Code");
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
                !productCodeField.getText().isEmpty() &&
                imageLabel.getIcon() != null;
    }

    private boolean phoneAllFieldsFilled() {
        return !phoneTitleField.getText().isEmpty() &&
                !phonePriceField.getText().isEmpty() &&
                !phoneProductCodeField.getText().isEmpty() &&
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
