package views;


import listeners.CardListener;
import listeners.CityViewListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WorldMapView extends Frame {
    private StatusPanel statusPanel;
    private JPanel citiesAndText;
    private JPanel cityButtons;
    private ArrayList<Army> armiesArray;
    private CityViewListener listener;


public WorldMapView(){
    this.setVisible(true);
    this.setLayout(null);
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
    JButton cairoButton = new JButton("Cairo");
    JButton romeButton = new JButton("Rome");
    JButton spartaButton = new JButton("Sparta");
    cityButtons.add(cairoButton);
    cityButtons.add(romeButton);
    cityButtons.add(spartaButton);

    citiesAndText.add(cityButtons);
    citiesAndText.setBounds((getWidth() - 710) / 2, 280, 410, 410);

    //Testing the scroll pane
    armiesArray = new ArrayList<>();
    for(int i=0;i<5;i++){
        armiesArray.add(new Army("army" +i));
    }
    //^\\
//    ArmiesPanel armiesPanel = new ArmiesPanel(armiesArray);
//    armiesPanel.setListener(listener);
//    JScrollPane pane = new JScrollPane(armiesPanel);
//    pane.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);
//    pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


    this.add(statusPanel);
    this.add(citiesAndText);
//    this.add(pane);

    this.revalidate();
    this.repaint();
}

    public static void main(String[] args) {
        new WorldMapView();
    }

    public void setListener(CityViewListener listener) {
        this.listener = listener;
        ArmiesPanel armiesPanel = new ArmiesPanel(armiesArray, listener);
        JScrollPane pane = new JScrollPane(armiesPanel);
        pane.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane);
    }
}
