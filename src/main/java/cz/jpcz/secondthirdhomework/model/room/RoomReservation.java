package cz.jpcz.secondthirdhomework.model.room;

import cz.jpcz.secondthirdhomework.model.Guest;
import cz.jpcz.secondthirdhomework.model.builder.RoomReservationRequest;
import cz.jpcz.secondthirdhomework.util.ConsoleColor;
import cz.jpcz.secondthirdhomework.util.DebugManager;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class RoomReservation {
    private int id;
    private int roomId;
    private LocalDate from, to;
    private List<Guest> guests;
    private BookingType type;

    public RoomReservation(int id, LocalDate from, LocalDate to, List<Guest> guests, BookingType type, int roomId) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.guests = guests;
        this.type = type;
        this.roomId = roomId;
        DebugManager.print(ConsoleColor.BLUE + "Created room reservation: " + this);
    }
    public RoomReservation(int id, RoomReservationRequest request) {
        this(id, request.getFrom(), request.getTo(), request.getGuests(), request.getType(), request.getRoomId());
    }

    public Duration getDuration() {
        return Duration.between(from.atStartOfDay(), to.atStartOfDay());
    }

    public int getId() {
        return id;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
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

    @Override
    public String toString() {
        return "RoomReservation{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", from=" + from +
                ", to=" + to +
                ", guests=" + guests +
                ", type=" + type +
                '}';
    }
}
