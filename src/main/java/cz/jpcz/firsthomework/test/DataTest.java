package cz.jpcz.firsthomework.test;

import cz.jpcz.firsthomework.model.Seller;
import cz.jpcz.firsthomework.util.ConsoleColor;
import cz.jpcz.firsthomework.util.DebugManager;

import java.time.LocalDate;

public class DataTest {
    public static void run() {
        DebugManager.setDebug(true);

        DebugManager.print(ConsoleColor.YELLOW + "Creating 2 users:");
        Seller seller1 = new Seller("Jan", LocalDate.of(2005, 1, 5), 21, 61.1, true);
        Seller seller2 = new Seller("Martin", LocalDate.of(2002, 1, 11), 7, 53.2, false);

        DebugManager.print(ConsoleColor.YELLOW + "Getting names of users:");
        DebugManager.print(ConsoleColor.BLUE + seller1.getName());
        DebugManager.print(ConsoleColor.BLUE + seller2.getName());
    }
}