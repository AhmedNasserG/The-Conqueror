package controller;

import buildings.*;
import engine.City;
import engine.Game;
import exceptions.*;
import listeners.*;
import units.*;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameGUI
        implements ActionListener, NewGameListener, StartMenuListener, CityViewListener,
        WorldMapListener, BattleListener, UnitListener, CardListener, UnitPopUpListener{

    private Game game;

    private final GameViews view;

    private Army a;
    private Army b;

    public GameGUI() throws IOException {
        view = new GameViews();


    }


    public static void main(String[] args) throws IOException {


        new GameGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getBattleView().getStartAutoResolveBtn()){
            try {
                System.out.println("actionPerformed");
                game.autoResolve(a,b);
            } catch (FriendlyFireException friendlyFireException) {
                friendlyFireException.printStackTrace();
            }
        }
    }


    @Override
    public void onNewGameClicked() throws IOException {
        view.setNewGameView(new NewGameView());
        view.getNewGameView().setListener(this);
        view.getStartMenuView().dispose();
    }

    @Override
    public void onPlayClicked() throws IOException {
        String playerName = view.getNewGameView().getPlayerName();
        String cityName = view.getNewGameView().getCityName();
        game = new Game(playerName, cityName);
        view.getNewGameView().dispose();

        City city = game.getPlayer().getControlledCities().get(0);

        //For TEST only
//        ArcheryRange a = new ArcheryRange();
//        a.setCoolDown(false);
//        city.getMilitaryBuildings().add(a);
//        city.getMilitaryBuildings().add(new Barracks());
//        city.getEconomicalBuildings().add(new Market());
//        city.getEconomicalBuildings().add(new Farm());
        //---------

        CityView cityView = new CityView(city);
        cityView.setListener(this);


        // Set Status panel
        cityView.setPlayerName(game.getPlayer().getName());
        cityView.setCurrentTurnCount(game.getCurrentTurnCount());
        cityView.setFood(game.getPlayer().getFood());
        cityView.setTreasury(game.getPlayer().getTreasury());

        view.setCityView(cityView);
        view.getCityView().revalidate();
        view.getCityView().repaint();
    }

    @Override
    public void onManualAttackChosen() throws InterruptedException {
//        view.setBattleView(new BattleView("MANUAL ATTACK"));
//        view.getBattleView().setVisible(true);
        // TODO: hide WorldMapView
    }

    @Override
    public void onAutoResolveChosen() throws InterruptedException {
//        view.setBattleView(new BattleView("AUTO RESOLVE"));
//        view.getBattleView().setVisible(true);
        // TODO: hide WorldMapView
    }



    @Override
    public void onUpgradeClicked(BuildingPopUp buildingPopUp) {
        try {
            game.getPlayer().upgradeBuilding(buildingPopUp.getBuildingToShow());
            buildingPopUp.dispose();
            view.getCityView().updateCityGrid();
        } catch (BuildingInCoolDownException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building is Cooling Down, Please wait to the next turn to upgrade.");
        } catch (MaxLevelException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building Reached Maximum Level Available.");
        } catch (NotEnoughGoldException e) {
            JOptionPane.showMessageDialog(null, "Sorry You Have Not Enough Gold To Upgrade.");
        }
    }

    @Override
    public void onRecruitClicked(BuildingPopUp buildingPopUp) {
        try {
            game.getPlayer().recruitUnit(((MilitaryBuilding) (buildingPopUp.getBuildingToShow())));
            buildingPopUp.dispose();
        } catch (BuildingInCoolDownException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building is Cooling Down, Please wait to the next turn to upgrade.");
        } catch (MaxRecruitedException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building Already Recruited The Maximum Number of Units Per Turn, Please wait to the next turn to recruit more.");
        } catch (NotEnoughGoldException e) {
            JOptionPane.showMessageDialog(null, "Sorry You Have Not Enough Gold To Recruit.");
        }
    }



    @Override
    public void onBuildingCardClicked(Building building, String whereToBuild) throws NotEnoughGoldException {
        if (whereToBuild != null) {
            try {
                game.getPlayer().build(building.getBuildingName(), whereToBuild);
                view.getCityView().updateCityGrid();
            } catch (BuildingInCityAlreadyException e) {
                JOptionPane.showMessageDialog(null, "Sorry You Already Have " + building.getBuildingName() + " In Your City, Please Choose Another Building.");
            } catch (NotEnoughGoldException e) {
                JOptionPane.showMessageDialog(null, "Sorry You Have Not Enough Gold To Build " + building.getBuildingName() + ".");
            }
        } else {
            BuildingPopUp buildingPopUp = new BuildingPopUp(building);
            buildingPopUp.setListener(this);
        }
    }

    @Override
    public void onUnitCardClicked(Unit unit) {
        JPanel p = view.getBattleView().getUnitInfoPanel();
        p.removeAll();
        p.setBorder(BorderFactory.createTitledBorder("UNIT INFO"));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        Card c = new Card(unit); // true here is a dummy value
        c.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(c);

        UnitInfoLabel l1 = new UnitInfoLabel("   Soldier Count: " + unit.getCurrentSoldierCount());
        UnitInfoLabel l2 = new UnitInfoLabel("   Max Soldier Count: " + unit.getMaxSoldierCount());
        UnitInfoLabel l3 = new UnitInfoLabel("   Siege Upkeep: " + unit.getSiegeUpkeep());
        UnitInfoLabel l4 = new UnitInfoLabel("   Idle Upkeep: " + unit.getIdleUpkeep());
        UnitInfoLabel l5 = new UnitInfoLabel("   Marching Upkeep: " + unit.getMarchingUpkeep());

        p.add(l1);
        p.add(Box.createRigidArea(new Dimension(0, 5)));
        p.add(l2);
        p.add(Box.createRigidArea(new Dimension(0, 5)));
        p.add(l3);
        p.add(Box.createRigidArea(new Dimension(0, 5)));
        p.add(l4);
        p.add(Box.createRigidArea(new Dimension(0, 5)));
        p.add(l5);

        view.getBattleView().revalidate();
        view.getBattleView().repaint();
        System.out.println("displayed units info");
    }

    @Override
    public void onFriendlyUnitCardClicked(Unit unit) {
        onUnitCardClicked(unit);

        // Selected To Attack

        System.out.println("friendly unit");
    }

    @Override
    public void onEnemyUnitCardClicked(Unit unit) {
        onUnitCardClicked(unit);

        UnitPopUp unitPopUp = new UnitPopUp(unit);
        unitPopUp.setListener(this);

        System.out.println("enemy unit");
    }

    @Override
    public void onAttackPressed(Unit u) {


        System.out.println("attacked enemy unit");
    }

    @Override
    public void onAttack(Unit attacker, Unit target) throws FriendlyFireException {
        attacker.attack(target);
    }

    @Override
    public void onBattleUpdated(Army unitParentArmy, String result1, String result2) {
        JTextArea log = view.getBattleView().getBattleLog();
        String RESULT = "- " + result1 + (game.getPlayer().getControlledArmies().contains(unitParentArmy) ? "Target" : "Player") + result2;
        log.setText((log.getText() + "\n\n" + RESULT));
//        JLabel resultsDisplay = new JLabel();
//
//        resultsDisplay.setText(result);
//        resultsDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
//        view.getBattleView().setBattleResultsDisplay(resultsDisplay);
//        view.getBattleView().revalidate();
//        view.getBattleView().repaint();
        updateUnitsPanels(a,b);
        view.getBattleView().getStartAutoResolveBtn().setEnabled(false);
        System.out.println(a.getUnits().size() + " " + b.getUnits().size());
//        System.out.println(RESULT);
    }

    public void updateUnitsPanels(Army playerArmy, Army targetArmy){
//        UnitsPanel playerUnitsPanel = new UnitsPanel("player", playerArmy, this);
//        UnitsPanel targetUnitsPanel = new UnitsPanel("target", targetArmy, this);
//
//
//        view.getBattleView().setPlayerUnitsPanel(playerUnitsPanel);
//        view.getBattleView().setTargetUnitsPanel(targetUnitsPanel);
//
//        view.getBattleView().getPlayerUnitsPanel().revalidate();
//        view.getBattleView().getPlayerUnitsPanel().repaint();
//        view.getBattleView().getPlayerUnitsPanel().removeAll();
//        view.getBattleView().getTargetUnitsPanel().removeAll();

        view.getBattleView().setPlayerArmy(playerArmy);
        view.getBattleView().setTargetArmy(targetArmy);
        view.getBattleView().initBattlePanel();

//        view.getBattleView().revalidate();
//        view.getBattleView().repaint();
    }

    @Override
    public void onArmyCardClicked(Army army) {

    }


}
