package listeners;

import units.Unit;

public interface BattleListener {
    void onBattleUpdated(Unit attacker, Unit target, String result);
}
