package views;

import listeners.CardListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArmiesPanel extends JPanel {

    public ArmiesPanel(ArrayList<Army> armiesArray, CardListener listener) {
        super();
        for (Army army : armiesArray) {
            Card armyCard = new Card(army);
            armyCard.setListener(listener);
            armyCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(armyCard);
            this.add(Box.createVerticalStrut(10));


        }
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public static void main(String[] args) {
        JFrame armyFrame = new JFrame();
        ArrayList<Army> armies = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            armies.add(new Army("Army" + i));

        JScrollPane scroll = new JScrollPane(new ArmiesPanel(armies, null));
        armyFrame.setContentPane(scroll);
        armyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        armyFrame.setSize((400 / 3), (400 / 3));
        armyFrame.setVisible(true);
    }


}
