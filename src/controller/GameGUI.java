package controller;

import views.StartMenuView;

public class GameGUI {
    private StartMenuView startMenuView;

    public GameGUI(){
        startMenuView = new StartMenuView();

        startMenuView.setVisible(true);
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
