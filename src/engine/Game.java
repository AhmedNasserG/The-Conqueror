package engine;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private Player player;
    private ArrayList<City> availableCities;
    private ArrayList<Distance> distances;
    private final int maxTurnCount = 30;
    private int currentTurnCount;

    //this constructor needs checking
    public Game(String playerName, String playerCity) throws IOException {
        currentTurnCount = 1;
        player = new Player(playerName);
        //how to set the rest of the attributes?
    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<City> getAvailableCities() {
        return availableCities;
    }

    public ArrayList<Distance> getDistances() {
        return distances;
    }

    public int getMaxTurnCount() {
        return maxTurnCount;
    }

    public int getCurrentTurnCount() {
        return currentTurnCount;
    }


    public void setCurrentTurnCount(int currentTurnCount) {
        this.currentTurnCount = currentTurnCount;
    }
}
