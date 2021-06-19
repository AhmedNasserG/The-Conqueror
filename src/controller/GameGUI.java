package controller;

import engine.Game;
import engine.Player;
import listeners.NewGameListener;
import listeners.StartMenuListener;
import views.CityView;
import views.GameViews;
import views.NewGameView;
import views.StartMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameGUI implements ActionListener, NewGameListener, StartMenuListener {

    private Game game;
    private GameViews view;

    public GameGUI() throws IOException {
        view = new GameViews();
        view.setStartMenuView(new StartMenuView());
        view.getStartMenuView().setListener(this);
    }


    public static void main(String[] args) throws IOException {
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
        view.setCityView(new CityView(game.getPlayer().getControlledCities().get(0)));
        view.getCityView().setPlayerName(game.getPlayer().getName());
        view.getCityView().setCurrentTurnCount(game.getCurrentTurnCount());
        view.getCityView().setFood(game.getPlayer().getFood());
        view.getCityView().setTreasury(game.getPlayer().getTreasury());
        view.getCityView().revalidate();
        view.getCityView().repaint();

    }
}
