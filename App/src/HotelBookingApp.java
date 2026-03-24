import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, -1);
    }

    public void decrement(String type) throws InvalidBookingException {
        int available = getAvailability(type);

        if (available <= 0) {
            throw new InvalidBookingException("No availability for " + type);
        }

        inventory.put(type, available - 1);
    }

    public boolean isValidRoomType(String type) {
        return inventory.containsKey(type);
    }
}

class BookingValidator {
    private RoomInventory inventory;

    public BookingValidator(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void validate(String guestName, String roomType) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("Room not available: " + roomType);
        }
    }
}

class BookingService {
    private RoomInventory inventory;
    private BookingValidator validator;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        this.validator = new BookingValidator(inventory);
    }

    public void bookRoom(String guestName, String roomType) {
        try {
            validator.validate(guestName, roomType);
            inventory.decrement(roomType);
            System.out.println("Booking successful for " + guestName + " -> " + roomType);
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}

public class HotelBookingApp{
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App 🏨");
        System.out.println("   Hotel Booking System v9.0");
        System.out.println("=====================================\n");

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.bookRoom("Alice", "Single Room");
        service.bookRoom("", "Double Room");
        service.bookRoom("Bob", "Suite Room");
        service.bookRoom("Charlie", "Luxury Room");

        System.out.println("\nSystem continues running safely after errors.");
    }
}