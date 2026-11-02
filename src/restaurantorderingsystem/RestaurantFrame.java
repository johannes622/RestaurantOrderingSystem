package restaurantorderingsystem;

import java.awt.*;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class RestaurantFrame extends JFrame {

    private static final Color COFFEE = new Color(91, 48, 27);
    private static final Color DARK = new Color(53, 28, 17);
    private static final Color CREAM = new Color(250, 242, 226);
    private static final Color TAN = new Color(225, 194, 157);
    private static final Color ORANGE = new Color(174, 91, 43);

    private static final NumberFormat PESO =
            NumberFormat.getCurrencyInstance(new Locale("en", "PH"));

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);

    private final List<CartItem> cart = new ArrayList<>();
    private final List<MenuItem> menu = RestaurantData.getMenu();

    private String customerName = "Admin";
    private boolean sukiDiscount = true;

    private JPanel menuGrid;

    private JComboBox<String> categoryBox;
    private JButton cartButton;

    private JTable cartTable;
    private DefaultTableModel cartModel;

    private JLabel subtotalLabel;
    private JLabel discountLabel;
    private JLabel totalLabel;

    private JTextField paymentField;
    private JLabel changeLabel;

    private JCheckBox sukiCheck;

    private JTable historyTable;
    private DefaultTableModel historyModel;

    public RestaurantFrame() {
        setTitle("Restaurant Ordering System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 740);
        setMinimumSize(new Dimension(950, 650));
        setLocationRelativeTo(null);

        cards.add(buildLoginPage(), "login");
        cards.add(buildMenuPage(), "menu");
        cards.add(buildCartPage(), "cart");
        cards.add(buildHistoryPage(), "history");

        add(cards);
        showCard("login");
    }

    private void showCard(String name) {
        cardLayout.show(cards, name);

        if ("menu".equals(name)) {
            renderMenu();
            updateCartButton();
        }

        if ("cart".equals(name)) {
            refreshCart();
        }

        if ("history".equals(name)) {
            refreshHistory();
        }
    }

    private JPanel page(Color color) {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(color);
        panel.setBorder(new EmptyBorder(16, 18, 16, 18));
        return panel;
    }

    private JLabel title(String text, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        label.setForeground(color);
        return label;
    }

    private JButton button(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBackground(COFFEE);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBorder(new EmptyBorder(9, 14, 9, 14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    // ==================== LOGIN PAGE ====================

    private JPanel buildLoginPage() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(DARK);

        JPanel box = new JPanel();
        box.setPreferredSize(new Dimension(420, 440));
        box.setBackground(CREAM);

        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TAN, 2),
                new EmptyBorder(28, 32, 28, 32)
        ));

        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel logo = title("Kusina ni Bai", 30, COFFEE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = title(
                "O R D E R I N G   S Y S T E M", 12, DARK
        );
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginTitle = title(
                "Welcome! Please log in", 18, DARK
        );
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField("");
        JPasswordField password = new JPasswordField("");

        styleInput(username);
        styleInput(password);

        JLabel usernameLabel = leftLabel("Username");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordLabel = leftLabel("Password");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(logo);
        box.add(Box.createVerticalStrut(4));
        box.add(subtitle);
        box.add(Box.createVerticalStrut(28));
        box.add(loginTitle);
        box.add(Box.createVerticalStrut(24));

        box.add(usernameLabel);
        box.add(Box.createVerticalStrut(5));
        box.add(username);
        box.add(Box.createVerticalStrut(14));

        box.add(passwordLabel);
        box.add(Box.createVerticalStrut(5));
        box.add(password);
        box.add(Box.createVerticalStrut(24));

        JButton login = button("Log In");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setMaximumSize(new Dimension(300, 42));

        login.addActionListener(e -> {
            String u = username.getText().trim();
            String p = new String(password.getPassword());

            if (u.equals("admin") && p.equals("admin123")) {
                customerName = "Admin";
                showCard("menu");
            } else if (!u.equals("admin") && !p.equals("admin123")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Credentials.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
        }
             else {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter a username and password.",
                        "Login required",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        box.add(login);
        box.add(Box.createVerticalStrut(16));



        outer.add(box);
        return outer;
    }

    private JLabel leftLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(DARK);
        return label;
    }

    private void styleInput(JComponent input) {
        input.setPreferredSize(new Dimension(300, 36));
        input.setMinimumSize(new Dimension(300, 36));
        input.setMaximumSize(new Dimension(300, 36));

        input.setFont(new Font("SansSerif", Font.PLAIN, 14));

        input.setBorder(
                BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TAN),
                new EmptyBorder(6, 8, 6, 8)
                )
        );

        input.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // ==================== MENU PAGE ====================

    private JPanel buildMenuPage() {
        JPanel panel = page(CREAM);

        JPanel header = new JPanel(new BorderLayout(12, 8));
        header.setBackground(COFFEE);
        header.setBorder(new EmptyBorder(14, 18, 14, 18));

        JPanel branding = new JPanel();
        branding.setOpaque(false);
        branding.setLayout(new BoxLayout(branding, BoxLayout.Y_AXIS));

        branding.add(title("Kusina ni Bai", 27, Color.WHITE));
        branding.add(title(
                "ORDERING SYSTEM  •  Good Food, Good Mood",
                11,
                TAN
        ));

        header.add(branding, BorderLayout.WEST);

        JPanel tools = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 8, 5)
        );
        tools.setOpaque(false);



        categoryBox = new JComboBox<>(
                new String[]{"All", "Meals", "Drinks"}
        );

        cartButton = button("Cart (0)");
        JButton history = button("History");


        cartButton.addActionListener(e -> showCard("cart"));
        history.addActionListener(e -> showCard("history"));


        tools.add(categoryBox);

        tools.add(cartButton);
        tools.add(history);

        header.add(tools, BorderLayout.EAST);

        menuGrid = new JPanel(new GridLayout(0, 4, 12, 12));
        menuGrid.setBackground(CREAM);

        JScrollPane scroll = new JScrollPane(menuGrid);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(CREAM);

        JLabel welcome = title(
                "Menu  |  Welcome, " + customerName,
                18,
                DARK
        );

        panel.add(header, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(welcome, BorderLayout.SOUTH);

        return panel;
    }

    private void renderMenu() {
        if (menuGrid == null) {
            return;
        }

        menuGrid.removeAll();

        String category = categoryBox == null
                ? "All"
                : (String) categoryBox.getSelectedItem();

        int count = 0;

        for (MenuItem item : menu) {
            if (!"All".equals(category)
                    && !item.getCategory().equals(category)) {
                continue;
            }

            menuGrid.add(createMenuCard(item));
            count++;
        }

        if (count == 0) {
            JPanel empty = new JPanel(new GridBagLayout());
            empty.setBackground(CREAM);

            empty.add(title(
                    "No menu items match your search.",
                    16,
                    COFFEE
            ));

            menuGrid.setLayout(new GridLayout(1, 1));
            menuGrid.add(empty);
        } else {
            menuGrid.setLayout(new GridLayout(0, 4, 12, 12));
        }

        menuGrid.revalidate();
        menuGrid.repaint();
    }

    // ==================== FOOD PHOTO LOADER ====================

    private ImageIcon loadFoodImage(String fileName, int width, int height) {
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }

        String path =
                "/restaurantorderingsystem/images/" + fileName;

        URL imageURL = getClass().getResource(path);

        if (imageURL == null) {
            System.err.println("Image not found: " + path);
            return null;
        }

        ImageIcon original = new ImageIcon(imageURL);

        Image resized = original.getImage().getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );

        return new ImageIcon(resized);
    }

    // Maps menu item IDs to the six image filenames.
    // Change these IDs if your RestaurantData uses different IDs.
    private String getFoodImageName(MenuItem item) {
        String name = item.getName().toLowerCase();

        if (name.contains("burger w/ fries")) {
            return "burger steak.jpg";
        } else if (name.contains("pizza")) {
            return "pizza.jpg";
        } else if (name.contains("burger steak")) {
            return "burger steak.jpg";
        } else if (name.contains("fries")) {
            return "fries.jpg";
        } else if (name.contains("chicken")) {
            return "378.jpg";
        } else if (name.contains("tea")) {
            return "377.jpg";
        } else if (name.contains("spaghetti")) {
            return "spaghetti.jpg";
        } else if (name.contains("sinigang")) {
            return "sinigang.jpg";
        } else if (name.contains("tocino")) {
            return "tocino w egg.jpg";
        } else if (name.contains("fried rice")) {
            return "fried rice.jpg";
        }

        return "default.jpg";
    }

    private JPanel createMenuCard(MenuItem item) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TAN),
                new EmptyBorder(8, 8, 8, 8)
                ));

        JLabel id = new JLabel(item.getId());
        id.setForeground(ORANGE);
        id.setFont(new Font("SansSerif", Font.BOLD, 10));
        id.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Dimension imageSize = new Dimension(220, 115);
        imageLabel.setPreferredSize(imageSize);
        imageLabel.setMinimumSize(imageSize);
        imageLabel.setMaximumSize(imageSize);

        ImageIcon foodImage = loadFoodImage(
                getFoodImageName(item),
                220,
                115
        );

        if (foodImage != null) {
            imageLabel.setIcon(foodImage);
        } else {
            imageLabel.setText("Food photo unavailable");
            imageLabel.setForeground(Color.GRAY);
            imageLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        }

        JLabel name = title(item.getName(), 15, DARK);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea description = new JTextArea(item.getDescription());
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setEditable(false);
        description.setFocusable(false);
        description.setOpaque(false);
        description.setFont(new Font("SansSerif", Font.PLAIN, 12));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel price = title(
                PESO.format(item.getPrice()),
                15,
                COFFEE
        );
        price.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton add = button("Add to Cart");
        add.setAlignmentX(Component.CENTER_ALIGNMENT);
        add.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        add.addActionListener(e -> {
            addToCart(item);
            updateCartButton();

            JOptionPane.showMessageDialog(
                    this,
                    item.getName() + " added to cart.",
                    "Cart updated",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        card.add(id);
        card.add(Box.createVerticalStrut(4));
        card.add(imageLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(name);
        card.add(Box.createVerticalStrut(5));
        card.add(description);
        card.add(Box.createVerticalStrut(7));
        card.add(price);
        card.add(Box.createVerticalGlue());
        card.add(Box.createVerticalStrut(8));
        card.add(add);

        return card;
    }

    private void addToCart(MenuItem item) {
        for (CartItem cartItem : cart) {
            if (cartItem.getMenuItem().getId().equals(item.getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }

        cart.add(new CartItem(item, 1));
    }

    private void updateCartButton() {
        if (cartButton == null) {
            return;
        }

        int quantity = 0;

        for (CartItem item : cart) {
            quantity += item.getQuantity();
        }

        cartButton.setText("Cart (" + quantity + ")");
    }

    // ==================== CART PAGE ====================

    private JPanel buildCartPage() {
        JPanel panel = page(CREAM);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        header.add(title("Food Cart", 24, COFFEE), BorderLayout.WEST);

        JButton back = button("← Back to Menu");
        back.addActionListener(e -> showCard("menu"));

        header.add(back, BorderLayout.EAST);

        String[] columns = {
                "ID", "Food Name", "Price", "Qty", "Total"
        };

        cartModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 3;
            }
        };

        cartTable = new JTable(cartModel);
        styleTable(cartTable);

        cartTable.getColumnModel().getColumn(3).setCellEditor(
                new DefaultCellEditor(
                        new JComboBox<>(new String[]{
                                "1", "2", "3", "4", "5",
                                "6", "7", "8", "9", "10"
                        })
                )
        );

        cartTable.getModel().addTableModelListener(e -> {
            if (e.getType()
                    == javax.swing.event.TableModelEvent.UPDATE
                    && e.getColumn() == 3) {

                int row = e.getFirstRow();

                if (row >= 0 && row < cart.size()) {
                    try {
                        int quantity = Integer.parseInt(
                                String.valueOf(
                                        cartModel.getValueAt(row, 3)
                                )
                        );

                        cart.get(row).setQuantity(quantity);
                    } catch (NumberFormatException ignored) {
                        // Ignore invalid quantity values.
                    }

                    refreshCart();
                }
            }
        });

        JPanel totals = new JPanel(new GridLayout(0, 2, 8, 8));
        totals.setBackground(new Color(242, 222, 197));
        totals.setBorder(new EmptyBorder(14, 16, 14, 16));

        sukiCheck = new JCheckBox("Use Suki Card (10% OFF)", true);
        sukiCheck.setOpaque(false);

        sukiCheck.addActionListener(e -> {
            sukiDiscount = sukiCheck.isSelected();
            refreshCart();
        });

        subtotalLabel = new JLabel();
        discountLabel = new JLabel();
        totalLabel = title("", 20, DARK);

        totals.add(sukiCheck);
        totals.add(new JLabel(""));
        totals.add(new JLabel("Subtotal:"));
        totals.add(subtotalLabel);
        totals.add(new JLabel("Discount:"));
        totals.add(discountLabel);
        totals.add(title("Total Amount:", 16, DARK));
        totals.add(totalLabel);

        JButton clear = button("Clear Cart");

        clear.addActionListener(e -> {
            cart.clear();
            refreshCart();
            updateCartButton();
        });

        JButton checkout = button("Proceed to Payment →");

        checkout.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Your cart is empty.",
                        "Empty cart",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            showPaymentDialog();
        });

        JPanel bottom = new JPanel(new BorderLayout(10, 10));
        bottom.setOpaque(false);
        bottom.add(totals, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setOpaque(false);
        actions.add(clear);
        actions.add(checkout);

        bottom.add(actions, BorderLayout.SOUTH);

        panel.add(header, BorderLayout.NORTH);
        panel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshCart() {
        if (cartModel == null) {
            return;
        }

        cartModel.setRowCount(0);

        for (CartItem item : cart) {
            cartModel.addRow(new Object[]{
                    item.getMenuItem().getId(),
                    item.getMenuItem().getName(),
                    PESO.format(item.getMenuItem().getPrice()),
                    item.getQuantity(),
                    PESO.format(item.getLineTotal()),
                    "Remove"
            });
        }

        double subtotal = getSubtotal();
        double discount = getDiscount();

        subtotalLabel.setText(PESO.format(subtotal));
        discountLabel.setText("-" + PESO.format(discount));
        totalLabel.setText(PESO.format(subtotal - discount));

        updateCartButton();
    }

    private double getSubtotal() {
        double total = 0;

        for (CartItem item : cart) {
            total += item.getLineTotal();
        }

        return total;
    }

    private double getDiscount() {
        return sukiDiscount ? getSubtotal() * 0.10 : 0;
    }

    // ==================== PAYMENT ====================

    private void showPaymentDialog() {
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel amount = new JLabel(
                PESO.format(getSubtotal() - getDiscount())
        );

        paymentField = new JTextField("");

        changeLabel = new JLabel(PESO.format(0));

        JCheckBox discountBox = new JCheckBox(
                "Apply Suki Card 10% discount",
                sukiDiscount
        );

        discountBox.addActionListener(e -> {
            sukiDiscount = discountBox.isSelected();
            amount.setText(
                    PESO.format(getSubtotal() - getDiscount())
            );
        });

        JTextField nameField = new JTextField();

        form.add(new JLabel("Customer name:"));
        form.add(nameField);
        form.add(new JLabel("Amount due:"));
        form.add(amount);
        form.add(discountBox);
        form.add(new JLabel(""));
        form.add(new JLabel("Cash received (₱):"));
        form.add(paymentField);
        form.add(new JLabel("Change:"));
        form.add(changeLabel);

        paymentField.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {

                    private void update() {
                        try {
                            double paid = Double.parseDouble(
                                    paymentField.getText().trim()
                            );

                            double due = getSubtotal() - getDiscount();

                            changeLabel.setText(
                                    PESO.format(Math.max(0, paid - due))
                            );

                        } catch (NumberFormatException ex) {
                            changeLabel.setText("Enter valid amount");
                        }
                    }

                    @Override
                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e) {
                        update();
                    }

                    @Override
                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e) {
                        update();
                    }

                    @Override
                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e) {
                        update();
                    }
                }
        );

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Payment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            double paid = Double.parseDouble(
                    paymentField.getText().trim()
            );

            double due = getSubtotal() - getDiscount();

            if (paid < due) {
                JOptionPane.showMessageDialog(
                        this,
                        "Insufficient payment. Please enter at least "
                                + PESO.format(due),
                        "Payment error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                name = "Guest";
            }

            Order order = new Order(name, cart, paid, sukiDiscount);

            RestaurantData.addOrder(order);

            showReceipt(order);

            cart.clear();
            refreshCart();
            updateCartButton();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid numeric payment amount.",
                    "Invalid amount",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Order error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void showReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();

        receipt.append("       RESTAURANT ORDERING SYSTEM\n");
        receipt.append("Thank you for dining with us!\n\n");
        receipt.append("Transaction: ")
                .append(order.getTransactionId()).append("\n");
        receipt.append("Date: ")
                .append(order.getFormattedDate()).append("\n");
        receipt.append("Customer: ")
                .append(order.getCustomerName()).append("\n");
        receipt.append("-----------------------------------\n");

        for (CartItem item : order.getItems()) {
            receipt.append(item.getMenuItem().getName())
                    .append(" x")
                    .append(item.getQuantity())
                    .append("  ")
                    .append(PESO.format(item.getLineTotal()))
                    .append("\n");
        }

        receipt.append("-----------------------------------\n");
        receipt.append("Subtotal: ")
                .append(PESO.format(order.getSubtotal())).append("\n");
        receipt.append("Discount: -")
                .append(PESO.format(order.calculateDiscount())).append("\n");
        receipt.append("TOTAL: ")
                .append(PESO.format(order.getTotal())).append("\n");
        receipt.append("Payment: ")
                .append(PESO.format(order.getPayment())).append("\n");
        receipt.append("Change: ")
                .append(PESO.format(order.getChange())).append("\n");

        JTextArea text = new JTextArea(receipt.toString(), 20, 38);
        text.setEditable(false);
        text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(text),
                "Receipt",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==================== TRANSACTION HISTORY ====================

    private JPanel buildHistoryPage() {
        JPanel panel = page(CREAM);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        header.add(title("Transaction History", 24, COFFEE),
                BorderLayout.WEST);

        JButton back = button("← Back to Menu");
        back.addActionListener(e -> showCard("menu"));
        header.add(back, BorderLayout.EAST);

        historyModel = new DefaultTableModel(
                new String[]{
                        "Transaction ID",
                        "Date & Time",
                        "Customer",
                        "Items",
                        "Total"
                },
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        historyTable = new JTable(historyModel);
        styleTable(historyTable);

        JButton details = button("View Details");

        details.addActionListener(e -> {
            int row = historyTable.getSelectedRow();

            if (row < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Select a transaction first."
                );
                return;
            }

            int modelRow = historyTable.convertRowIndexToModel(row);
            List<Order> orders = RestaurantData.getOrders();

            if (modelRow >= 0 && modelRow < orders.size()) {
                showOrderDetails(orders.get(modelRow));
            }
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);
        bottom.add(details);

        panel.add(header, BorderLayout.NORTH);
        panel.add(new JScrollPane(historyTable), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshHistory() {
        if (historyModel == null) {
            return;
        }

        historyModel.setRowCount(0);

        for (Order order : RestaurantData.getOrders()) {
            StringBuilder names = new StringBuilder();

            for (CartItem item : order.getItems()) {
                if (names.length() > 0) {
                    names.append(", ");
                }

                names.append(item.getMenuItem().getName())
                        .append(" x")
                        .append(item.getQuantity());
            }

            historyModel.addRow(new Object[]{
                    order.getTransactionId(),
                    order.getFormattedDate(),
                    order.getCustomerName(),
                    names.toString(),
                    PESO.format(order.getTotal())
            });
        }
    }

    private void showOrderDetails(Order order) {
        StringBuilder details = new StringBuilder();

        details.append("Transaction ID: ")
                .append(order.getTransactionId()).append("\n");
        details.append("Date: ")
                .append(order.getFormattedDate()).append("\n");
        details.append("Customer: ")
                .append(order.getCustomerName()).append("\n\n");

        for (CartItem item : order.getItems()) {
            details.append(item.getMenuItem().getName())
                    .append(" | Qty: ")
                    .append(item.getQuantity())
                    .append(" | ")
                    .append(PESO.format(item.getLineTotal()))
                    .append("\n");
        }

        details.append("\nSubtotal: ")
                .append(PESO.format(order.getSubtotal()));
        details.append("\nDiscount: -")
                .append(PESO.format(order.calculateDiscount()));
        details.append("\nTotal: ")
                .append(PESO.format(order.getTotal()));
        details.append("\nPayment: ")
                .append(PESO.format(order.getPayment()));
        details.append("\nChange: ")
                .append(PESO.format(order.getChange()));

        JOptionPane.showMessageDialog(
                this,
                details.toString(),
                "Transaction Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==================== TABLE STYLING ====================

    private void styleTable(JTable table) {
        table.setRowHeight(32);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));

        table.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 13)
        );

        table.getTableHeader().setBackground(TAN);
        table.getTableHeader().setForeground(DARK);
        table.setSelectionBackground(new Color(240, 215, 186));
        table.setGridColor(new Color(226, 210, 188));
        table.setFillsViewportHeight(true);
    }
}
