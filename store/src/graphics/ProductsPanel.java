package graphics;

import javax.swing.*;
import java.awt.*;

public abstract class ProductsPanel extends JFrame {

    protected JPanel root;

    public ProductsPanel() {
        root = new JPanel(new BorderLayout());

        // Header
        JPanel header = createHeader();
        root.add(header, BorderLayout.NORTH);

        // Product Grid
        JPanel productGrid = createProductGrid();
        root.add(productGrid, BorderLayout.CENTER);

        // Filters
        JPanel filters = createFilters();
        root.add(filters, BorderLayout.EAST);

        this.add(root);
        this.setTitle("Online Shop");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public abstract JPanel createHeader();

    private JPanel createProductGrid() {
        JPanel grid = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel productBox = createProductBox();
                grid.add(productBox);
            }
        }

        return grid;
    }

    private JPanel createProductBox() {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel productName = new JLabel("Product Name");
        JLabel productPrice = new JLabel("$1000");
        JLabel productRating = new JLabel("★★★★★");

        box.add(productName);
        box.add(productPrice);
        box.add(productRating);
        return box;
    }

    private JPanel createFilters() {
        JPanel filters = new JPanel();
        filters.setLayout(new BoxLayout(filters, BoxLayout.Y_AXIS));

        JLabel categoryLabel = new JLabel("Category");
        JComboBox<String> categoryComboBox = new JComboBox<>();
        categoryComboBox.addItem("Car");
        categoryComboBox.addItem("Phone");
        categoryComboBox.addItem("Laptop");

        JLabel priceLabel = new JLabel("Price");
        JSlider priceSlider = new JSlider(0, 2000, 1000);

        JCheckBox availableGoodsCheckBox = new JCheckBox("Only available goods");

        filters.add(categoryLabel);
        filters.add(categoryComboBox);
        filters.add(priceLabel);
        filters.add(priceSlider);
        filters.add(availableGoodsCheckBox);
        return filters;
    }

    /*public static void main(String[] args) {
        new ProductsPanel();
    }*/

    public JPanel getRoot() {
        return root;
    }
}