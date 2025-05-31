package cz.jpcz.secondthirdhomework.test;

import cz.jpcz.secondthirdhomework.application.HotelManager;
import cz.jpcz.secondthirdhomework.exception.AlreadyReservedException;
import cz.jpcz.secondthirdhomework.model.room.BookingType;
import cz.jpcz.secondthirdhomework.model.builder.RoomReservationRequest;
import cz.jpcz.secondthirdhomework.model.room.RoomReservation;
import cz.jpcz.secondthirdhomework.util.ConsoleColor;
import cz.jpcz.secondthirdhomework.model.Guest;
import cz.jpcz.secondthirdhomework.model.room.Room;
import cz.jpcz.secondthirdhomework.util.DebugManager;

import java.time.LocalDate;
import java.util.List;

public class DataTest1 {
    public static void run() {
        DebugManager.setDebug(true);
        HotelManager hotelManager = new HotelManager();

        DebugManager.print(ConsoleColor.GREEN + "Executing data test from: Lekce 2 - Hotel");

        DebugManager.print(ConsoleColor.YELLOW + "Creating 2 guests:");
        Guest guest1 = new Guest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
        Guest guest2 = new Guest("Jan", "Dvořáček", LocalDate.of(1995, 5, 5));

        DebugManager.print(ConsoleColor.YELLOW + "Updating birthdate of guest2: " + guest2);
        guest2.setBirthDate(LocalDate.of(1995, 4, 5));

        DebugManager.print(ConsoleColor.BLUE + "Updated birthdate of guest2: " + guest2.getBirthDate());

        DebugManager.print(ConsoleColor.YELLOW + "Creating 3 rooms:");
        hotelManager.addRoom(new Room(1, 1, true, true, 1000));
        hotelManager.addRoom(new Room(2, 1, true, true, 1000));
        hotelManager.addRoom(new Room(3, 3, false, true, 2400));

        DebugManager.print(ConsoleColor.YELLOW + "Reserving rooms with id 1 and 3:");

        RoomReservationRequest request = new RoomReservationRequest()
                .from(LocalDate.of(2021, 9, 14))
                .to(LocalDate.of(2021, 9, 1))
                .guests(guest1)
                .type(BookingType.WORKING);
        hotelManager.reserveRoom(request, hotelManager.getRoom(1));

        RoomReservationRequest request2 = new RoomReservationRequest()
                .from(LocalDate.of(2021, 9, 5))
                .to(LocalDate.of(2021, 9, 11))
                .guests(List.of(guest1, guest2))
                .type(BookingType.WORKING);
        hotelManager.reserveRoom(request2, hotelManager.getRoom(3));

        DebugManager.print(ConsoleColor.YELLOW + "Printing all reservations for all rooms:");
        for (Room room : hotelManager.getAllRooms()) {
            DebugManager.print(ConsoleColor.BLUE + "Reservations for room: " + room.getRoomId() + ":");
            for (RoomReservation reservation : hotelManager.getAllReservations()) {
                DebugManager.print(ConsoleColor.PURPLE + "      Reservation: " + reservation);
            }
        }

        DebugManager.print(ConsoleColor.YELLOW + "Testing reservation positive overlapping:");
        RoomReservationRequest request3 = new RoomReservationRequest()
                .from(LocalDate.of(2021,9,2))
                .to(LocalDate.of(2021,9,7))
                .guests(guest1)
                .type(BookingType.WORKING);
        try {
            hotelManager.reserveRoom(request3,hotelManager.getRoom(3));
        } catch (AlreadyReservedException e) {
            DebugManager.print(ConsoleColor.BLUE + "Successful catch AlreadyReservedException");
        }

        DebugManager.print(ConsoleColor.YELLOW + "Testing reservation negative overlapping with different room:");
        RoomReservationRequest request4 = new RoomReservationRequest()
                .from(LocalDate.of(2021,9,12))
                .to(LocalDate.of(2021,9,15))
                .guests(guest1)
                .type(BookingType.WORKING);
        hotelManager.reserveRoom(request4,hotelManager.getRoom(1));

        DebugManager.print(ConsoleColor.YELLOW + "Testing reservation negative overlapping with different room:");
        RoomReservationRequest request5 = new RoomReservationRequest()
                .from(LocalDate.of(2021,9,12))
                .to(LocalDate.of(2021,9,15))
                .guests(guest1)
                .type(BookingType.WORKING);
        hotelManager.reserveRoom(request5,hotelManager.getRoom(3));

        DebugManager.print(ConsoleColor.GREEN + "Data test finished.");
    }
}