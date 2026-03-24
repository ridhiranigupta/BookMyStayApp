import java.util.HashMap;

abstract class Room {
    private String roomType;
    private int beds;
    private double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 2000.0);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getBeds());
        System.out.println("Price: ₹" + getPrice());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 3500.0);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getBeds());
        System.out.println("Price: ₹" + getPrice());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 6000.0);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getBeds());
        System.out.println("Price: ₹" + getPrice());
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public HashMap<String, Integer> getAllAvailability() {
        return inventory;
    }
}

class RoomSearchService {
    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms() {
        HashMap<String, Integer> data = inventory.getAllAvailability();

        System.out.println("---- Available Rooms ----\n");

        for (String type : data.keySet()) {
            int count = data.get(type);

            if (count > 0) {
                Room room = null;

                if (type.equals("Single Room")) {
                    room = new SingleRoom();
                } else if (type.equals("Double Room")) {
                    room = new DoubleRoom();
                } else if (type.equals("Suite Room")) {
                    room = new SuiteRoom();
                }

                if (room != null) {
                    room.displayDetails();
                    System.out.println("Available: " + count + "\n");
                }
            }
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App 🏨");
        System.out.println("   Hotel Booking System v4.0");
        System.out.println("=====================================\n");

        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchAvailableRooms();

        System.out.println("Search completed successfully!");
    }
}