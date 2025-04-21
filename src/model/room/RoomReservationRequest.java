package model.room;

import model.Guest;

import java.time.LocalDate;
import java.util.List;

public class RoomReservationRequest {
    private LocalDate from = LocalDate.now();
    private LocalDate to = LocalDate.now().plusDays(6);
    private List<Guest> guests;
    private BookingType type = BookingType.HOLIDAY;

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
}