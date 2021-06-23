package listeners;

import exceptions.FriendlyFireException;
import units.Unit;

public interface UnitPopUpListener {
    public void onAttackPressed(Unit u) throws FriendlyFireException;
}
