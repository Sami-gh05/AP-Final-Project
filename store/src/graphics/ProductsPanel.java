package graphics;

import product.Cloth;
import product.Phone;
import product.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ProductsPanel {

    protected JFrame frame;
    protected JPanel root;
    protected List<Product> products;



    public ProductsPanel(List<Product> products) {
        this.products = products;

        frame = new JFrame();
        root = new JPanel(new BorderLayout());

        frame.setTitle("Online Shop");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Header
        JPanel header = createHeader();
        root.add(header, BorderLayout.NORTH);

        // Product Grid
        JPanel productGrid = showProducts();
        root.add(productGrid, BorderLayout.CENTER);

        // Filters
        JPanel filters = createFilters();
        root.add(filters, BorderLayout.EAST);

        frame.add(root);
    }

    public JPanel getMainPanel(){
        return root;
    }

    public abstract JPanel createHeader();

    private JPanel showProducts() {
        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setLayout(new FlowLayout());

        if(products != null){
            for(Product product: products){
                if(product instanceof Cloth){
                    Cloth cloth = (Cloth) product;
                    allProductsPanel.add(createProductBox(cloth));
                }
                else if(product instanceof Phone){
                    Phone phone = (Phone) product;
                    allProductsPanel.add(createProductBox(phone));
                }

            }
        }

        JScrollPane scrollPane = new JScrollPane(allProductsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        root.add(scrollPane, BorderLayout.EAST);

        return  allProductsPanel;
    }

    //overloading polymorphism
    public abstract JPanel createProductBox(Cloth cloth);
    public abstract JPanel createProductBox(Phone phone);

    private JPanel createFilters() {
        JPanel filters = new JPanel();
        filters.setLayout(new BoxLayout(filters, BoxLayout.Y_AXIS));

        JLabel categoryLabel = new JLabel("Category");
        JComboBox<String> categoryComboBox = new JComboBox<>();
        categoryComboBox.addItem("Cloth");
        categoryComboBox.addItem("Phone");

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


    public JPanel getRoot() {
        return root;
    }
}