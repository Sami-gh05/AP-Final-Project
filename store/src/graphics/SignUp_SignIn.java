package graphics;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUp_SignIn extends Application {
    // Set up the fields
    private final VBox root = new VBox(10);
    private final Button loginButton = new Button();
    private final Label Username = new Label();
    private final TextField usernameField = new TextField();
    private final Label Password = new Label();
    private final TextField passwordField = new TextField();
    private final Label Name = new Label();
    private final TextField nameField = new TextField();
    private final Label Phone = new Label();
    private final TextField phoneField = new TextField();
    private final Label Address = new Label();
    private final TextField addressField = new TextField();

    public SignUp_SignIn() {
        // Set up the default fields
        Username.setText("Username:");
        usernameField.setPromptText("Enter your Username");
        Password.setText("Password:");
        passwordField.setPromptText("Enter your password");
        Name.setText("Name:");
        nameField.setPromptText("Enter your name");
        Phone.setText("Phone:");
        phoneField.setPromptText("Enter your phone number");
        Address.setText("Address:");
        addressField.setPromptText("Enter your address");

        // Add the fields to the grid
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(Username, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(Password, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.setVisible(false);

        // Add event handlers to the buttons
        Button signUp = new Button("Sign Up");
        signUp.setOnAction(e -> {
            resetGridPane(gridPane);
            gridPane.add(Name, 0, 2);
            gridPane.add(nameField, 1, 2);
            gridPane.add(Phone, 0, 3);
            gridPane.add(phoneField, 1, 3);
            gridPane.add(Address, 0, 4);
            gridPane.add(addressField, 1, 4);
            gridPane.setVisible(true);
            loginButton.setText("Sign Up");
            loginButton.setVisible(true);
        });

        Button signIn = new Button("Sign In");
        signIn.setOnAction(e -> {
            resetGridPane(gridPane);
            gridPane.setVisible(true);
            loginButton.setText("Sign In");
            loginButton.setVisible(true);
        });

        Button admin = new Button("Admin Login");
        admin.setOnAction(e -> {
            resetGridPane(gridPane);
            gridPane.setVisible(true);
            loginButton.setText("Admin login");
            loginButton.setVisible(true);
        });

        loginButton.setOnAction(e -> {
            ProductsPanel productsPanel = new ProductsPanel();
            try {
                productsPanel.start(new Stage());
                Stage currentStage = (Stage) loginButton.getScene().getWindow();
                currentStage.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        // Add the components to the root VBox
        root.setAlignment(Pos.CENTER);
        Label label = new Label("Welcome to our store!");
        root.getChildren().addAll(label, signUp, signIn, admin, gridPane, loginButton);
        loginButton.setVisible(false);
    }

    // Method to reset the GridPane
    private void resetGridPane(GridPane gridPane) {
        gridPane.getChildren().clear();
        gridPane.add(Username, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(Password, 0, 1);
        gridPane.add(passwordField, 1, 1);
    }

    public VBox getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up/Sign In");
        primaryStage.setScene(new Scene(getRoot(), 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showSignUpSignIn() {
        launch();
    }
}