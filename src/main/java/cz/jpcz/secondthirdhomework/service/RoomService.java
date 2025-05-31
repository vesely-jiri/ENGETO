package cz.jpcz.secondthirdhomework.service;

import cz.jpcz.secondthirdhomework.model.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private final List<Room> rooms = new ArrayList<>();

    public void add(Room room) {
        rooms.add(room);
    }
    public void remove(Room room) {
        rooms.remove(room);
    }

    public Room getRoom(int roomId) {
        return rooms.stream()
                .filter(r -> r.getRoomId() == roomId)
                .findFirst()
                .orElse(null);
    }
    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }
    public List<Room> getAvailableRooms(LocalDate from, LocalDate to, int minCapacity) {
        return rooms.stream()
                .filter(room -> room.getCapacity() >= minCapacity)
                .toList();
    }
}