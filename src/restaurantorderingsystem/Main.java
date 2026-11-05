package restaurantorderingsystem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                System.err.println("Could not apply system look and feel: " + ex.getMessage());
            }
            new RestaurantFrame().setVisible(true);
        });
    }
}
