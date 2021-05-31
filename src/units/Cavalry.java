package units;

public class Cavalry extends Unit {

    private static final double[][] CavalryValues = {{-1, -1, -1, -1}, {40, 0.6, 0.7, 0.75},
            {40, 0.6, 0.7, 0.75}, {60, 0.7, 0.8, 0.9}};

    private final double[] targetArcherFactor = {0.5,0.6,0.7};
    private final double[] targetInfantryFactor = {0.3,0.4,0.5};
    private final double[] targetCavalryFactor = {0.2,0.2,0.3};

    public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
    }

    public Cavalry(int level) {
        super(level, (int) CavalryValues[level][0], CavalryValues[level][1], CavalryValues[level][2], CavalryValues[level][3]);
    }

    public double[] getTargetArcherFactor() {
        return targetArcherFactor;
    }

    public double[] getTargetInfantryFactor() {
        return targetInfantryFactor;
    }

    public double[] getTargetCavalryFactor() {
        return targetCavalryFactor;
    }
}
