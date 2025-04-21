package model;

import util.ConsoleColor;
import util.DebugManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a guest with personal information such as first name, last name, and birthdate
 */
public class Guest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    private static List<Guest> guests = new ArrayList<>();

    public Guest(String firstName, String lastName, LocalDate birthDate) {
        DebugManager.print(ConsoleColor.BLUE + "Creating guest: " + firstName + " " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        guests.add(this);
    }

    public void destroy() {
        guests.remove(this);
    }

    public static List<Guest> getGuests() {
        return guests;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + birthDate + ")";
    }
}