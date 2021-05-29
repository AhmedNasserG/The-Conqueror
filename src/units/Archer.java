package units;

public class Archer extends Unit {

    private static final double[][] ArcherValues = {{-1, -1, -1, -1}, {60, 0.4, 0.5, 0.6},
            {60, 0.4, 0.5, 0.6}, {70, 0.5, 0.6, 0.7}};

    public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
    }

    public Archer(int level) {
        super(level, (int) ArcherValues[level][0], ArcherValues[level][1], ArcherValues[level][2], ArcherValues[level][3]);
    }
}
