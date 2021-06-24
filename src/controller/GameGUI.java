package controller;

import buildings.*;
import engine.City;
import engine.Game;
import exceptions.*;
import listeners.*;
import units.*;
import views.*;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameGUI
        implements ActionListener, NewGameListener, StartMenuListener, CityViewListener,
        WorldMapListener, BattleListener, UnitListener, CardListener, UnitPopUpListener, CityPopUpListener {

    private Game game;
    private final GameViews view;
    private StatusPanel statusPanel;

    private Army playerArmy;
    private City targetCity;
    private Army targetArmy;

    public GameGUI() throws IOException {
        view = new GameViews();
        view.setNewGameView(new NewGameView());
        view.getNewGameView().setListener(this);

    }

    public static void main(String[] args) throws IOException {
        new GameGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("START AUTORESOLVE")) {
            try {
                System.out.println("actionPerformed");
                game.autoResolve(playerArmy, targetArmy);
            } catch (FriendlyFireException friendlyFireException) {
                friendlyFireException.printStackTrace();
            }
        } else if (e.getActionCommand().equals("ATTACK")) {
            Card playerUnitCard = view.getBattleView().getPlayerUnitsPanel().getSelectedCard();
            Card targetUnitCard = view.getBattleView().getTargetUnitsPanel().getSelectedCard();

            if (playerUnitCard == null || targetUnitCard == null) {
                System.out.println("units not selected correctly");
                showMessageDialog(null, "Select a Friendly and a Target unit!");
            } else {
                try {
                    this.onAttack(playerUnitCard.getUnit(), targetUnitCard.getUnit());
                } catch (FriendlyFireException friendlyFireException) {
                    friendlyFireException.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equals("worldMapButton")) {
            view.getCityView().dispose();
            view.setWorldMapView(new WorldMapView(game));
            view.getWorldMapView().setListener(this);
            view.getWorldMapView().setStatusPanel(statusPanel);
            statusPanel.updateStatusPanel();

        } else if (e.getActionCommand().equals("End Turn")) {
            game.endTurn();
            statusPanel.updateStatusPanel();
            if (game.isGameOver()) {
                EndGameView endGameView = new EndGameView(game.getPlayer().getControlledCities().size() == game.getAvailableCities().size());
                endGameView.setListener(this);
                view.setEndGameView(endGameView);
            }
        } else if (e.getActionCommand().equals("PLAY AGAIN!")) {
            view.setNewGameView(new NewGameView());
            view.getNewGameView().setListener(this);
            view.getEndGameView().dispose();
        } else if (e.getActionCommand().equals("EXIT")) {
            System.exit(0);
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
        //TEST ONLY
        Army testArmy = new Army("Sparta");
        testArmy.getUnits().add(new Archer(2));
        game.getPlayer().getControlledArmies().add(testArmy);

        statusPanel = new StatusPanel();
        statusPanel.setGame(game);
        statusPanel.setListener(this);

        view.setWorldMapView(new WorldMapView(game));
        view.getWorldMapView().setListener(this);
        view.getWorldMapView().setStatusPanel(statusPanel);
        statusPanel.updateStatusPanel();

        view.getNewGameView().dispose();

    }

    //TODO: remove this method;
    @Override
    public void onCityClicked(City city) {
        CityPopUp cityPopUp = new CityPopUp(city);
    }

    @Override
    public void onCityCardClicked(City city) {
        if (game.getPlayer().getControlledCities().contains(city)) {
            CityView cityView = new CityView(city);
            cityView.setListener(this);
            cityView.setStatusPanel(statusPanel);
            statusPanel.updateStatusPanel();
            view.setCityView(cityView);
            view.getWorldMapView().dispose();
        } else {
            CityPopUp cityPopUp = new CityPopUp(city);
            cityPopUp.setListener(this);
        }

    }

    @Override
    public void onAttackClicked() {
//TODO: Omar's part
    }

    @Override
    public void onLaySiegeClicked() {
//TODO: should do sth here
    }

    @Override
    public void onSetTargetClicked() {

    }

    @Override
    public void onNewUnitCardClicked(Unit unit) {
        newUnitPopUp newUnitPopUp = new newUnitPopUp(unit);
        newUnitPopUp.setListener(this);
    }

    @Override
    public void onInitiateArmyClicked() {
//TODO: initiate new Army
    }

    @Override
    public void onUpgradeClicked(BuildingPopUp buildingPopUp) {
        try {
            game.getPlayer().upgradeBuilding(buildingPopUp.getBuildingToShow());
            buildingPopUp.dispose();
            view.getCityView().updateCityGrid();
            statusPanel.updateStatusPanel();
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
            statusPanel.updateStatusPanel();
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
                statusPanel.updateStatusPanel();
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
    public void onManualAttackChosen(Army playerArmy, City targetCity) {
        this.playerArmy = playerArmy;
        this.targetCity = targetCity;
        this.targetArmy = targetCity.getDefendingArmy();

        BattleView bv = new BattleView("MANUAL ATTACK", playerArmy, targetCity, this);
        view.setBattleView(bv);
        bv.getStartManualAttackBtn().addActionListener(this);

        view.getWorldMapView().setVisible(false);
    }

    @Override
    public void onAutoResolveChosen(Army playerArmy, City targetCity) {
        this.playerArmy = playerArmy;
        this.targetCity = targetCity;
        this.targetArmy = targetCity.getDefendingArmy();

        BattleView bv = new BattleView("AUTO RESOLVE", playerArmy, targetCity, this);
        view.setBattleView(bv);
        bv.getStartAutoResolveBtn().addActionListener(this);

        view.getWorldMapView().setVisible(false);
    }

    @Override
    public void onAttack(Unit attacker, Unit target) throws FriendlyFireException {
        attacker.attack(target);
    }

    @Override
    public void onBattleUpdated(Army unitParentArmy, String result1, String result2) {
        JTextArea log = view.getBattleView().getBattleLog();
        String RESULT = result1 + (game.getPlayer().getControlledArmies().contains(unitParentArmy) ? "Target" : "Player") + result2;
        log.setText((log.getText() + "\n\n" + RESULT));

        updateUnitsPanels(playerArmy, targetArmy);
        view.getBattleView().getStartAutoResolveBtn().setEnabled(false);
        view.getBattleView().getBattleResultsDisplay().setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
        view.getBattleView().getBattleResultsDisplay().setText(RESULT);
        view.getBattleView().getBattleResultsDisplay().setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

        view.getBattleView().getPlayerUnitsPanel().setSelectedCard(null);
        view.getBattleView().getTargetUnitsPanel().setSelectedCard(null);

        if (targetArmy.getUnits().size() == 0) {
            endBattle(true, targetCity);
        } else if (playerArmy.getUnits().size() == 0) {
            endBattle(false, targetCity);
        }

        System.out.println(RESULT);
    }

    public void updateUnitsPanels(Army playerArmy, Army targetArmy) {
        view.getBattleView().setPlayerArmy(playerArmy);
        view.getBattleView().setTargetArmy(targetArmy);

        view.getBattleView().getPlayerUnitsPanel().updatePanel(playerArmy);
        view.getBattleView().getTargetUnitsPanel().updatePanel(targetArmy);
    }

    public void endBattle(boolean playerWon, City targetCity) {
        if (playerWon) {
            showMessageDialog(null, "YOU WON THE BATTLE!\n\n" + "Enemy's " + targetCity.getName() + " City Has Been Occupied!");
        } else {
            showMessageDialog(null, "YOU LOST THE BATTLE!\n\nRETREATING");
        }

        view.getBattleView().dispose();
    }

    public void onUnitCardClicked(Unit unit) {
        JPanel p = view.getBattleView().getUnitInfoPanel();
        p.removeAll();
        p.setBorder(BorderFactory.createTitledBorder("UNIT INFO"));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        Card c = new Card(unit);
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
    public void onFriendlyUnitCardClicked(Card c) {
        onUnitCardClicked(c.getUnit());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("FRIENDLY UNIT INFO");
        titledBorder.setTitleColor(Color.BLUE);
        view.getBattleView().getUnitInfoPanel().setBorder(titledBorder);

        if (view.getBattleView().getPlayerUnitsPanel().getSelectedCard() != null) {
            view.getBattleView().getPlayerUnitsPanel().getSelectedCard().setBorder(null);
        }

        c.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        view.getBattleView().getPlayerUnitsPanel().setSelectedCard(c);
        // Selected To Attack

        System.out.println("friendly unit");
    }

    @Override
    public void onEnemyUnitCardClicked(Card c) {
        onUnitCardClicked(c.getUnit());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("ENEMY UNIT INFO");
        titledBorder.setTitleColor(Color.RED);
        view.getBattleView().getUnitInfoPanel().setBorder(titledBorder);


        if (view.getBattleView().getTargetUnitsPanel().getSelectedCard() != null) {
            view.getBattleView().getTargetUnitsPanel().getSelectedCard().setBorder(null);
        }

        c.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        view.getBattleView().getTargetUnitsPanel().setSelectedCard(c);

        System.out.println("enemy unit");
    }

    @Override
    public void onArmyCardClicked(Army army) {
        //Checking if the card is of defending type or not
        ArmyPopUp armyPopUp;
        if (game.getPlayer().getControlledArmies().contains(army)) {
            armyPopUp = new ArmyPopUp(army, "");
        } else {
            armyPopUp = new ArmyPopUp(army, "Defending");
        }
        armyPopUp.setListener(this);

    }

    @Override
    public void onRelocateViewClicked(Unit unit) {
        new RelocateView(unit, game.getPlayer().getControlledArmies());
    }

    public void onRelocateClicked(Unit unit, Army army) throws MaxCapacityException {
        army.relocateUnit(unit);
    }

    @Override
    public void onAttackCityClicked(City city) {
        ArrayList<Army> availableArmiesAtThisCity = new ArrayList<>();
        for (Army army : game.getPlayer().getControlledArmies()) {
            if (army.getCurrentLocation().equals(city.getName())) {
                for (Unit unit : army.getUnits()) {
                    if (unit instanceof Archer){
                        ((Archer) unit).setListener(this);
                    } else if (unit instanceof Cavalry){
                        ((Cavalry) unit).setListener(this);
                    } else if (unit instanceof Infantry){
                        ((Infantry) unit).setListener(this);
                    }
                }
                availableArmiesAtThisCity.add(army);
            }
        }
        AttackCityPopup attackCityPopup = new AttackCityPopup(city, availableArmiesAtThisCity);
        attackCityPopup.setListener(this);
    }

    @Override
    public void onLaySiegeCityButton(City city) {

    }
}
