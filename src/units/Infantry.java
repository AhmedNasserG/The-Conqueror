package units;

public class Infantry extends Unit {

    private static final double[][] InfantryValues = {{-1, -1, -1, -1}, {50, 0.5, 0.6, 0.7},
            {50, 0.5, 0.6, 0.7}, {60, 0.6, 0.7, 0.8}};

    public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
    }

    public Infantry(int level) {
        super(level, (int) InfantryValues[level][0], InfantryValues[level][1], InfantryValues[level][2], InfantryValues[level][3]);
    }

}
