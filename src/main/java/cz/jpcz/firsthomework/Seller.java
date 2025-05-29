package cz.jpcz.firsthomework;

import cz.jpcz.firsthomework.util.ConsoleColor;
import cz.jpcz.firsthomework.util.DebugManager;

import java.time.LocalDate;

public class Seller {
    private String name;
    private LocalDate birthDate;
    private Integer contracts;
    private Double soldCarrotsInTons;
    private boolean senior;

    Seller(String name, LocalDate birthDate, Integer contracts, Double soldCarrotsInTons, boolean senior){
        this.name = name;
        this.birthDate = birthDate;
        this.contracts = contracts;
        this.soldCarrotsInTons = soldCarrotsInTons;
        this.senior = senior;
        DebugManager.print(ConsoleColor.BLUE + "Created seller: " + this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getContracts() {
        return contracts;
    }

    public void setContracts(Integer contracts) {
        this.contracts = contracts;
    }

    public Double getSoldCarrotsInTons() {
        return soldCarrotsInTons;
    }

    public void setSoldCarrotsInTons(Double soldCarrotsInTons) {
        this.soldCarrotsInTons = soldCarrotsInTons;
    }

    public boolean isSenior() {
        return senior;
    }

    public void setSenior(boolean senior) {
        this.senior = senior;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", contracts=" + contracts +
                ", soldCarrotsInTons=" + soldCarrotsInTons +
                ", senior=" + senior +
                '}';
    }
}