package restaurantorderingsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** In-memory data store. No database or external storage is used. */
public final class RestaurantData {
    private static final List<MenuItem> MENU = new ArrayList<>();
    private static final List<Order> ORDERS = new ArrayList<>();

    static {
        MENU.add(new FoodItem("KB001", "Burger w/ Fries", "Meals",
                "Juicy beef patty with fresh vegetables and fries.", 120.25, "🍔"));
        MENU.add(new FoodItem("KB002", "Spaghetti", "Meals",
                "Classic spaghetti with rich tomato sauce.", 80.00, "🍝"));
        MENU.add(new FoodItem("KB003", "Burger Steak", "Meals",
                "Beef patty with mushroom gravy and rice.", 130.89, "🥩"));
        MENU.add(new FoodItem("KB004", "Fried Rice", "Meals",
                "Savory fried rice with mixed vegetables.", 60.00, "🍚"));
        MENU.add(new FoodItem("KB005", "Fried Chicken", "Meals",
                "Crispy fried chicken served with rice.", 60.00, "🍗"));
        MENU.add(new FoodItem("KB006", "Tocino w/ Egg", "Meals",
                "Sweet cured pork with egg and rice.", 75.00, "🍳"));
        MENU.add(new FoodItem("KB007", "Sinigang", "Meals",
                "Sour soup with pork and vegetables.", 180.75, "🍲"));
        MENU.add(new FoodItem("KB008", "Ice Tea", "Drinks",
                "Refreshing iced tea (16 oz).", 15.00, "🥤"));
    }

    private RestaurantData() { }

    public static List<MenuItem> getMenu() {
        return Collections.unmodifiableList(MENU);
    }

    public static List<Order> getOrders() {
        return Collections.unmodifiableList(ORDERS);
    }

    public static void addOrder(Order order) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null.");
        ORDERS.add(0, order);
    }
}
