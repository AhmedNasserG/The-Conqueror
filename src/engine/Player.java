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
        if (city == null){
            return;
        }
        // NOTE: handle if units array size equals to maxToHold
        if (city.getDefendingArmy() == null) {
            city.setDefendingArmy(new Army(cityName));
        }
        if (city.getDefendingArmy().getUnits().size() == city.getDefendingArmy().getMaxToHold()) {
            return;
        }
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
        MilitaryBuilding currentBuilding = searchForMilitaryBuilding(city, buildingType);
        // NOTE: handle if the player doesn't have the building responsible for recruiting this unit
        if (currentBuilding == null) {
            return;
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
        treasury -= currentBuilding.getRecruitmentCost();
    }

    public void build(String type, String cityName) throws NotEnoughGoldException {
        City city = getCity(cityName);
        Building newBuilding;
        switch (type) {
            case "Farm":
                newBuilding = new Farm();
                break;
            case "Market":
                newBuilding = new Market();
                break;
            case "ArcheryRange":
                newBuilding = new ArcheryRange();
                break;
            case "Barracks":
                newBuilding = new Barracks();
                break;
            case "Stable":
                newBuilding = new Stable();
                break;
            default:
                return;
        }
        if (newBuilding.getCost() > treasury) {
            throw new NotEnoughGoldException();
        }
        if (newBuilding instanceof EconomicBuilding) {
            // NOTE: handle if the city have the same type of building
            if (searchForEconomicBuilding(city, newBuilding.getClass()) != null){
                return;
            }
            city.getEconomicalBuildings().add((EconomicBuilding) newBuilding);
        } else {
            // NOTE: handle if the city have the same type of building
            if (searchForMilitaryBuilding(city, newBuilding.getClass()) != null){
                return;
            }
            city.getMilitaryBuildings().add((MilitaryBuilding) newBuilding);
        }
        treasury -= newBuilding.getCost();
    }

    public void upgradeBuilding(Building b) throws
            NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {

        if(b.getUpgradeCost() > treasury) {
            throw  new NotEnoughGoldException();
        }
        treasury -= b.getUpgradeCost();
        b.upgrade();
    }

    public void initiateArmy(City city, Unit unit){
        Army attackingArmy = new Army(city.getName());
        if (unit.getParentArmy() != null){
            unit.getParentArmy().getUnits().remove(unit);
        }

        Army defendingArmy = city.getDefendingArmy();
        defendingArmy.getUnits().remove(unit);

        unit.setParentArmy(attackingArmy);
        attackingArmy.getUnits().add(unit);
        controlledArmies.add(attackingArmy);
    }

    public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException {
        if(!army.getCurrentLocation().equals(city.getName())) throw new TargetNotReachedException();
        if(controlledCities.contains(city)) throw new FriendlyCityException();

        army.setCurrentStatus(Status.BESIEGING);
        city.setUnderSiege(true);
    }


    // Helper Methods
    private City getCity(String cityName) {
        City city = null;
        for (City currentCity : controlledCities) {
            if (currentCity.getName().equals(cityName)) {
                city = currentCity;
            }
        }
        return city;
    }

    private static MilitaryBuilding searchForMilitaryBuilding(City city, Class buildingType){
        for (MilitaryBuilding Building : city.getMilitaryBuildings()) {
            if (Building.getClass().equals(buildingType)) {
                return Building;
            }
        }
        return null;
    }

    private EconomicBuilding searchForEconomicBuilding(City city, Class buildingType){
        for (EconomicBuilding Building : city.getEconomicalBuildings()) {
            if (Building.getClass().equals(buildingType)) {
                return Building;
            }
        }
        return null;
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
