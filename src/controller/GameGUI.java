package controller;

import engine.Game;
import listeners.BattleListener;
import listeners.NewGameListener;
import listeners.StartMenuListener;
import listeners.WorldMapListener;
import units.Unit;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameGUI
        implements ActionListener, NewGameListener, StartMenuListener, WorldMapListener, BattleListener {

    private Game game;
    private final GameViews view;

    public GameGUI() throws IOException, InterruptedException {
        view = new GameViews();
        view.setStartMenuView(new StartMenuView());
        view.getStartMenuView().setListener(this);
        view.getBattleView().revalidate();
        view.getBattleView().repaint();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        new GameGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
        view.setCityView(new CityView());
        view.getCityView().setPlayerName(game.getPlayer().getName());
        view.getCityView().setCurrentTurnCount(game.getCurrentTurnCount());
        view.getCityView().setFood(game.getPlayer().getFood());
        view.getCityView().setTreasury(game.getPlayer().getTreasury());
        view.getCityView().revalidate();
        view.getCityView().repaint();

    }

    @Override
    public void onManualAttackChosen() throws InterruptedException {
        view.setBattleView(new BattleView("MANUAL ATTACK"));
        view.getBattleView().setVisible(true);
        // TODO: hide WorldMapView
    }

    @Override
    public void onAutoResolveChosen() throws InterruptedException {
        view.setBattleView(new BattleView("AUTO RESOLVE"));
        view.getBattleView().setVisible(true);
        // TODO: hide WorldMapView
    }


    @Override
    public void onBattleUpdated(Unit attacker, Unit target, String result) {
        JLabel resultsDisplay = view.getBattleView().getBattleResultsDisplay();
        resultsDisplay.setText(result);
        resultsDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        view.getBattleView().setBattleResultsDisplay(resultsDisplay);
        view.getBattleView().revalidate();
        view.getBattleView().repaint();
    }


    public void autoResolveBattle() {
        JPanel battlePanel = view.getBattleView().getBattlePanel();

    }
}
