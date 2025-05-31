package cz.jpcz.secondthirdhomework.model.room;

import cz.jpcz.secondthirdhomework.util.ConsoleColor;
import cz.jpcz.secondthirdhomework.util.DebugManager;


/**
 * Model class representing a room in a hotel
 */
public class Room {

    private final int roomId;
    private final int capacity;
    private final boolean hasBalcony;
    private final boolean hasSeaView;
    private final int price;

    public Room(int roomId, int capacity, boolean hasBalcony, boolean hasSeaView, int price) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.hasBalcony = hasBalcony;
        this.hasSeaView = hasSeaView;
        this.price = price;
        DebugManager.print(ConsoleColor.BLUE + "Created room: " + this);
    }

    public int getRoomId() {
        return roomId;
    }
    public int getCapacity() {
        return capacity;
    }
    public boolean hasBalcony() {
        return hasBalcony;
    }
    public boolean hasSeaView() {
        return hasSeaView;
    }
    public int getPrice() {
        return price;
    }

    public String toString() {
        return "Room [id=" + roomId + ", capacity=" + capacity + ", hasBalcony=" + hasBalcony + ", hasSeaView=" +
                hasSeaView + ", price=" + price + "]";
    }
}