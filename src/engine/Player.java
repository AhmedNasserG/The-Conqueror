package engine;

import buildings.*;
import exceptions.*;
import units.Army;
import units.Status;
import units.Unit;

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


    public void recruitUnit(String type, String cityName) throws
            BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {

        // TODO : فاضل فيه حبة هبقي اكمله بعدين عشان زهقت

        City givenCity = null;
        for(City city: controlledCities){
            if(city.getName().equals(cityName)) givenCity = city;
        }

        MilitaryBuilding correspondingBuilding = null;
        switch(type){
            case "Archer" -> correspondingBuilding = new ArcheryRange();
            case "Cavalry" -> correspondingBuilding = new Stable();
            case "Infantry" -> correspondingBuilding = new Barracks();
        }
        if(correspondingBuilding.getRecruitmentCost() > treasury) throw new NotEnoughGoldException();
        Unit unit = correspondingBuilding.recruit();
        treasury -= correspondingBuilding.getRecruitmentCost();

        Army defendingArmy = givenCity.getDefendingArmy();
        ArrayList<Unit> defendingArmyUnits = defendingArmy.getUnits();
        defendingArmyUnits.add(unit);
        defendingArmy.setUnits(defendingArmyUnits);

        Army unitParentArmy = unit.getParentArmy();
        ArrayList<Unit> parentArmyUnits = unitParentArmy.getUnits();
        parentArmyUnits.add(unit);
        unit.setParentArmy(unitParentArmy);
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

            }
            case "Barracks" : {
                newBuilding = new Barracks();
                addToMilBuildings(newBuilding, givenCity);

            }
            case "Stable" : {
                newBuilding = new Stable();
                addToMilBuildings(newBuilding, givenCity);

            }
            case "Farm" : {
                newBuilding = new Farm();
                addToEcoBuildings(newBuilding, givenCity);

            }
            case "Market" : {
                newBuilding = new Market();
                addToEcoBuildings(newBuilding, givenCity);

            }
        }
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
