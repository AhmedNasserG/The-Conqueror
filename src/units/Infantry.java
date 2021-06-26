package units;

import exceptions.FriendlyFireException;
import listeners.BattleListener;

import java.util.HashMap;
import java.util.Map;

public class Infantry extends Unit {

    private int casualtiesCount;


    private static final double[][] InfantryValues = {{-1, -1, -1, -1}, {50, 0.5, 0.6, 0.7},
            {50, 0.5, 0.6, 0.7}, {60, 0.6, 0.7, 0.8}};

    private final Map<Class, double[]> attackFactors = new HashMap<Class, double[]>() {{
        put(Archer.class, new double[]{-1, 0.3, 0.4, 0.5});
        put(Infantry.class, new double[]{-1, 0.1, 0.2, 0.3});
        put(Cavalry.class, new double[]{-1, 0.1, 0.2, 0.25});
    }};

    public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
    }

    public Infantry(int level) {
        super(level, (int) InfantryValues[level][0], InfantryValues[level][1], InfantryValues[level][2], InfantryValues[level][3]);
    }

    @Override
    public void attack(Unit target) throws FriendlyFireException {
        super.attack(target);

        int attackersCount = getCurrentSoldierCount();
        double attackFactor = attackFactors.get(target.getClass())[getLevel()];
        this.casualtiesCount = (int)(attackFactor * attackersCount);

        target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(attackFactor*getCurrentSoldierCount()));
        target.getParentArmy().handleAttackedUnit(target);


        String res1 = this.getUnitName() + " ATTACKED ";
        String res2 = target.getUnitName() + ". ";
        String res3 = " has lost: " + casualtiesCount + " " + target.getUnitName() + "(s).";

        String[] results = new String[6];
        results[1] = res1;
        results[3] = res2;
        results[5] = res3;

        getListener().onBattleUpdated(this.getParentArmy(), results);
    }

    @Override
    public String toString() {
        return "Infantry " + getLevel();
    }

}
