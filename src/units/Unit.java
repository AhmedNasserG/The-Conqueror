package units;

import exceptions.FriendlyFireException;

import java.util.ArrayList;

public abstract class Unit {

    private int level;
    private int maxSoldierCount;
    private int currentSoldierCount;
    private double idleUpkeep;
    private double marchingUpkeep;
    private double siegeUpkeep;
    private Army parentArmy;


    public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        this.level = level;
        this.maxSoldierCount = maxSoldierCount;
        this.currentSoldierCount = maxSoldierCount;
        this.idleUpkeep = idleUpkeep;
        this.marchingUpkeep = marchingUpkeep;
        this.siegeUpkeep = siegeUpkeep;
    }


    public void attack(Unit target) throws FriendlyFireException {
        // TODO 2.1.2 : How do I know the type of the unit (Archer/Cavalry/Infantry)?
        // code below is not correct
        ArrayList<Unit> armyUnits = parentArmy.getUnits();
        for(Unit u : armyUnits){
            int attackerCurrentSoldierCount = u.getCurrentSoldierCount();
            int targetCurrentSoldierCount = target.getCurrentSoldierCount();
            double factor = 1.0;
            int newTargetCurrentSoldierCount = (int) Math.max(targetCurrentSoldierCount - attackerCurrentSoldierCount * factor,0);
            target.setCurrentSoldierCount(newTargetCurrentSoldierCount);

            if(newTargetCurrentSoldierCount == 0){
                // game over, i guess?
            }
        }
    }

    // Getters
    public int getLevel() {
        return level;
    }

    public int getMaxSoldierCount() {
        return maxSoldierCount;
    }

    public int getCurrentSoldierCount() {
        return currentSoldierCount;
    }

    public double getIdleUpkeep() {
        return idleUpkeep;
    }

    public double getMarchingUpkeep() {
        return marchingUpkeep;
    }

    public double getSiegeUpkeep() {
        return siegeUpkeep;
    }

    public Army getParentArmy() {
        return parentArmy;
    }

    // Setters
    public void setCurrentSoldierCount(int currentSoldierCount) {
        this.currentSoldierCount = currentSoldierCount;
    }

    public void setParentArmy(Army parentArmy) {
        this.parentArmy = parentArmy;
    }
}
