package views;

import units.Archer;
import units.Army;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UnitsPanel extends JPanel {

    public UnitsPanel(String armyOwner, Army army){
//        FlowLayout fl = new FlowLayout();
//        this.setPreferredSize(new Dimension(500, 500));
//        fl.setHgap(10);
//        this.setLayout(fl);
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(1,10));


        if(armyOwner.equals("player")) this.setBackground(new Color(131, 179, 105));
        else this.setBackground(new Color(110, 32, 32));

        ArrayList<Unit> units = army.getUnits();
        for(Unit u : units){
            Card c = new Card(u);
            this.add(new Card(u));
        }
    }
}
