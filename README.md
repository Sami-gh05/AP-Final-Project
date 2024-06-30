# Store Application 📦🛒✨

Welcome to the Store Application repository! This project is a Java Swing-based GUI application designed to simulate a simple online store experience. The application features user sign-up/sign-in functionality, an admin login panel, a product display grid, and user profile and shopping cart panels. This README will guide you through the setup, usage, and features of the application.

## Table of Contents 📜

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Code Overview](#code-overview)
- [Contributing](#contributing)
- [License](#license)

## Introduction 🌟

The Store Application provides a graphical interface for users to interact with an online store. Users can sign up, log in, browse products, view their profile, and manage their shopping cart. Admin users have a separate login to manage store operations.

## Features 🎉

✨ **User Sign-Up/Sign-In:** New users can sign up and existing users can sign in to access the store.

🔑 **Admin Login:** Admin users have a separate login interface.

🛒 **Product Display:** Products are displayed in a grid layout for easy browsing.

👤 **User Profile:** Users can view and manage their profile information.

🛍️ **Shopping Cart:** Users can add products to their shopping cart and view the cart contents.

## Prerequisites 🛠️

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 8 or later
- An IDE like IntelliJ IDEA or Eclipse for running the application
- Git for version control (optional)

## Installation 🚀

To install the Store Application, follow these steps:

1. Clone this repository:

    ```bash
    git clone https://github.com/yourusername/store-application.git
    ```

2. Open the project in your preferred IDE.

3. Ensure that the JDK is set up correctly in your IDE.

## Usage 🖥️

To use the Store Application, follow these steps:

1. Navigate to the `graphics` package.

2. Run the `MainGUI` class. This will launch the main application window.

3. You will be presented with a sign-up/sign-in screen. From here, you can either sign up as a new user, log in as an existing user, or log in as an admin.

4. Once logged in, you can browse products, view your profile, and manage your shopping cart.

## Code Overview 🧩

The application is structured into several packages, each handling different aspects of the application:

- `account`: This package contains classes that represent and manage user and admin accounts. It includes classes like User and Admin, which encapsulate the data and behaviors associated with a user and an admin respectively.
- `database.java`: This file manages database operations of the application. It includes classes like UsersDatabaseManager and ProductsDatabaseManager, handling reading from and writing to the database for users and products using JDBC.
- `graphics`: Contains the main GUI classes and panels.
- `authentication`: Manages user and admin authentication.
- `product`: Represents product-related data and operations.
- `shop`: Manages main operations of the online shop. It includes the Data class holding core application data like users and products, with methods for operations such as adding and removing products. The Data class interacts with database.java for data storage.

## Contributing 🤝

Contributions are welcome! If you have suggestions for improving this project, feel free to open an issue or create a pull request.

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

Thank you for checking out the Store Application! If you have any questions or need further assistance, feel free to contact us. Happy shopping! 🛍️📦
