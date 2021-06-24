package views;

import listeners.CardListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArmiesPanel extends JPanel {

    public ArmiesPanel(ArrayList<Army> defendingArmies,ArrayList<Army> controlledArmies, CardListener listener) {
        super();
        JPanel defendingArmyPanel = new JPanel();
        defendingArmyPanel.setLayout(new BoxLayout(defendingArmyPanel,BoxLayout.Y_AXIS));
        JPanel controlledArmiesPanel = new JPanel();
        controlledArmiesPanel.setLayout(new BoxLayout(controlledArmiesPanel,BoxLayout.Y_AXIS));
        for (Army army : defendingArmies) {
            Card armyCard = new Card(army);
            armyCard.setListener(listener);
            armyCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            defendingArmyPanel.add(armyCard);
            defendingArmyPanel.add(Box.createVerticalStrut(10));
        }
        for (Army army : controlledArmies) {
            Card armyCard = new Card(army);
            armyCard.setListener(listener);
            armyCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            controlledArmiesPanel.add(armyCard);
            controlledArmiesPanel.add(Box.createVerticalStrut(10));
        }
        defendingArmyPanel.setBorder(BorderFactory.createTitledBorder("Defending Armies"));
        controlledArmiesPanel.setBorder(BorderFactory.createTitledBorder("Controlled Armies"));
        this.add(new JScrollPane(defendingArmyPanel));
        this.add(new JScrollPane(controlledArmiesPanel));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public static void main(String[] args) {
        JFrame armyFrame = new JFrame();
        ArrayList<Army> defendingArmies = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            defendingArmies.add(new Army("Def " + i));

        ArrayList<Army> controlledArmies = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            controlledArmies.add(new Army("Def " + i));

        JPanel scroll = new ArmiesPanel(defendingArmies,controlledArmies,null);
        armyFrame.add(scroll);
        armyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        armyFrame.setSize((400 / 3), (400 / 3));
        armyFrame.setVisible(true);
    }


}
