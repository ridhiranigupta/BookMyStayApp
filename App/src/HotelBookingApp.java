import java.io.*;
import java.util.*;

class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Integer> inventory;
    List<String> bookings;

    public SystemState(Map<String, Integer> inventory, List<String> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public List<String> getBookings() {
        return bookings;
    }
}

class PersistenceService {
    private static final String FILE_NAME = "system_state.dat";

    public void save(SystemState state) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(state);
            System.out.println("State saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    public SystemState load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("State loaded successfully.");
            return (SystemState) in.readObject();
        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return null;
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App 🏨");
        System.out.println("   Hotel Booking System v12.0");
        System.out.println("=====================================\n");

        PersistenceService service = new PersistenceService();

        SystemState state = service.load();

        Map<String, Integer> inventory;
        List<String> bookings;

        if (state == null) {
            inventory = new HashMap<>();
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);

            bookings = new ArrayList<>();
        } else {
            inventory = state.getInventory();
            bookings = state.getBookings();
        }

        System.out.println("\nCurrent Inventory: " + inventory);
        System.out.println("Booking History: " + bookings);

        System.out.println("\nAdding new booking...");

        if (inventory.get("Single Room") > 0) {
            inventory.put("Single Room", inventory.get("Single Room") - 1);
            bookings.add("SR" + (bookings.size() + 1));
        }

        System.out.println("Updated Inventory: " + inventory);
        System.out.println("Updated Bookings: " + bookings);

        service.save(new SystemState(inventory, bookings));

        System.out.println("\nSystem state persisted successfully!");
    }
}