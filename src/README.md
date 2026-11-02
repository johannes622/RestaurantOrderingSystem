# Restaurant Ordering System (Java Swing)

A desktop restaurant ordering system based on the reference design, built with **Java Swing** and in-memory Java collections. It does not use a database, JavaFX, a game engine, web technologies, or mobile technologies.

## Features
- Login screen with restaurant branding
- Menu cards with categories, search, descriptions, prices, and add-to-cart
- Shopping cart and quantity editing
- Optional Suki Card discount (10%)
- Cash payment validation and change calculation
- Receipt after checkout
- Transaction history and transaction details
- Philippine peso formatting
- Warm coffee-brown and cream color palette

## Requirements
- JDK 8 or newer
- NetBeans, IntelliJ IDEA, Eclipse, or a terminal

## Demo login
- Username: `admin`
- Password: `admin123`

For a classroom demo, any non-empty username and password also proceeds to the menu. Replace this with a proper validation policy if your instructor requires strict login behavior.

## Run in NetBeans
1. Create a Java Application project named `RestaurantOrderingSystem`.
2. Copy the `src/restaurantorderingsystem` folder into your project's `src` folder.
3. Set `restaurantorderingsystem.Main` as the main class.
4. Run the project.

## Run from terminal
From the project folder:

```bash
javac -d out src/restaurantorderingsystem/*.java
java -cp out restaurantorderingsystem.Main
```

## OOP concepts demonstrated
- **Encapsulation:** fields in `Person`, `MenuItem`, `CartItem`, and `Order` are private and accessed through methods.
- **Inheritance:** `Customer` extends `Person`; `FoodItem` extends `MenuItem`.
- **Polymorphism:** `getRole()` and `getTypeLabel()` are overridden methods.
- **Abstraction:** `Person` and `MenuItem` are abstract classes.
- **Interface:** `Discountable` defines discount calculation.
- **Composition/Aggregation:** `Order` owns copied cart lines; cart lines refer to menu items.
- **Exception handling:** invalid prices, quantities, empty orders, and invalid payment input are checked.

## Data storage note
Menu items and transactions are held in Java `List` collections in memory. Data is cleared when the application closes, as required by the no-database project rules.

## Suggested project structure
```text
RestaurantOrderingSystem/
├── src/
│   └── restaurantorderingsystem/
│       ├── Main.java
│       ├── RestaurantFrame.java
│       ├── RestaurantData.java
│       ├── Person.java
│       ├── Customer.java
│       ├── MenuItem.java
│       ├── FoodItem.java
│       ├── CartItem.java
│       ├── Discountable.java
│       └── Order.java
└── README.md
```

## Before submission
- Add your actual group members and their roles to your documentation.
- Push the project to GitHub and make sure commits reflect each member's contributions.
- Capture screenshots of the running application.
- Record a walkthrough and have each member explain the code they contributed and the OOP concepts used.
- Zip the entire project folder for Google Drive submission.
