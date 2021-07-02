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

    public WorldMapView(Game game) {
        this.setVisible(true);

        String backgroundPath = "res/backgrounds/start_menuOpacity.jpg";
        this.setBackground(backgroundPath);


        this.setLayout(new BorderLayout());
        this.game = game;


        citiesAndText = new JPanel();
        citiesAndText.setLayout(new BoxLayout(citiesAndText, BoxLayout.Y_AXIS));
        JLabel chooseLabel = new JLabel("Open A City");
        chooseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseLabel.setFont(chooseLabel.getFont().deriveFont(Font.BOLD, 23));
        citiesAndText.add(chooseLabel);
        citiesAndText.add(Box.createRigidArea(new Dimension(0, 50)));

        cityCards = new JPanel();
        cityCards.setLayout(new GridLayout());

        cities = new ArrayList<>();

        for (City city : game.getAvailableCities()) {
            Card cityCard = new Card(city);
            cityCard.removeAll();
            cityCard.setSize(300,400);
            ImageIcon icon = new ImageIcon("res/img/" + city.getName().toLowerCase() + "City.jpeg");
            icon = new ImageIcon(icon.getImage().getScaledInstance(cityCard.getWidth()+100, cityCard.getHeight()+100, Image.SCALE_DEFAULT));
            cityCard.setIcon(icon);
            cityCard.revalidate();
            cityCard.repaint();
            cityCards.add(cityCard);
            cities.add(cityCard);
        }

        cityCards.setOpaque(false);
        citiesAndText.add(cityCards);
        citiesAndText.setOpaque(false);


        controlledArmies = game.getPlayer().getControlledArmies();
        defendingArmies = new ArrayList<>();
        for (City city : game.getPlayer().getControlledCities()) {
            defendingArmies.add(city.getDefendingArmy());
        }

        this.add(citiesAndText,BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    public void setStatusPanel(StatusPanel statusPanel) {
        this.statusPanel = statusPanel;
        statusPanel.setBounds(200, 0, getWidth(), 100);
        add(statusPanel,BorderLayout.NORTH);
    }


    public void setListener(WorldMapListener listener) {
        this.listener = listener;
        for (Card card : cities) {
            card.setListener(listener);
        }
    }

    public void setControlledArmiesAtThisCity(ArrayList<Army> controlledArmiesAtThisCity) {
        if (armiesPanel != null) {
            armiesPanel.removeAll();
        }
        armiesPanel = new ArmiesPanel(defendingArmies, controlledArmiesAtThisCity, listener);
        armiesPanel.setBackground(Color.ORANGE);
        add(armiesPanel,BorderLayout.SOUTH);
        armiesPanel.revalidate();
        armiesPanel.repaint();

    }

    public ArmiesPanel getArmiesPanel() {
        return armiesPanel;
    }


    public JPanel getCityCards() {
        return cityCards;
    }


    public void updateCitiesCards() {
        if (cityCards != null) {
            cityCards.removeAll();
        }
        for (City city : game.getAvailableCities()) {
            Card cityCard = new Card(city);
            cityCard.removeAll();
            cityCard.setSize(500,800);
            ImageIcon icon = new ImageIcon("res/img/" + city.getName().toLowerCase() + "City.jpeg");
            icon = new ImageIcon(icon.getImage().getScaledInstance(cityCard.getWidth()+100, cityCard.getHeight()+100, Image.SCALE_DEFAULT));
            cityCard.setIcon(icon);
            cityCard.revalidate();
            cityCard.repaint();
            cityCards.add(cityCard);
            cities.add(cityCard);
            cityCard.setListener(listener);
        }
        cityCards.revalidate();
        cityCards.repaint();

    }
}