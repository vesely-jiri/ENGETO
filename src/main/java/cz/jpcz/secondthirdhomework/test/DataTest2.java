package cz.jpcz.secondthirdhomework.test;

import cz.jpcz.secondthirdhomework.application.HotelManager;
import cz.jpcz.secondthirdhomework.model.Guest;
import cz.jpcz.secondthirdhomework.analytics.ReservationAnalytics;
import cz.jpcz.secondthirdhomework.model.room.Room;
import cz.jpcz.secondthirdhomework.model.builder.RoomReservationRequest;
import cz.jpcz.secondthirdhomework.util.ConsoleColor;
import cz.jpcz.secondthirdhomework.util.DebugManager;
import cz.jpcz.secondthirdhomework.model.room.BookingType;
import cz.jpcz.secondthirdhomework.analytics.ComparisonType;

import java.time.LocalDate;
import java.util.List;

/**
 * Data test for Lekce 3 - Hotel - evidence rezervací
 */
public class DataTest2 {
    public static void run() {
        DebugManager.setDebug(true);

        HotelManager hotelManager = new HotelManager();
        ReservationAnalytics reservationAnalytics = new ReservationAnalytics(hotelManager);

        DebugManager.print(ConsoleColor.GREEN + "Executing data test from: Lekce 3 - Hotel - evidence rezervací");

        DebugManager.print(ConsoleColor.YELLOW + "Creating 5 guests:");
        Guest guest1 = new Guest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
        Guest guest2 = new Guest("Jan", "Dvořáček", LocalDate.of(1995, 5, 5));
        Guest guest3 = new Guest("Petr", "Dvořáček", LocalDate.of(2004, 9, 14));
        Guest guest4 = new Guest("Martin", "Hofmann", LocalDate.of(1999, 12, 1));
        Guest guest5 = new Guest("Jarda", "Turek", LocalDate.of(2001, 3, 7));

        DebugManager.print(ConsoleColor.YELLOW + "Creating 4 rooms:");
        hotelManager.addRoom(new Room(1, 1, true, true, 1000));
        hotelManager.addRoom(new Room(2, 1, true, true, 1000));
        hotelManager.addRoom(new Room(3, 3, false, true, 2400));
        hotelManager.addRoom(new Room(4, 3, false, true, 2400));

        DebugManager.print(ConsoleColor.YELLOW + "Reserving rooms with id 1, 3 and 4:");

        RoomReservationRequest request1 = new RoomReservationRequest()
                .from(LocalDate.of(2021, 9, 1))
                .to(LocalDate.of(2021, 9, 14))
                .guests(guest1)
                .type(BookingType.HOLIDAY);
        hotelManager.reserveRoom(request1, hotelManager.getRoom(1));

        RoomReservationRequest request2 = new RoomReservationRequest()
                .from(LocalDate.of(2021, 9, 1))
                .to(LocalDate.of(2021, 9, 14))
                .guests(List.of(guest3, guest4, guest1))
                .type(BookingType.WORKING);
        hotelManager.reserveRoom(request2, hotelManager.getRoom(3));

        RoomReservationRequest request3 = new RoomReservationRequest()
                .from(LocalDate.of(2022, 3, 8))
                .to(LocalDate.of(2022, 3, 14))
                .guests(List.of(guest5,guest1, guest2))
                .type(BookingType.HOLIDAY);
        hotelManager.reserveRoom(request3, hotelManager.getRoom(4));

        DebugManager.print(ConsoleColor.YELLOW + "Average guest count: " + ConsoleColor.BLUE + reservationAnalytics.getAverageGuests());

        DebugManager.print(ConsoleColor.YELLOW + "Finding first 8 recreational(holiday) reservations(by id):");
        reservationAnalytics.getTopReservations(8, BookingType.HOLIDAY).forEach(res ->
                        DebugManager.print(ConsoleColor.CYAN + "    " + res));

        DebugManager.print(ConsoleColor.YELLOW + "Finding reservations with 1 guest");
        reservationAnalytics.getReservationsByGuests(1, ComparisonType.EQUAL)
                .forEach(res -> DebugManager.print(ConsoleColor.CYAN + "    " + res));

        DebugManager.print(ConsoleColor.YELLOW + "Finding reservations with 2 guests");
        reservationAnalytics.getReservationsByGuests(2, ComparisonType.EQUAL)
                .forEach(res -> DebugManager.print(ConsoleColor.CYAN + "    " + res));

        DebugManager.print(ConsoleColor.YELLOW + "Finding reservations with 3 guests or more");
        reservationAnalytics.getReservationsByGuests(2, ComparisonType.GREATER_THAN)
                .forEach(res -> DebugManager.print(ConsoleColor.CYAN + "    " + res));

        DebugManager.print(ConsoleColor.YELLOW + "Printing all reservations in defined formatting");
        hotelManager.getAllReservations()
                .forEach(res -> DebugManager.print(ConsoleColor.CYAN + "    "
                        + hotelManager.getReservationService().getDescription(res)));

        DebugManager.print(ConsoleColor.GREEN + "Data test finished");
    }
}