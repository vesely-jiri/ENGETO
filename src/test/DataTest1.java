package test;

import model.room.BookingType;
import model.room.RoomReservationRequest;
import util.ConsoleColor;
import model.Guest;
import model.room.Room;
import model.room.RoomReservation;
import util.DebugManager;

import java.time.LocalDate;
import java.util.List;

public class DataTest1 {
    public static void run() {
        DebugManager.setDebug(true);

        DebugManager.print(ConsoleColor.GREEN + "Executing data test from: Lekce 2 - Hotel");

        DebugManager.print(ConsoleColor.YELLOW + "Creating 2 guests:");
        Guest guest1 = new Guest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
        Guest guest2 = new Guest("Jan", "Dvořáček", LocalDate.of(1995, 5, 5));

        DebugManager.print(ConsoleColor.YELLOW + "Updating birthdate of guest2: " + guest2);
        guest2.setBirthDate(LocalDate.of(1995, 4, 5));

        DebugManager.print(ConsoleColor.YELLOW + "Updated birthdate of guest2: " + guest2 + "to" + guest2.getBirthDate());

        DebugManager.print(ConsoleColor.YELLOW + "Creating 3 rooms:");
        Room room1 = new Room(1, 1, true, true, 1000);
        Room room2 = new Room(2, 1, true, true, 1000);
        Room room3 = new Room(3, 3, false, true, 2400);

        DebugManager.print(ConsoleColor.YELLOW + "Reserving rooms with id 1 and 3:");

        RoomReservationRequest request = new RoomReservationRequest()
                .from(LocalDate.of(2021, 9, 14))
                .to(LocalDate.of(2021, 9, 1))
                .guests(guest1)
                .type(BookingType.WORKING);
        room1.reserveRoom(request);

        RoomReservationRequest request2 = new RoomReservationRequest()
                .from(LocalDate.of(2021, 9, 5))
                .to(LocalDate.of(2021, 9, 11))
                .guests(List.of(guest1, guest2))
                .type(BookingType.WORKING);
        room3.reserveRoom(request2);

        DebugManager.print(ConsoleColor.YELLOW + "Printing all reservations for all rooms:");
        for (Room room : Room.getRooms()) {
            DebugManager.print(ConsoleColor.BLUE + "Reservations for room: " + room.getRoomId() + ":");
            for (RoomReservation reservation : room.getReservations()) {
                DebugManager.print(ConsoleColor.PURPLE + "      Reservation: " + reservation);
            }
        }

        DebugManager.print(ConsoleColor.YELLOW + "Testing reservation positive overlapping:");
        RoomReservationRequest request3 = new RoomReservationRequest()
                .from(LocalDate.of(2021,9,2))
                .to(LocalDate.of(2021,9,7))
                .guests(guest1)
                .type(BookingType.WORKING);
        room3.reserveRoom(request3);

        DebugManager.print(ConsoleColor.YELLOW + "Testing reservation negative overlapping with same room:");
        RoomReservationRequest request4 = new RoomReservationRequest()
                .from(LocalDate.of(2021,9,12))
                .to(LocalDate.of(2021,9,15))
                .guests(guest1)
                .type(BookingType.WORKING);
        room1.reserveRoom(request4);

        DebugManager.print(ConsoleColor.YELLOW + "Testing reservation negative overlapping with same room:");
        RoomReservationRequest request5 = new RoomReservationRequest()
                .from(LocalDate.of(2021,9,12))
                .to(LocalDate.of(2021,9,15))
                .guests(guest1)
                .type(BookingType.WORKING);
        room3.reserveRoom(request4);

        DebugManager.print(ConsoleColor.GREEN + "Data test finished.");
    }
}