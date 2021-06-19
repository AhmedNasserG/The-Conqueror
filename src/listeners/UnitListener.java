package listeners;

import exceptions.FriendlyFireException;
import units.Unit;

public interface UnitListener {
    void onAttack(Unit attacker, Unit target) throws FriendlyFireException;
}
