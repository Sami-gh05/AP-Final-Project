package graphics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ProductsPanel extends Application {

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Header
        HBox header = createHeader();
        root.setTop(header);

        // Product Grid
        GridPane productGrid = createProductGrid();
        root.setCenter(productGrid);

        // Filters
        VBox filters = createFilters();
        root.setRight(filters);

        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Online Shop");
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox(10);
        header.setPadding(new Insets(10));

        Button userPanelButton = new Button("User Panel");
        ImageView logo = new ImageView(new Image(getClass().getResource("/Logo.png").toExternalForm()));
        logo.setFitHeight(50);
        logo.setFitWidth(50);
        logo.setPreserveRatio(true);
        logo.setSmooth(true);
        logo.setCache(true);

        Button cartButton = new Button("Cart");

        header.getChildren().addAll(logo, userPanelButton, cartButton);
        return header;
    }

    private GridPane createProductGrid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                VBox productBox = createProductBox();
                grid.add(productBox, j, i);
            }
        }

        return grid;
    }

    private VBox createProductBox() {
        VBox box = new VBox(5);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        ImageView productImage = new ImageView(new Image("file:product.png"));
        Label productName = new Label("Product Name");
        productName.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Label productPrice = new Label("$1000");
        productPrice.setStyle("-fx-text-fill: green;");
        Label productRating = new Label("★★★★★");
        productRating.setStyle("-fx-text-fill: gold;");

        box.getChildren().addAll(productImage, productName, productPrice, productRating);
        return box;
    }

    private VBox createFilters() {
        VBox filters = new VBox(10);
        filters.setPadding(new Insets(10));
        filters.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        Label categoryLabel = new Label("Category");
        categoryLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Car", "Phone", "Laptop");

        Label priceLabel = new Label("Price");
        priceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Slider priceSlider = new Slider(0, 2000, 1000);

        CheckBox availableGoodsCheckBox = new CheckBox("Only available goods");

        filters.getChildren().addAll(categoryLabel, categoryComboBox, priceLabel, priceSlider, availableGoodsCheckBox);
        return filters;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Parent getRoot() {
        BorderPane root = new BorderPane();

        // Header
        HBox header = createHeader();
        root.setTop(header);

        // Product Grid
        GridPane productGrid = createProductGrid();
        root.setCenter(productGrid);

        // Filters
        VBox filters = createFilters();
        root.setRight(filters);

        return root;
    }
}