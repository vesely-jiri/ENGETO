package cz.jpcz.secondthirdhomework.application;

import cz.jpcz.secondthirdhomework.model.builder.RoomReservationRequest;
import cz.jpcz.secondthirdhomework.model.room.Room;
import cz.jpcz.secondthirdhomework.model.room.RoomReservation;
import cz.jpcz.secondthirdhomework.service.ReservationService;
import cz.jpcz.secondthirdhomework.service.RoomService;

import java.time.LocalDate;
import java.util.List;

public class HotelManager {
    RoomService roomService = new RoomService();
    ReservationService reservationService = new ReservationService(roomService);

    public void addRoom(Room room) {
        roomService.add(room);
    }
    public void removeRoom(Room room) {
        roomService.remove(room);
    }
    public void reserveRoom(RoomReservationRequest request, Room room) {
        int roomId = room.getRoomId();
        request.roomId(roomId);
        reservationService.reserveRoom(request, room);
    }

    public RoomService getRoomService() {
        return roomService;
    }
    public ReservationService getReservationService() {
        return reservationService;
    }
    public Room getRoom(int roomId) {
        return roomService.getRooms().stream()
                .filter(r -> r.getRoomId() == roomId)
                .findFirst()
                .orElse(null);
    }
    public List<Room> getAllRooms() {
        return roomService.getRooms();
    }
    public List<Room> getAvailableRooms(LocalDate from, LocalDate to, int minCapacity) {
        return roomService.getRooms().stream()
                .filter(room -> room.getCapacity() >= minCapacity)
                .toList();
    }
    public List<RoomReservation> getAllReservations() {
        return reservationService.getReservations();
    }
}