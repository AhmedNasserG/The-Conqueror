package units;

import exceptions.FriendlyFireException;

import java.util.HashMap;
import java.util.Map;

public class Cavalry extends Unit {

    private static final double[][] CavalryValues = {{-1, -1, -1, -1}, {40, 0.6, 0.7, 0.75},
            {40, 0.6, 0.7, 0.75}, {60, 0.7, 0.8, 0.9}};

    private final Map<Class, double[]> attackFactors = new HashMap<Class, double[]>() {{
        put(Archer.class, new double[]{-1, 0.5, 0.6, 0.7});
        put(Infantry.class, new double[]{-1, 0.3, 0.4, 0.5});
        put(Cavalry.class, new double[]{-1, 0.2, 0.2, 0.3});
    }};

    public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
    }

    public Cavalry(int level) {
        super(level, (int) CavalryValues[level][0], CavalryValues[level][1], CavalryValues[level][2], CavalryValues[level][3]);
    }

    @Override
    public void attack(Unit target) throws FriendlyFireException {
        super.attack(target);
        int attackersCount = getCurrentSoldierCount();
        int targetCount = target.getCurrentSoldierCount();
        double attackFactor = attackFactors.get(target.getClass())[getLevel()];
        target.setCurrentSoldierCount(Math.max(targetCount - (int)(attackFactor * attackersCount), 0));
        target.getParentArmy().handleAttackedUnit(target);
    }
}
