package engine;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;

import java.util.ArrayList;

public class City {

    private String name;
    private ArrayList<EconomicBuilding> economicalBuildings;
    private ArrayList<MilitaryBuilding> militaryBuildings;
    private Army defendingArmy;
    private int turnsUnderSiege;
    private boolean underSiege;


    public City(String name) {
        this.name = name;
        this.economicalBuildings = new ArrayList<>();
        this.militaryBuildings = new ArrayList<>();
        Army cityDefendingArmy = new Army(name);
        cityDefendingArmy.setArmyName("Defending Army");
        this.defendingArmy = cityDefendingArmy;
        this.underSiege = false;
        this.turnsUnderSiege = -1;
    }


    // Getters
    public String getName() {
        return name;
    }

    public ArrayList<EconomicBuilding> getEconomicalBuildings() {
        return economicalBuildings;
    }

    public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
        return militaryBuildings;
    }

    public Army getDefendingArmy() {
        return defendingArmy;
    }

    public int getTurnsUnderSiege() {
        return turnsUnderSiege;
    }

    public boolean isUnderSiege() {
        return underSiege;
    }


    // Setters
    public void setDefendingArmy(Army defendingArmy) {
        this.defendingArmy = defendingArmy;
    }

    public void setTurnsUnderSiege(int turnsUnderSiege) {
        this.turnsUnderSiege = turnsUnderSiege;
    }

    public void setUnderSiege(boolean underSiege) {
        this.underSiege = underSiege;
    }

    @Override
    public String toString() {
        return name;
    }
}
