package model.room;

import model.Guest;
import util.ConsoleColor;
import util.DebugManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a room with its attributes in hotel
 */
public class Room {

    private final String ALREADY_RESERVED = "Room with id: %d is already reserved.";
    private final String NOT_ENOUGH_CAPACITY = "Room with id: %d does not have enough capacity.";
    private int roomId;
    private int capacity;
    private boolean hasBalcony;
    private boolean hasSeaView;
    private final int price;
    private List<RoomReservation> reservations = new ArrayList<>();
    private static List<Room> rooms = new ArrayList<>();

    public Room(int roomId, int capacity, boolean hasBalcony, boolean hasSeaView, int price) {
        DebugManager.print(ConsoleColor.BLUE + "Creating room with id: " + roomId);
        this.roomId = roomId;
        this.capacity = capacity;
        this.hasBalcony = hasBalcony;
        this.hasSeaView = hasSeaView;
        this.price = price;
        rooms.add(this);
    }

    public void destroy() {
        rooms.remove(this);
    }

    public static List<Room> getRooms() {
        return rooms;
    }

    public List<RoomReservation > getReservations() {
        return reservations;
    }

    public RoomReservation getReservation(int id) {
        for (RoomReservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public void reserveRoom(RoomReservationRequest reservation) {
        reserveRoom(reservation.getGuests(), reservation.getFrom(), reservation.getTo(), reservation.getType());
    }

    public void reserveRoom(List<Guest> guests, LocalDate from, LocalDate to, BookingType type) {
        if (guests.size() > capacity) {
            DebugManager.printError(NOT_ENOUGH_CAPACITY.replace("%d", String.valueOf(roomId)));
            return;
        }
        for (RoomReservation reservation : reservations) {
            if (datesOverlap(from, to, reservation.getFrom(), reservation.getTo())) {
                DebugManager.printError(ALREADY_RESERVED.replace("%d", String.valueOf(roomId)));
                return;
            }
        }
        reservations.add(new RoomReservation(from, to, guests, type, this.getRoomId()));
    }



    private boolean datesOverlap(LocalDate from1, LocalDate to1, LocalDate from2, LocalDate to2) {
        return from1.isBefore(to2) && to1.isAfter(from2);
    }

    public static Room getRoomFromId(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public boolean hasBalcony() {
        return hasBalcony;
    }
    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }
    public boolean hasSeaView() {
        return hasSeaView;
    }
    public void setHasSeaView(boolean hasSeaView) {
        this.hasSeaView = hasSeaView;
    }
    public int getPrice() {
        return price;
    }

    public String toString() {
        return "Room [id=" + roomId + ", capacity=" + capacity + ", hasBalcony=" + hasBalcony + ", hasSeaView=" +
                hasSeaView + ", price=" + price + "]";
    }
}