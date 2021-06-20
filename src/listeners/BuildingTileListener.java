package listeners;

import buildings.Building;
import views.BuildingTile;

import java.awt.event.MouseListener;


public interface BuildingTileListener extends BuildingPopUpListener{
    void onTileClicked(Building building);
}
