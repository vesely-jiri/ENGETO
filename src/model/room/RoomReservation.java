package model.room;

import model.Guest;
import util.ConsoleColor;
import util.DebugManager;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a reservation for a room within a specified date range and a list of guests
 */
public class RoomReservation {
    private int id;
    private final int roomId;
    private LocalDate from, to;
    private List<Guest> guests;
    private BookingType type;
    private static List<RoomReservation> reservations = new ArrayList<>();

    public RoomReservation(LocalDate from, LocalDate to, List<Guest> guests, BookingType type, int roomId) {
        DebugManager.print(ConsoleColor.BLUE + "Creating room reservation: " + from + " - " + to + " (" + guests + ")");
        this.from = from;
        this.to = to;
        this.guests = guests;
        this.type = type;
        this.roomId = roomId;
        reservations.add(this);
        this.id = reservations.size();
    }

    public void destroy() {
        reservations.remove(this);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        Room room = Room.getRoomFromId(roomId);
        assert room != null;
        for (int i = 0; i < getDuration().toDays(); i++) {
            totalPrice += room.getPrice();
        }
        return totalPrice;
    }

    //Renamed from clearReservations to clearBookings match task
    public static void clearBookings() {
        for (RoomReservation reservation : reservations) {
            reservation.destroy();
        }
    }

    public static RoomReservation getReservation(int id) {
        for (RoomReservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public static List<RoomReservation> getReservations() {
        return reservations;
    }

    /**
     *
     * @return Get booking length
     */
    public Duration getDuration() {
        return Duration.between(from.atStartOfDay(), to.atStartOfDay());
    }

    public int getId() {
        return id;
    }
    public LocalDate getFrom() {
        return from;
    }
    public void setFrom(LocalDate from) {
        this.from = from;
    }
    public LocalDate getTo() {
        return to;
    }
    public void setTo(LocalDate to) {
        this.to = to;
    }
    public List<Guest> getGuests() {
        return guests;
    }
    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
    public BookingType getType() {
        return type;
    }
    public void setType(BookingType type) {
        this.type = type;
    }

    public String toString() {
        return from + " - " + to + " (" + guests + ")" + " (" + this.type + ")";
    }

    //semi-GPT
    public String getDescription() {
        Room room = Room.getRoomFromId(roomId);
        if (room == null) {
            return "Pokoj s ID " + roomId + " nebyl nalezen.";
        }
        Guest firstGuest = guests.isEmpty() ? null : guests.getFirst();
        if (firstGuest == null) {
            return "Žádní hosté k rezervaci.";
        }
        String birthDateFormatted = firstGuest.getBirthDate() != null ?
                firstGuest.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "Neznámé datum narození";
        String seaView = room.hasSeaView() ? "ano" : "ne";
        return String.format("%s až %s: %s (%s)[%d, %s] za %d Kč",
                getFrom(), getTo(),
                firstGuest.getFullName(), birthDateFormatted,
                getGuests().size(), seaView,
                getTotalPrice());
    }
}