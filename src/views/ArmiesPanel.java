package views;

import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArmiesPanel extends JPanel{

    public ArmiesPanel(ArrayList<Army> armiesArray){
        super();
        for(Army army: armiesArray){
            //TODO: change the button to a card
            JButton armyButton = new JButton(army.getCurrentLocation());
            armyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(armyButton);

        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

        public static void main(String[] args){
            JFrame armyFrame = new JFrame();
            JScrollPane scroll = new JScrollPane(new ArmiesPanel(new ArrayList<Army>()));
            armyFrame.setContentPane(scroll);
            armyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            armyFrame.setSize((400 / 3), (400 / 3));
            armyFrame.setVisible(true);
        }



}
