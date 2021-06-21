package listeners;

import units.Army;
import units.Unit;

public interface BattleListener {
    void onBattleUpdated(Army unitParentArmy, String result1, String result2);
}
