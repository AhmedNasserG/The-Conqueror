package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {

    private final int[] farmGoldValue = {-1, 500, 700, 1000};

    public Farm() {
        super(1000, 500);
    }

    @Override
    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        super.upgrade();
        setUpgradeCost(700);
    }

    @Override
    public int harvest() {
        return farmGoldValue[getLevel()];
    }

    public String getName() {
        return "Farm";
    }
}
