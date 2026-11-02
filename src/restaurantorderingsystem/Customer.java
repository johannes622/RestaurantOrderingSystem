package restaurantorderingsystem;

public class Customer extends Person {
    public Customer(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}
