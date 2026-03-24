import java.util.*;

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingHistory {
    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {
    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void displayAllBookings() {
        System.out.println("\n---- Booking History ----\n");
        for (Reservation r : history.getAllReservations()) {
            System.out.println("ID: " + r.getReservationId() +
                    " | Guest: " + r.getGuestName() +
                    " | Room: " + r.getRoomType());
        }
    }

    public void generateSummary() {
        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : history.getAllReservations()) {
            summary.put(r.getRoomType(),
                    summary.getOrDefault(r.getRoomType(), 0) + 1);
        }

        System.out.println("\n---- Booking Summary ----\n");
        for (String type : summary.keySet()) {
            System.out.println(type + " Booked: " + summary.get(type));
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App 🏨");
        System.out.println("   Hotel Booking System v8.0");
        System.out.println("=====================================\n");

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService(history);

        history.addReservation(new Reservation("SR1", "Alice", "Single Room"));
        history.addReservation(new Reservation("DR1", "Bob", "Double Room"));
        history.addReservation(new Reservation("SR2", "Charlie", "Single Room"));
        history.addReservation(new Reservation("SU1", "Diana", "Suite Room"));

        reportService.displayAllBookings();
        reportService.generateSummary();

        System.out.println("\nReporting completed successfully!");
    }
}