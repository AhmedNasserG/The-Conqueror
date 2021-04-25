package units;

public abstract class Unit {
    // Attribiutes
   private int level;
   private int maxSoldierCount;
   private int currentSoldierCount;
   private double idleUpkeep;
   private double marchingUpkeep;
   private double siegeUpkeep;
    // The Constructor

    public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep)
    {
        this.level = level;
        this.maxSoldierCount= maxSoldierCount;
        this.idleUpkeep=idleUpkeep;
        this.marchingUpkeep=marchingUpkeep;
        this.siegeUpkeep=siegeUpkeep;
    }

    // Getters READ

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

    // Setters WRITE


    public void setCurrentSoldierCount(int currentSoldierCount) {
        this.currentSoldierCount = currentSoldierCount;
    }


}
