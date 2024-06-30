package graphics;

import javax.swing.*;
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
public abstract class ProductsPanel extends JFrame {

    protected JPanel root;

    public ProductsPanel() {
        root = new JPanel(new BorderLayout());

        // Header
        JPanel header = createHeader();
        root.add(header, BorderLayout.NORTH);

        // Product Grid
        JScrollPane productGrid = createProductGrid("All");
        root.add(productGrid, BorderLayout.CENTER);

        this.add(root);
        this.setTitle("Online Shop");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public abstract JPanel createHeader();

    JScrollPane createProductGrid(String selectedCategory) {

        // use layout manager to create scrollable grid and fixed width and height for each product box
        JPanel grid = new JPanel(new GridLayout(0, 3, 10, 100));

        for (int i = 0; i < 30; i++) {
            JPanel productBox = createProductBox();
            grid.add(productBox);
        }
        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;

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
}