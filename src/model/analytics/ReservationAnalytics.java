package model.analytics;

import model.room.BookingType;
import model.room.Room;
import model.room.RoomReservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAnalytics {
    /**
     * @return Average guest count per reservation
     */
    public static double getAverageGuests() {
        List<Double> guestCounts = new ArrayList<>();
        double totalGuests = 0.0;
        for (Room room : Room.getRooms()) {
            for (RoomReservation reservation : room.getReservations()) {
                guestCounts.add((double) reservation.getGuests().size());
                totalGuests += reservation.getGuests().size();
            }
        }
        return totalGuests / guestCounts.size();
    }

    public static List<RoomReservation> getTopReservations(int limit, BookingType type) {
        int topCount = 0;
        List<RoomReservation> topReservations = new ArrayList<>();
        for (RoomReservation reservation : RoomReservation.getReservations()) {
            if (reservation.getType() == type && topCount <= limit) {
                topCount++;
                topReservations.add(reservation);
            }
        }
        return topReservations;
    }

    public static List<RoomReservation> getReservationsByGuests(int limit, ComparisonType comparison) {
        List<RoomReservation> reservations = new ArrayList<>();
        for (RoomReservation reservation : RoomReservation.getReservations()) {
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