import java.util.*;

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decrement(String type) {
        inventory.put(type, getAvailability(type) - 1);
    }

    public void increment(String type) {
        inventory.put(type, getAvailability(type) + 1);
    }
}

class BookingService {
    private RoomInventory inventory;
    private Map<String, String> bookings;
    private Stack<String> rollbackStack;
    private int idCounter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        bookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    public String book(String guest, String type) {
        if (inventory.getAvailability(type) <= 0) {
            System.out.println("Booking failed for " + guest + " -> " + type);
            return null;
        }

        String id = type.substring(0, 2).toUpperCase() + idCounter++;
        bookings.put(id, type);
        inventory.decrement(type);

        System.out.println("Booked: " + guest + " -> " + type + " | ID: " + id);
        return id;
    }

    public void cancel(String reservationId) {
        if (!bookings.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Invalid ID " + reservationId);
            return;
        }

        String type = bookings.remove(reservationId);
        rollbackStack.push(reservationId);
        inventory.increment(type);

        System.out.println("Cancelled: " + reservationId + " | " + type);
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack: " + rollbackStack);
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App 🏨");
        System.out.println("   Hotel Booking System v10.0");
        System.out.println("=====================================\n");

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        String id1 = service.book("Alice", "Single Room");
        String id2 = service.book("Bob", "Double Room");

        service.cancel(id1);
        service.cancel("XX99");

        service.showRollbackStack();

        System.out.println("\nSystem state restored successfully!");
    }
}