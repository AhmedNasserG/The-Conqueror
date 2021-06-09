package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

    private final int[] marketGoldValue = {-1, 1000, 1500, 2000};

    public Market() {
        super(1500, 700);
    }

    @Override
    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        super.upgrade();
        setUpgradeCost(1000);
    }

    @Override
    public int harvest() {
        return marketGoldValue[getLevel()];
    }
}
