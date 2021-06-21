package views;

import listeners.CardListener;
import units.Army;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UnitsPanel extends JPanel {



    public UnitsPanel(String armyOwner, Army army, CardListener listener){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setPreferredSize(new Dimension(screenSize.width, screenSize.height/7));

        this.setLayout(new GridLayout(0,10));

        if(armyOwner.equals("player")) this.setBorder(BorderFactory.createTitledBorder("PLAYER UNITS"));
        else this.setBorder(BorderFactory.createTitledBorder("ENEMY UNITS"));

        ArrayList<Unit> units = army.getUnits();
        for(Unit u : units){
            Card c = new Card(u);
            c.setListener(listener);
            this.add(c);
        }
    }
}
