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
    JPanel productGrid;
    protected List<Product> products;

    private List<Product> sortedProducts;



    public ProductsPanel(List<Product> products) {
        this.products = products;
        this.sortedProducts = new ArrayList<>();

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
        productGrid = showProducts();
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

    public JPanel showProducts() {
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

        return  allProductsPanel;
    }

    //overloading polymorphism
    public JPanel showProducts(String category) {
        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setLayout(new FlowLayout());

        if(products != null) {
            if (category.equals("Cloth")) {
                for (Product product : products) {
                    if (product instanceof Cloth) {
                        Cloth cloth = (Cloth) product;
                        allProductsPanel.add(createProductBox(cloth));
                    }
                }
            } else if (category.equals("Phone")) {
                for (Product product : products) {
                    if (product instanceof Phone) {
                        Phone phone = (Phone) product;
                        allProductsPanel.add(createProductBox(phone));
                    }
                }
            }
        }

        return  allProductsPanel;
    }
    public JPanel showProducts(List<Product> sorted) {
        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setLayout(new FlowLayout());

        if(sorted != null){
            for(Product product: sorted){
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

        return  allProductsPanel;
    }

    //overloading polymorphism
    public abstract JPanel createProductBox(Cloth cloth);
    public abstract JPanel createProductBox(Phone phone);

    private JPanel createFilters() {
        JPanel filters = new JPanel();
        filters.setLayout(new BoxLayout(filters, BoxLayout.Y_AXIS));

        JLabel categoryLabel = new JLabel("Category");

        JButton clothCategory = new JButton("Cloth Category");
        clothCategory.addActionListener(e -> {
            root.remove(productGrid);
            root.add(showProducts("Cloth"), BorderLayout.CENTER);
        });
        JButton phoneCategory = new JButton("Phone Category");
        phoneCategory.addActionListener(e -> {
            root.remove(productGrid);
            root.add(showProducts("Phone"), BorderLayout.CENTER);
        });

        JButton rateSort = new JButton("Sort by rates");
        rateSort.addActionListener(e -> {
            //copying elements into new arrayList
            sortedProducts.addAll(products);
            //sorting
            for(int i = 0; i < products.size(); i++){
                for(int j = i + 1; j < products.size(); j++){
                    if(sortedProducts.get(i).getRate() < sortedProducts.get(j).getRate()){
                        //swap
                        Product temp = sortedProducts.get(i);
                        sortedProducts.set(i, sortedProducts.get(j));
                        sortedProducts.set(j, temp);
                    }
                }
            }
            //showing the sorted products
            root.remove(productGrid);
            root.add(showProducts(sortedProducts), BorderLayout.CENTER);
        });

        JButton highPriceSort = new JButton("Sort from highest price");
        highPriceSort.addActionListener(e -> {
            //copying elements into new arrayList
            sortedProducts.addAll(products);
            //sorting
            for(int i = 0; i < products.size(); i++){
                for(int j = i + 1; j < products.size(); j++){
                    if(sortedProducts.get(i).getPrice() < sortedProducts.get(j).getPrice()){
                        //swap
                        Product temp = sortedProducts.get(i);
                        sortedProducts.set(i, sortedProducts.get(j));
                        sortedProducts.set(j, temp);
                    }
                }
            }
            //showing the sorted products
            root.remove(productGrid);
            root.add(showProducts(sortedProducts), BorderLayout.CENTER);
        });

        JButton lowPriceSorted = new JButton("Sort from lowest price");
        lowPriceSorted.addActionListener(e -> {
            //copying elements into new arrayList
            sortedProducts.addAll(products);
            //sorting
            for(int i = 0; i < products.size(); i++){
                for(int j = i + 1; j < products.size(); j++){
                    if(sortedProducts.get(i).getPrice() > sortedProducts.get(j).getPrice()){
                        //swap
                        Product temp = sortedProducts.get(i);
                        sortedProducts.set(i, sortedProducts.get(j));
                        sortedProducts.set(j, temp);
                    }
                }
            }
            //showing the sorted products
            root.remove(productGrid);
            root.add(showProducts(sortedProducts), BorderLayout.CENTER);
        });

        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search title");
        searchButton.addActionListener(e -> {
            String title = searchField.getText();
            searchField.setText("");

            root.remove(productGrid);
            root.add(showProducts(title), BorderLayout.CENTER);
        });

        filters.add(categoryLabel);
        filters.add(clothCategory);
        filters.add(phoneCategory);
        filters.add(rateSort);
        filters.add(highPriceSort);
        filters.add(lowPriceSorted);
        filters.add(searchField);
        filters.add(searchButton);

        return filters;
    }


    public JPanel getRoot() {
        return root;
    }
}