package cz.jpcz.secondthirdhomework.analytics;

import cz.jpcz.secondthirdhomework.application.HotelManager;
import cz.jpcz.secondthirdhomework.model.room.BookingType;
import cz.jpcz.secondthirdhomework.model.room.Room;
import cz.jpcz.secondthirdhomework.model.room.RoomReservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAnalytics {

    private final HotelManager hotelManager;

    public ReservationAnalytics(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
    }

    /**
     * @return Average guest count per reservation
     */
    public double getAverageGuests() {
        List<Double> guestCounts = new ArrayList<>();
        double totalGuests = 0.0;
        for (Room room : hotelManager.getAllRooms()) {
            for (RoomReservation reservation : hotelManager.getAllReservations()) {
                guestCounts.add((double) reservation.getGuests().size());
                totalGuests += reservation.getGuests().size();
            }
        }
        return totalGuests / guestCounts.size();
    }

    public List<RoomReservation> getTopReservations(int limit, BookingType type) {
        int topCount = 0;
        List<RoomReservation> topReservations = new ArrayList<>();
        for (RoomReservation reservation : hotelManager.getAllReservations()) {
            if (reservation.getType() == type && topCount <= limit) {
                topCount++;
                topReservations.add(reservation);
            }
        }
        return topReservations;
    }

    public List<RoomReservation> getReservationsByGuests(int limit, ComparisonType comparison) {
        List<RoomReservation> reservations = new ArrayList<>();
        for (RoomReservation reservation : hotelManager.getAllReservations()) {
            switch (comparison) {
                case GREATER_THAN:
                    if (reservation.getGuests().size() > limit) {
                        reservations.add(reservation);
                    }
                    break;
                case LESS_THAN:
                    if (reservation.getGuests().size() < limit) {
                        reservations.add(reservation);
                    }
                    break;
                case EQUAL:
                    if (reservation.getGuests().size() == limit) {
                        reservations.add(reservation);
                    }
                    break;
            }
        }
        return reservations;
    }
}