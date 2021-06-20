package views;


import javax.swing.*;
import java.awt.*;

public class WorldMapView extends Frame {
    StatusPanel statusPanel;
    JPanel citiesAndText;
    JPanel cityButtons;
    JPanel armiesPanel;

public WorldMapView(){
    this.setVisible(true);
    this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
    statusPanel= new StatusPanel();
    statusPanel.setBounds(0, 0, getWidth() - 100, 100);

    citiesAndText = new JPanel();
    citiesAndText.setLayout(new BoxLayout(citiesAndText, BoxLayout.Y_AXIS));
    JLabel chooseLabel = new JLabel("Choose the city you want to view");
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

    armiesPanel = new JPanel(new GridLayout(1,2));

    JPanel idleArmies = new JPanel(new GridLayout(0,5));
    idleArmies.setBorder(BorderFactory.createTitledBorder("IDLE Armies stehet hier "));
    //Testing the scroll pane
    for(int i =0;i<50;i++){
        idleArmies.add(new JButton("Army" + i));
    }
    JScrollPane idlePane = new JScrollPane(idleArmies);


    JPanel otherArmies = new JPanel(new GridLayout(0,5));
    otherArmies.setBorder(BorderFactory.createTitledBorder("BESIEGING and MARCHING Armies stehet hier "));
    //Testing the scroll pane
    for(int i =50;i>=0;i--){
        otherArmies.add(new JButton("Army" + i));
    }
    JScrollPane otherPane = new JScrollPane(otherArmies);

    armiesPanel.add(idleArmies);
    armiesPanel.add(otherArmies);


    this.add(statusPanel);
    this.add(citiesAndText);
    this.add(armiesPanel);

    this.revalidate();
    this.repaint();
}

    public static void main(String[] args) {
        new WorldMapView();
    }

}
