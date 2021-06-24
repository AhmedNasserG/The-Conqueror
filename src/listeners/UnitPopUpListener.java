package listeners;

import exceptions.FriendlyFireException;
import units.Unit;

import java.awt.event.ActionListener;

public interface UnitPopUpListener extends ActionListener {
    public void onRelocateCLicked(Unit unit);
}
