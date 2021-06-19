package units;

import exceptions.FriendlyFireException;
import listeners.BattleListener;
import listeners.UnitListener;

import java.util.HashMap;
import java.util.Map;

public class Archer extends Unit implements UnitListener {

    private int casualtiesCount;

    private BattleListener listener;


    public void setListener(BattleListener listener) {
        this.listener = listener;
    }

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
        this.casualtiesCount = (int)(attackFactor * attackersCount);

        target.setCurrentSoldierCount(Math.max(targetCount - casualtiesCount, 0));
        target.getParentArmy().handleAttackedUnit(target);
    }

    @Override
    public void onAttack(Unit attacker, Unit target) throws FriendlyFireException {
        this.attack(target);

        String attackerUnit = attacker.getClass().getName();
        String targetUnit = target.getClass().getName();

        String res = attackerUnit + " Attacked " + targetUnit + '\n' +
                "Defending Army Has Lost: " + casualtiesCount;

        listener.onBattleUpdated(attacker, target, res);
    }
}
