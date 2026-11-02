package restaurantorderingsystem;

/** Abstract parent class for menu products. */
public abstract class MenuItem {
    private final String id;
    private final String name;
    private final String category;
    private final String description;
    private final double price;
    private final String icon;

    protected MenuItem(String id, String name, String category, String description,
                       double price, String icon) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.icon = icon;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getIcon() { return icon; }

    public abstract String getTypeLabel();
}
