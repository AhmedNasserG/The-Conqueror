package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {

	private final int[][] BarracksValuesUpgrades = {{1500, 550}, {-1, 600}};

	public Barracks() {
		super(2000, 1000, 500);
	}

	@Override
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		setUpgradeCost(BarracksValuesUpgrades[getLevel() - 2][0]);
		setRecruitmentCost(BarracksValuesUpgrades[getLevel() - 2][1]);
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
		return new Infantry(1);
	}
}
