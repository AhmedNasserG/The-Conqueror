package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import units.Unit;

public abstract class MilitaryBuilding extends Building {

    private int recruitmentCost;
    private int currentRecruit;
    private final int maxRecruit;


    public MilitaryBuilding(int cost, int upgradeCost, int recruitmentCost) {
        super(cost, upgradeCost);
        this.recruitmentCost = recruitmentCost;
        this.maxRecruit = 3;
    }

    public abstract Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException;

    // Getters
    public int getRecruitmentCost() {
        return recruitmentCost;
    }

    public int getCurrentRecruit() {
        return currentRecruit;
    }

    public int getMaxRecruit() {
        return maxRecruit;
    }


    // Setters
    public void setRecruitmentCost(int recruitmentCost) {
        this.recruitmentCost = recruitmentCost;
    }

    public void setCurrentRecruit(int currentRecruit) {
        this.currentRecruit = currentRecruit;
    }


}
