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
        // NOTE: handle if units array size equals to maxToHold
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
        MilitaryBuilding currentBuilding = null;
        for (MilitaryBuilding Building : city.getMilitaryBuildings()) {
            if (Building.getClass().equals(buildingType)) {
                currentBuilding = Building;
                break;
            }
        }
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
        City givenCity = null;
        for(City city: controlledCities){
            if(city.getName().equals(cityName)) givenCity = city;
        }

        Building newBuilding = null;
        switch(type){
            case "ArcheryRange" : {
                newBuilding = new ArcheryRange();
                addToMilBuildings(newBuilding, givenCity);
                break;
            }
            case "Barracks" : {
                newBuilding = new Barracks();
                addToMilBuildings(newBuilding, givenCity);
                break;
            }
            case "Stable" : {
                newBuilding = new Stable();
                addToMilBuildings(newBuilding, givenCity);
                break;
            }
            case "Farm" : {
                newBuilding = new Farm();
                addToEcoBuildings(newBuilding, givenCity);
                break;
            }
            case "Market" : {
                newBuilding = new Market();
                addToEcoBuildings(newBuilding, givenCity);
                break;
            }
        }

        if (newBuilding.getCost() > treasury) {
            throw new NotEnoughGoldException();
        }

        treasury -= newBuilding.getCost();

        // TODO: Make sure to update the coolDown value after performing the action
        // TODO: As per the game rules, player can only have one building from each type.
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

    public void addToEcoBuildings (Building b, City c){
        ArrayList<EconomicBuilding> economicalBuildings = c.getEconomicalBuildings();
        economicalBuildings.add((EconomicBuilding) b);
    }
    public void addToMilBuildings (Building b, City c){
        ArrayList<MilitaryBuilding> economicalBuildings = c.getMilitaryBuildings();
        economicalBuildings.add((MilitaryBuilding) b);
    }

    public void upgradeBuilding(Building b) throws
            NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {

        if(b.getUpgradeCost() > treasury) throw  new NotEnoughGoldException();

        b.upgrade();
    }

    public void initiateArmy(City city, Unit unit){
        Army newArmy = new Army(city.getName());

        Army defendingArmy = city.getDefendingArmy();
        ArrayList<Unit> defendingArmyUnits = defendingArmy.getUnits();
        defendingArmyUnits.remove(unit);
        defendingArmy.setUnits(defendingArmyUnits);

        Army parentArmy = unit.getParentArmy();
        ArrayList<Unit> parentArmyUnits = parentArmy.getUnits();
        parentArmyUnits.add(unit);
        parentArmy.setUnits(parentArmyUnits);

        controlledArmies.add(newArmy);
    }

    public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException {
        if(!army.getCurrentLocation().equals(city.getName())) throw new TargetNotReachedException();
        if(controlledCities.contains(city)) throw new FriendlyCityException();

        army.setCurrentStatus(Status.BESIEGING);
        city.setUnderSiege(true);
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
