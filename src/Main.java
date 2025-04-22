import util.ConsoleColor;
import util.DebugManager;

import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        System.out.println("Executing program in second homework...");
        Seller seller1 = new Seller("Honza", LocalDate.of(2005, 1, 5), 21, 61.1, true);
        Seller seller2 = new Seller("Martin", LocalDate.of(2002, 1, 11), 7, 53.2, false);

        DebugManager.setDebug(true);
        DebugManager.print(ConsoleColor.BLUE + seller1.getName());
        DebugManager.print(ConsoleColor.BLUE + seller2.getName());
    }
}