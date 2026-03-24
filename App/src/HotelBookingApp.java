import java.util.*;

class AddOnService {
    private String name;
    private double price;

    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class AddOnServiceManager {
    private HashMap<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
        System.out.println("Service added to " + reservationId + ": " + service.getName());
    }

    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getPrice();
            }
        }

        return total;
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        System.out.println("\nServices for Reservation " + reservationId + ":");

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println(s.getName() + " - ₹" + s.getPrice());
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App 🏨");
        System.out.println("   Hotel Booking System v7.0");
        System.out.println("=====================================\n");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId1 = "SR1";
        String reservationId2 = "DR1";

        manager.addService(reservationId1, new AddOnService("Breakfast", 500));
        manager.addService(reservationId1, new AddOnService("Spa", 1500));
        manager.addService(reservationId2, new AddOnService("Airport Pickup", 800));

        manager.displayServices(reservationId1);
        manager.displayServices(reservationId2);

        System.out.println("\nAdd-on services processed successfully!");
    }
}