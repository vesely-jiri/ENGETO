package test;

import model.Guest;
import model.analytics.ReservationAnalytics;
import model.room.Room;
import model.room.RoomReservation;
import model.room.RoomReservationRequest;
import util.ConsoleColor;
import util.DebugManager;
import model.room.BookingType;
import model.analytics.ComparisonType;

import java.time.LocalDate;
import java.util.List;

public class DataTest2 {
    public static void run() {
        DebugManager.setDebug(true);

        DebugManager.print(ConsoleColor.GREEN + "Executing data test from: Lekce 3 - Hotel - evidence rezervací");

        DebugManager.print(ConsoleColor.YELLOW + "Creating 5 guests:");
        Guest guest1 = new Guest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
        Guest guest2 = new Guest("Jan", "Dvořáček", LocalDate.of(1995, 5, 5));
        Guest guest3 = new Guest("Petr", "Dvořáček", LocalDate.of(2004, 9, 14));
        Guest guest4 = new Guest("Martin", "Hofmann", LocalDate.of(1999, 12, 1));
        Guest guest5 = new Guest("Jarda", "Turek", LocalDate.of(2001, 3, 7));

        DebugManager.print(ConsoleColor.YELLOW + "Creating 4 rooms:");
        Room room1 = new Room(1, 1, true, true, 1000);
        Room room2 = new Room(2, 1, true, true, 1000);
        Room room3 = new Room(3, 3, false, true, 2400);
        Room room4 = new Room(4, 3, false, true, 2400);

        DebugManager.print(ConsoleColor.YELLOW + "Reserving rooms with id 1, 3 and 4:");

        RoomReservationRequest request1 = new RoomReservationRequest()
                .from(LocalDate.of(2021, 9, 1))
                .to(LocalDate.of(2021, 9, 14))
                .guests(guest1)
                .type(BookingType.HOLIDAY);
        room1.reserveRoom(request1);

        RoomReservationRequest request2 = new RoomReservationRequest()
                .from(LocalDate.of(2021, 9, 1))
                .to(LocalDate.of(2021, 9, 14))
                .guests(List.of(guest3, guest4, guest1))
                .type(BookingType.WORKING);
        room3.reserveRoom(request2);

        RoomReservationRequest request3 = new RoomReservationRequest()
                .from(LocalDate.of(2022, 3, 8))
                .to(LocalDate.of(2022, 3, 14))
                .guests(List.of(guest5,guest1, guest2))
                .type(BookingType.HOLIDAY);
        room4.reserveRoom(request3);

        System.out.println(RoomReservation.getReservations().size());

        DebugManager.print(ConsoleColor.YELLOW + "Average guest count: " + ConsoleColor.BLUE + ReservationAnalytics.getAverageGuests());

        DebugManager.print(ConsoleColor.YELLOW + "Finding first 8 recreational(holiday) reservations(by id):");
        ReservationAnalytics.getTopReservations(8, BookingType.HOLIDAY).forEach(res ->
                        DebugManager.print(ConsoleColor.CYAN + "    " + res));

        DebugManager.print(ConsoleColor.YELLOW + "Finding reservations with 1 guest");
        ReservationAnalytics.getReservationsByGuests(1, ComparisonType.EQUAL)
                .forEach(res -> DebugManager.print(ConsoleColor.CYAN + "    " + res));

        DebugManager.print(ConsoleColor.YELLOW + "Finding reservations with 2 guests");
        ReservationAnalytics.getReservationsByGuests(2, ComparisonType.EQUAL)
                .forEach(res -> DebugManager.print(ConsoleColor.CYAN + "    " + res));

        DebugManager.print(ConsoleColor.YELLOW + "Finding reservations with 3 guests or more");
        ReservationAnalytics.getReservationsByGuests(2, ComparisonType.GREATER_THAN)
                .forEach(res -> DebugManager.print(ConsoleColor.CYAN + "    " + res));

        DebugManager.print(ConsoleColor.YELLOW + "Printing all reservations in defined formatting");
        RoomReservation.getReservations()
                .forEach(res -> DebugManager.print(ConsoleColor.CYAN + "    " + res.getDescription()));


        DebugManager.print(ConsoleColor.GREEN + "Data test finished");
    }
}