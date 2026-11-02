package restaurantorderingsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Composition: an order owns its copied list of order lines. */
public class Order implements Discountable {
    private static int nextNumber = 1;

    private final String transactionId;
    private final LocalDateTime dateTime;
    private final String customerName;
    private final List<CartItem> items;
    private final double payment;
    private final boolean hasSukiDiscount;

    public Order(String customerName, List<CartItem> cartItems, double payment, boolean hasSukiDiscount) {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cannot create an order with an empty cart.");
        }
        this.transactionId = String.format("TRN%04d", nextNumber++);
        this.dateTime = LocalDateTime.now();
        this.customerName = customerName;
        this.items = new ArrayList<>();
        for (CartItem item : cartItems) {
            this.items.add(new CartItem(item.getMenuItem(), item.getQuantity()));
        }
        this.payment = payment;
        this.hasSukiDiscount = hasSukiDiscount;
    }

    public String getTransactionId() { return transactionId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getFormattedDate() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy - hh:mm a"));
    }
    public String getCustomerName() { return customerName; }
    public List<CartItem> getItems() { return Collections.unmodifiableList(items); }
    public double getPayment() { return payment; }
    public boolean hasSukiDiscount() { return hasSukiDiscount; }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) total += item.getLineTotal();
        return total;
    }

    @Override
    public double calculateDiscount() {
        return hasSukiDiscount ? getSubtotal() * 0.10 : 0;
    }

    public double getTotal() { return getSubtotal() - calculateDiscount(); }
    public double getChange() { return payment - getTotal(); }
}
