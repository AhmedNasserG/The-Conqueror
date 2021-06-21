package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public abstract class Building {

    private int cost;
    private int level;
    private int upgradeCost;
    private boolean coolDown;


    public Building(int cost, int upgradeCost) {
        this.cost = cost;
        this.level = 1;
        this.upgradeCost = upgradeCost;
        this.coolDown = true;
    }

    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        if (coolDown) {
            throw new BuildingInCoolDownException();
        }
        if (level == 3) {
            throw new MaxLevelException();
        }
        level++;
        coolDown = true;
    }

    public String getBuildingName() {
        String imgStr = "";
        if (ArcheryRange.class.equals(this.getClass())) {
            imgStr = "ArcheryRange";
        } else if (Stable.class.equals(this.getClass())) {
            imgStr = "Stable";
        } else if (Barracks.class.equals(this.getClass())) {
            imgStr = "Barracks";
        } else if (Market.class.equals(this.getClass())) {
            imgStr = "Market";
        } else if (Farm.class.equals(this.getClass())) {
            imgStr = "Farm";
        }
        return imgStr;
    }

    // Getters

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public boolean isCoolDown() {
        return coolDown;
    }

    // Setters
    public void setLevel(int level) {
        this.level = level;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public void setCoolDown(boolean coolDown) {
        this.coolDown = coolDown;
    }
}
