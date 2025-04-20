package test;

import util.ConsoleColor;
import model.Guest;
import model.room.Room;
import model.room.RoomReservation;
import util.DebugManager;

import java.time.LocalDate;
import java.util.List;

public class DataTest {
    public static void run() {

        DebugManager.setDebug(true);

        DebugManager.print(ConsoleColor.GREEN + "Executing data test");

        DebugManager.print(ConsoleColor.BLUE + "Creating 2 guests:");
        Guest guest1 = new Guest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
        Guest guest2 = new Guest("Jan", "Dvořáček", LocalDate.of(1995, 5, 5));

        DebugManager.print(ConsoleColor.BLUE + "Updating birthdate of guest2: " + guest2);
        guest2.setBirthDate(LocalDate.of(1995, 4, 5));

        DebugManager.print(ConsoleColor.BLUE + "Updated birthdate of guest2: " + guest2);

        DebugManager.print(ConsoleColor.BLUE + "Creating 3 rooms:");
        Room room1 = new Room(1, 1, true, true, 1000);
        Room room2 = new Room(2, 1, true, true, 1000);
        Room room3 = new Room(3, 3, false, true, 2400);

        DebugManager.print(ConsoleColor.BLUE + "Reserving rooms with id 1 and 3:");
        room1.reserveRoom(guest1, LocalDate.of(2021, 7, 19), LocalDate.of(2021, 7, 26));
        room3.reserveRoom(List.of(guest1, guest2), LocalDate.of(2021, 9, 1), LocalDate.of(2021, 9, 14));

        DebugManager.print(ConsoleColor.BLUE + "Printing all reservations for all rooms:");
        for (Room room : Room.getRooms()) {
            DebugManager.print(ConsoleColor.CYAN + "Reservations for room: " + room.getRoomId() + ":");
            for (RoomReservation reservation : room.getReservations()) {
                DebugManager.print(ConsoleColor.PURPLE + "      Reservation: " + reservation);
            }
        }

        DebugManager.print(ConsoleColor.BLUE + "Testing reservation positive overlapping:");
        room1.reserveRoom(guest1, LocalDate.of(2021, 7, 19), LocalDate.of(2021, 7, 26));

        DebugManager.print(ConsoleColor.BLUE + "Testing reservation negative overlapping:");
        room1.reserveRoom(guest1, LocalDate.of(2021, 7, 27), LocalDate.of(2021, 7, 29));

        DebugManager.print(ConsoleColor.GREEN + "Data test finished.");
    }
}