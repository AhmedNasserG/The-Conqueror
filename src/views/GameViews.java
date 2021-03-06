package views;

public class GameViews {

    private StartMenuView startMenuView;
    private NewGameView newGameView;
    private CityView cityView;
    private BattleView battleView;
    private WorldMapView worldMapView;
    private EndGameView endGameView;

    public BattleView getBattleView() {
        return battleView;
    }

    public void setBattleView(BattleView battleView) {
        this.battleView = battleView;
    }

    public StartMenuView getStartMenuView() {
        return startMenuView;
    }

    public void setStartMenuView(StartMenuView startMenuView) {
        this.startMenuView = startMenuView;
    }

    public NewGameView getNewGameView() {
        return newGameView;
    }

    public void setNewGameView(NewGameView newGameView) {
        this.newGameView = newGameView;
    }

    public CityView getCityView() {
        return cityView;
    }

    public void setCityView(CityView cityView) {
        this.cityView = cityView;
    }

    public WorldMapView getWorldMapView() {
        return worldMapView;
    }

    public void setWorldMapView(WorldMapView worldMapView) {
        this.worldMapView = worldMapView;
    }

    public EndGameView getEndGameView() {
        return endGameView;
    }

    public void setEndGameView(EndGameView endGameView) {
        this.endGameView = endGameView;
    }
}
