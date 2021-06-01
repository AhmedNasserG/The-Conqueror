package engine;

import buildings.*;
import exceptions.*;
import units.*;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<City> controlledCities;
    private ArrayList<Army> controlledArmies;
    private double treasury;
    private double food;

    public Player(String name) {
        this.name = name;
        this.controlledCities = new ArrayList<>();
        this.controlledArmies = new ArrayList<>();
    }

    public void recruitUnit(String type, String cityName) throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {
        City city = getCity(cityName);
        Class buildingType;
        Unit recruitedUnit;
        switch (type) {
            case "Archer":
                buildingType = ArcheryRange.class;
                recruitedUnit = new Archer(1);
                break;
            case "Infantry":
                buildingType = Barracks.class;
                recruitedUnit = new Infantry(1);
                break;
            case "Cavalry":
                buildingType = Stable.class;
                recruitedUnit = new Cavalry(1);
                break;
            default:
                return;
        }
        MilitaryBuilding currentBuilding = null;
        for (MilitaryBuilding Building : city.getMilitaryBuildings()) {
            if (Building.getClass().equals(buildingType)) {
                currentBuilding = Building;
            }
        }
        if (currentBuilding.isCoolDown()) {
            throw new BuildingInCoolDownException();
        }
        if (currentBuilding.getCurrentRecruit() == currentBuilding.getMaxRecruit()) {
            throw new MaxRecruitedException();
        }
        if (currentBuilding.getRecruitmentCost() > treasury) {
            throw new NotEnoughGoldException();
        }
        recruitedUnit.setParentArmy(city.getDefendingArmy());
        city.getDefendingArmy().getUnits().add(recruitedUnit);
        currentBuilding.setCurrentRecruit(currentBuilding.getCurrentRecruit() + 1);
        currentBuilding.setCoolDown(true);
        treasury -= currentBuilding.getRecruitmentCost();
        // TODO: Should I handle if units array size equals to maxToHold
    }


    private City getCity(String cityName) {
        City city = null;
        for (City currentCity : controlledCities) {
            if (currentCity.getName().equals(cityName)) {
                city = currentCity;
            }
        }
        return city;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ArrayList<City> getControlledCities() {
        return controlledCities;
    }

    public ArrayList<Army> getControlledArmies() {
        return controlledArmies;
    }

    public double getTreasury() {
        return treasury;
    }

    public double getFood() {
        return food;
    }


    // Setters
    public void setTreasury(double treasury) {
        this.treasury = treasury;
    }

    public void setFood(double food) {
        this.food = food;
    }
}
