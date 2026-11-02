package restaurantorderingsystem;

/** Abstraction: common details shared by people using the application. */
public abstract class Person {
    private final String name;

    protected Person(String name) {
        this.name = name == null || name.trim().isEmpty() ? "Guest" : name.trim();
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();
}
