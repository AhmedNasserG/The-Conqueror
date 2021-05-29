package units;

public class Cavalry extends Unit {

    private static final double[][] CavalryValues = {{-1, -1, -1, -1}, {40, 0.6, 0.7, 0.75},
            {40, 0.6, 0.7, 0.75}, {60, 0.7, 0.8, 0.9}};

    public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
    }

    public Cavalry(int level) {
        super(level, (int) CavalryValues[level][0], CavalryValues[level][1], CavalryValues[level][2], CavalryValues[level][3]);
    }

}
