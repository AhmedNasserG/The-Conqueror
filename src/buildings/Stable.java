package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Cavalry;
import units.Unit;

public class Stable extends MilitaryBuilding {

	private final int[][] StableValuesUpgrades = {{2000, 650}, {-1, 700}};

	public Stable() {
		super(2500, 1500, 600);
	}

	@Override
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		setUpgradeCost(StableValuesUpgrades[getLevel() - 2][0]);
		setRecruitmentCost(StableValuesUpgrades[getLevel() - 2][1]);
	}

	@Override
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if (isCoolDown()) {
			throw new BuildingInCoolDownException();
		}
		if (getCurrentRecruit() == getMaxRecruit()) {
			throw new MaxRecruitedException();
		}
		setCoolDown(true);
		setCurrentRecruit(getCurrentRecruit() + 1);
		return new Cavalry(1);
	}
}
