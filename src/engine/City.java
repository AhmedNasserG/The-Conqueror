package engine;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;

import java.util.ArrayList;

public class City{
    private String name;
    private ArrayList<EconomicBuilding> economicBuildings;
    private ArrayList<MilitaryBuilding> militaryBuildings;
    private Army defendingArmy;
    private int turnsUnderSiege;
    private boolean underSiege;


    public City(String name){
        this.name = name;
        defendingArmy = new Army();
        underSiege = false;
    }


    public String getName() {
        return name;
    }

    public ArrayList<EconomicBuilding> getEconomicBuildings() {
        return economicBuildings;
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


    public void setDefendingArmy(Army defendingArmy) {
        this.defendingArmy = defendingArmy;
    }

    public void setTurnsUnderSiege(int turnsUnderSiege) {
        this.turnsUnderSiege = turnsUnderSiege;
    }

    public void setUnderSiege(boolean underSiege) {
        this.underSiege = underSiege;
    }
}
