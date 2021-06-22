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
    private ArrayList<JButton> buttons;
    private JPanel cityButtons;
    private ArrayList<Army> armiesArray;
    private ArrayList<Army> defendingArmies;
    private ArrayList<Army> controlledArmies;

    private WorldMapListener listener;

public WorldMapView(Game game){
    this.setVisible(true);
    this.setLayout(null);

    //TODO: intiate army Button: to add a unit from the defending army(and controlled armies)
    // to your attacking. The army must not be idle. !!Should be in the cityView
    //Add this button to any defending army panel
    //TODO: relocateUnit choose a unit and choose an army to put it in the
    //TODO: a frame to choose army and choose a unit from that unit and choose the army to be gone to
    //comboboxes :)
    //TODO: when a city is clicked options to attack (the armies that are there already)
    // or lay siege
    //TODO: army pop up should have setTarget the uncontrolled cites
    // TODO: edit and update the status panel
    //TODO: unit pop that has count and status and so on
    //TODO: separate the defending from the controlled armies
    //TODO: city can't be under siege and attacked at the same time

    statusPanel= new StatusPanel();
    statusPanel.setBounds(0, 0, getWidth() , 100);

    citiesAndText = new JPanel();
    citiesAndText.setLayout(new BoxLayout(citiesAndText, BoxLayout.Y_AXIS));
    JLabel chooseLabel = new JLabel("View City");
    chooseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    chooseLabel.setFont(chooseLabel.getFont().deriveFont(Font.BOLD,23));
    citiesAndText.add(chooseLabel);
    citiesAndText.add(Box.createRigidArea(new Dimension(0, 50)));

    cityButtons = new JPanel();
    cityButtons.setLayout(new FlowLayout());
    buttons = new ArrayList<JButton>();
    JButton cairoButton = new JButton("Cairo");
    JButton romeButton = new JButton("Rome");
    JButton spartaButton = new JButton("Sparta");
    buttons.add(cairoButton);
    buttons.add(romeButton);
    buttons.add(spartaButton);

    cityButtons.add(cairoButton);
    cityButtons.add(romeButton);
    cityButtons.add(spartaButton);

    citiesAndText.add(cityButtons);
    citiesAndText.setBounds((getWidth() - 710) / 2, 280, 410, 410);

    //Testing the scroll pane
    //TODO: Remove this part and in the controller add the available armies
    /*armiesArray = new ArrayList<>();
    for(int i=0;i<15;i++){
        armiesArray.add(new Army("army" +i));
    }*/
    controlledArmies = game.getPlayer().getControlledArmies();
    defendingArmies = new ArrayList<>();
    for(City city: game.getPlayer().getControlledCities()){
        defendingArmies.add(city.getDefendingArmy());
    }

    this.add(statusPanel);
    this.add(citiesAndText);

    this.revalidate();
    this.repaint();
}




    public void setListener(WorldMapListener listener) {
        this.listener = listener;
        ArmiesPanel armiesPanel = new ArmiesPanel(defendingArmies,controlledArmies, listener);
        JScrollPane pane = new JScrollPane(armiesPanel);
        pane.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane);
        for(JButton button: buttons)
        {
            button.addActionListener(listener);
        }
    }
}
