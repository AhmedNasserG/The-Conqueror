package controller;

import engine.Game;
import engine.Player;
import views.StartMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO: the class should implement all the listeners we made in the engine
public class GameGUI implements ActionListener {
    private StartMenuView startMenuView;
    private Player currentPlayer;
    private Game currentGame;

    public GameGUI(){
        startMenuView = new StartMenuView();
        startMenuView.setVisible(true);

        startMenuView.revalidate();
        startMenuView.repaint();

    }


    public static void main(String[] args) {
        new GameGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
