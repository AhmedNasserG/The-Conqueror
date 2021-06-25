package listeners;

import engine.City;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import units.Army;
import units.Unit;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface UnitPopUpListener extends ActionListener {
    void onRelocateViewClicked(Unit unit);
    void onRelocateClicked(Unit unit, Army army) throws MaxCapacityException;
    void onInitiateClicked(City city, Unit unit);
    void onSetClicked(City city,Army army);

}
