package views;


import engine.City;
import engine.Game;
import listeners.WorldMapListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WorldMapView extends Frame {
    private StatusPanel statusPanel;
    private JPanel citiesAndText;
    private JPanel cityCards;
    private ArrayList<Army> armiesArray;
    private ArrayList<Army> defendingArmies;
    private ArrayList<Army> controlledArmies;
    private ArrayList<Card> cities;
    private WorldMapListener listener;
    private ArmiesPanel armiesPanel;
    private Game game;

    public WorldMapView(Game game){
        this.setVisible(true);
        this.setLayout(null);
        this.game = game;
        //TODO: intiate army Button: to add a unit from the defending army(and controlled armies)
        // to your attacking. The army must not be idle. !!Should be in the cityView
        //Add this button to any defending army panel
        //TODO: relocateUnit choose a unit and choose an army to put it in the
        //TODO: a frame to choose army and choose a unit from that unit and choose the army to be gone to
        //comboboxes :)

        //TODO: city can't be under siege and attacked at the same time

        citiesAndText = new JPanel();
        citiesAndText.setLayout(new BoxLayout(citiesAndText, BoxLayout.Y_AXIS));
        JLabel chooseLabel = new JLabel("View City");
        chooseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseLabel.setFont(chooseLabel.getFont().deriveFont(Font.BOLD,23));
        citiesAndText.add(chooseLabel);
        citiesAndText.add(Box.createRigidArea(new Dimension(0, 50)));

        cityCards = new JPanel();
        cityCards.setLayout(new GridLayout());

        cities = new ArrayList<>();

        for(City city: game.getAvailableCities())
        {
            Card cityCard = new Card(city);
            cityCards.add(cityCard);
            cities.add(cityCard);
        }

        citiesAndText.add(cityCards);
        citiesAndText.setBounds((getWidth() - 710) / 2, 280, 410, 410);


        controlledArmies = game.getPlayer().getControlledArmies();
        defendingArmies = new ArrayList<>();
        for(City city: game.getPlayer().getControlledCities()){
            defendingArmies.add(city.getDefendingArmy());
        }

        this.add(citiesAndText);

        this.revalidate();
        this.repaint();
    }

    public void setStatusPanel(StatusPanel statusPanel) {
        this.statusPanel = statusPanel;
        statusPanel.setBounds(0, 0, getWidth(), 100);
        add(statusPanel);
    }



    public void setListener(WorldMapListener listener) {
        this.listener = listener;
        armiesPanel = new ArmiesPanel(defendingArmies,controlledArmies, listener);
        JScrollPane pane = new JScrollPane(armiesPanel);
        pane.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane);
        for(Card card: cities)
        {
            card.setListener(listener);
        }
    }

    public void setControlledArmiesAtThisCity(ArrayList<Army> controlledArmiesAtThisCity) {

        armiesPanel = new ArmiesPanel(defendingArmies, controlledArmiesAtThisCity, listener);
        armiesPanel.setBackground(Color.ORANGE);
        armiesPanel.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);
        add(armiesPanel);
        armiesPanel.revalidate();
        armiesPanel.repaint();
    }

    public ArmiesPanel getArmiesPanel() {
        return armiesPanel;
    }
    public void updateCitiesCards(){
        if(cityCards != null)
        {
            cityCards.removeAll();
        }
        for(City city: game.getAvailableCities())
        {
            Card cityCard = new Card(city);
            cityCards.add(cityCard);
            cities.add(cityCard);
            cityCard.setListener(listener);
        }
        cityCards.revalidate();
        cityCards.repaint();

    }
}