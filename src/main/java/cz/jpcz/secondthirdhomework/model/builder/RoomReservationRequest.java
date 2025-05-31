package cz.jpcz.secondthirdhomework.model.builder;

import cz.jpcz.secondthirdhomework.model.Guest;
import cz.jpcz.secondthirdhomework.model.room.BookingType;

import java.time.LocalDate;
import java.util.List;

public class RoomReservationRequest {
    private LocalDate from = LocalDate.now();
    private LocalDate to = LocalDate.now().plusDays(6);
    private List<Guest> guests;
    private BookingType type = BookingType.HOLIDAY;
    private int roomId;

    public RoomReservationRequest guests(List<Guest> guests) {
        this.guests = guests;
        return this;
    }
    public RoomReservationRequest guests(Guest guest) {
        this.guests = List.of(guest);
        return this;
    }
    public RoomReservationRequest from(LocalDate from) {
        this.from = from;
        return this;
    }
    public RoomReservationRequest to(LocalDate to) {
        this.to = to;
        return this;
    }
    public RoomReservationRequest type(BookingType type) {
        this.type = type;
        return this;
    }
    public RoomReservationRequest roomId(int roomId) {
        this.roomId = roomId;
        return this;
    }

    public LocalDate getFrom() {
        return from;
    }
    public LocalDate getTo() {
        return to;
    }
    public List<Guest> getGuests() {
        return guests;
    }
    public BookingType getType() {
        return type;
    }
    public int getRoomId() {
        return roomId;
    }
}