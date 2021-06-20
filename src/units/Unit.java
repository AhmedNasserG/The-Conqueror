package units;

import buildings.*;
import exceptions.FriendlyFireException;
import listeners.UnitListener;

import java.util.ArrayList;

public abstract class Unit {

    private int level;
    private int maxSoldierCount;
    private int currentSoldierCount;
    private double idleUpkeep;
    private double marchingUpkeep;
    private double siegeUpkeep;
    private Army parentArmy;

    UnitListener listener;

    public void setListener(UnitListener listener) {
        this.listener = listener;
    }


    public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        this.level = level;
        this.maxSoldierCount = maxSoldierCount;
        this.currentSoldierCount = maxSoldierCount;
        this.idleUpkeep = idleUpkeep;
        this.marchingUpkeep = marchingUpkeep;
        this.siegeUpkeep = siegeUpkeep;
        this.parentArmy = null;

    }


    public void attack(Unit target) throws FriendlyFireException {
        if (parentArmy.equals(target.getParentArmy())) {
            throw new FriendlyFireException();
        }

        listener.onAttack(this, target);
    }

    public String getUnitName() {
        String imgStr = "";
        if (Archer.class.equals(this.getClass())) {
            imgStr = "archer";
        } else if (Cavalry.class.equals(this.getClass())) {
            imgStr = "cavalry";
        } else if (Infantry.class.equals(this.getClass())) {
            imgStr = "Infantry";
        }
        return imgStr;
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
