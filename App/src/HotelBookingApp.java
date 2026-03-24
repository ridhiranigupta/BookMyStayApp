import java.util.HashMap;

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("---- Current Room Inventory ----\n");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Available: " + inventory.get(roomType));
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App 🏨");
        System.out.println("   Hotel Booking System v3.1");
        System.out.println("=====================================\n");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nUpdating availability...\n");

        inventory.updateAvailability("Single Room", 4);
        inventory.updateAvailability("Suite Room", 1);

        inventory.displayInventory();

        System.out.println("\nApplication executed successfully!");
    }
}