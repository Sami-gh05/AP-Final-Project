package graphics;

import product.Cloth;
import product.Phone;
import product.Product;

import javax.swing.*;
import java.awt.*;
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
        JPanel productGrid = showProducts(products);
        root.add(productGrid, BorderLayout.CENTER);

        frame.add(root);
    }

    public abstract JPanel createHeader();

    JPanel showProducts(List<Product> filteredProducts) {
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

}