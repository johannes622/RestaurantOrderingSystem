package restaurantorderingsystem;

public class FoodItem extends MenuItem {
    public FoodItem(String id, String name, String category, String description,
                    double price, String icon) {
        super(id, name, category, description, price, icon);
    }

    @Override
    public String getTypeLabel() {
        return "Food";
    }
}
