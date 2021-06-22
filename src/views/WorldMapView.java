package views;


import listeners.CardListener;
import listeners.CityViewListener;
import listeners.WorldMapListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WorldMapView extends Frame {
    private StatusPanel statusPanel;
    private JPanel citiesAndText;
    private ArrayList<JButton> buttons;
    private JPanel cityButtons;
    private ArrayList<Army> armiesArray;
   // private CityViewListener listener;
    private WorldMapListener worldMapListener;

public WorldMapView(){
    this.setVisible(true);
    this.setLayout(null);
    //TODO: intiate army Button: to add a unit from the defending army(and controlled armies)
    // to your attacking. The army must not be idle.
    //TODO: when a city is clicked options to setTarget or attack or lay siege
    // TODO: edit and update the status panel
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
    armiesArray = new ArrayList<>();
    for(int i=0;i<15;i++){
        armiesArray.add(new Army("army" +i));
    }


    this.add(statusPanel);
    this.add(citiesAndText);

    this.revalidate();
    this.repaint();
}

    public static void main(String[] args) {
        new WorldMapView();
    }


    public void setListener(WorldMapListener worldMapListener) {
        this.worldMapListener = worldMapListener;
        ArmiesPanel armiesPanel = new ArmiesPanel(armiesArray, worldMapListener);
        JScrollPane pane = new JScrollPane(armiesPanel);
        pane.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane);
        for(JButton button: buttons)
        {
            button.addActionListener(worldMapListener);
        }
    }
}
