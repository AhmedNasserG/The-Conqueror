package views;

import listeners.CardListener;
import units.Army;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UnitsPanel extends JPanel {

    private String armyOwner;
    private Army army;

    private CardListener listener;

    static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public UnitsPanel(String armyOwner, Army army, CardListener listener){

        this.armyOwner = armyOwner;
        this.army = army;
        this.listener = listener;

        this.setPreferredSize(new Dimension(SCREENSIZE.width, SCREENSIZE.height/7));

        this.setLayout(new GridLayout(0,10));

        if(armyOwner.equals("player")) this.setBorder(BorderFactory.createTitledBorder("PLAYER UNITS"));
        else this.setBorder(BorderFactory.createTitledBorder("ENEMY UNITS"));

        ArrayList<Unit> units = army.getUnits();
        for(Unit u : units){
            if(u.getCurrentSoldierCount() == 0) continue;
            Card c;
            if(armyOwner.equals("player")) c = new Card(u,false);
            else c = new Card(u, true);
            c.setListener(listener);
            this.add(c);
        }
    }


    public void updatePanel(Army army){
        removeAll();

        ArrayList<Unit> units = army.getUnits();
        for(Unit u : units){
            if(u.getCurrentSoldierCount() == 0) continue;
            Card c;
            if(armyOwner.equals("player")) c = new Card(u,false);
            else c = new Card(u, true);
            c.setListener(listener);
            this.add(c);
        }

        revalidate();
        repaint();
    }

}
