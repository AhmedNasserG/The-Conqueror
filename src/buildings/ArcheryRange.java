package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

    private final int[][] ArcheryRangeValuesUpgrades = {{700, 450}, {-1, 500}};

    public ArcheryRange() {
        super(1500, 800, 400);
    }

    @Override
    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        super.upgrade();
        setUpgradeCost(ArcheryRangeValuesUpgrades[getLevel() - 2][0]);
        setRecruitmentCost(ArcheryRangeValuesUpgrades[getLevel() - 2][1]);
    }


}
