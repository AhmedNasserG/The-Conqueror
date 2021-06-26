package listeners;

import units.Army;
import units.Unit;

public interface BattleListener {
    void onBattleUpdated(Army unitParentArmy, String[] results);
    void onAutoResolveEnded(String result);
}
