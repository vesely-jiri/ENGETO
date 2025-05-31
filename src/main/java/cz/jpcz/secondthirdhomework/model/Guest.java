package cz.jpcz.secondthirdhomework.model;

import cz.jpcz.secondthirdhomework.util.ConsoleColor;
import cz.jpcz.secondthirdhomework.util.DebugManager;

import java.time.LocalDate;

/**
 * Represents a guest with personal information such as first name, last name, and birthdate
 */
public class Guest {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Guest(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        DebugManager.print(ConsoleColor.BLUE + "Created guest: " + this);
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