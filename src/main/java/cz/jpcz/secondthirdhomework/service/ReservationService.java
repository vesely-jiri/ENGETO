package cz.jpcz.secondthirdhomework.service;

import cz.jpcz.secondthirdhomework.application.HotelManager;
import cz.jpcz.secondthirdhomework.exception.AlreadyReservedException;
import cz.jpcz.secondthirdhomework.exception.NoCapacityException;
import cz.jpcz.secondthirdhomework.model.Guest;
import cz.jpcz.secondthirdhomework.model.builder.RoomReservationRequest;
import cz.jpcz.secondthirdhomework.model.room.Room;
import cz.jpcz.secondthirdhomework.model.room.RoomReservation;
import cz.jpcz.secondthirdhomework.util.DebugManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for handling reservations
 */
public class ReservationService {

    private final String ALREADY_RESERVED = "Room with id: %d is already reserved.";

    private final RoomService roomService;
    Map<Integer, List<RoomReservation>> reservations = new HashMap<>();
    private int lastReservationId = 0;

    public ReservationService(RoomService roomService) {
        this.roomService = roomService;
    }

    public void reserveRoom(RoomReservationRequest request, Room room) {
        int roomId = room.getRoomId();
        if (request.getGuests().size() > room.getCapacity()) {
            throw new NoCapacityException("Room with id: " + roomId + " does not have enough capacity.");
        }

        List<RoomReservation> roomReservations = reservations.computeIfAbsent(roomId, k -> new ArrayList<>());

        for (RoomReservation res : roomReservations) {
            if (datesOverlap(res.getFrom(), res.getTo(), request.getFrom(), request.getTo())) {
                throw new AlreadyReservedException("Room with id: " + roomId + " is already reserved.");
            }
        }
        RoomReservation newReservation = new RoomReservation(++lastReservationId, request);
        roomReservations.add(newReservation);
    }
    private boolean datesOverlap(LocalDate from1, LocalDate to1, LocalDate from2, LocalDate to2) {
        return from1.isBefore(to2) && to1.isAfter(from2);
    }

    public int getTotalPrice(RoomReservation reservation) {
        int totalPrice = 0;
        int roomPrice = roomService.getRoom(reservation.getRoomId()).getPrice();
        for (int i = 0; i < reservation.getDuration().toDays(); i++) {
            totalPrice += roomPrice;
        }
        return totalPrice;
    }
    public RoomReservation getReservation(int id) {
        for (RoomReservation reservation : getReservations()) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }
    public List<RoomReservation> getReservations() {
        List<RoomReservation> allReservations = new ArrayList<>();
        for (List<RoomReservation> roomResList : reservations.values()) {
            allReservations.addAll(roomResList);
        }
        return allReservations;
    }
    public List<RoomReservation> getRoomReservations(int roomId) {
        return reservations.getOrDefault(roomId, new ArrayList<>());
    }
    public String getDescription(RoomReservation reservation) {
        int roomId = reservation.getRoomId();
        List<Guest> guests = reservation.getGuests();
        Room room = roomService.getRoom(roomId);
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
                reservation.getFrom(), reservation.getTo(),
                firstGuest.getFullName(), birthDateFormatted,
                guests.size(), seaView,
                getTotalPrice(reservation));
    }
}