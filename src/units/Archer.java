package units;

import exceptions.FriendlyFireException;

import java.util.HashMap;
import java.util.Map;

public class Archer extends Unit {

    private static final double[][] ArcherValues = {{-1, -1, -1, -1}, {60, 0.4, 0.5, 0.6},
            {60, 0.4, 0.5, 0.6}, {70, 0.5, 0.6, 0.7}};

    private final Map<Class, double[]> attackFactors = new HashMap<Class, double[]>() {{
        put(Archer.class, new double[]{-1, 0.3, 0.4, 0.5});
        put(Infantry.class, new double[]{-1, 0.2, 0.3, 0.4});
        put(Cavalry.class, new double[]{-1, 0.1, 0.1, 0.2});
    }};

    public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
    }

    public Archer(int level) {
        super(level, (int) ArcherValues[level][0], ArcherValues[level][1], ArcherValues[level][2], ArcherValues[level][3]);
    }

    @Override
    public void attack(Unit target) throws FriendlyFireException {
        super.attack(target);
        int attackersCount = getCurrentSoldierCount();
        int targetCount = target.getCurrentSoldierCount();
        double attackFactor = attackFactors.get(target.getClass())[getLevel()];
        target.setCurrentSoldierCount(Math.max((int) (targetCount - attackFactor * attackersCount), 0));
        target.getParentArmy().handleAttackedUnit(target);
    }

}
