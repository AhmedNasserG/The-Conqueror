package views;

import buildings.Building;
import exceptions.NotEnoughGoldException;
import listeners.CardListener;
import units.Army;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends JLayeredPane implements MouseListener {
    private JLabel img;
    private JLabel topLabel;
    private JLabel bottomLabel;
    private String whereToBuild;
    private Building building;
    private Unit unit;
    private Army army;
    private CardListener listener;

    public Card() {
        super();
        addMouseListener(this);
        img = new JLabel();
        topLabel = new JLabel("", SwingConstants.CENTER);
        bottomLabel = new JLabel("", SwingConstants.CENTER);

        img.setBounds(0, 0, (400 / 3), (400 / 3));
        topLabel.setBounds(0, (400 / 3) - 30, (400 / 3), 15);
        bottomLabel.setBounds(0, (400 / 3) - 15, (400 / 3), 15);


        topLabel.setOpaque(true);
        topLabel.setBackground(Color.BLACK);
        topLabel.setForeground(Color.white);

        bottomLabel.setOpaque(true);
        bottomLabel.setBackground(Color.BLACK);
        bottomLabel.setForeground(Color.white);

        setSize((400 / 3), (400 / 3));
        ImageIcon icon = new ImageIcon("res/img/grass.png");
        ImageIcon resizedIcon = new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
        img.setIcon(resizedIcon);
        add(img, Integer.valueOf(0));
    }

    public Card(Building building) {
        this();
        this.building = building;
        img.setIcon(getIcon(building));
        topLabel.setText(building.getBuildingName());
        bottomLabel.setText("Level " + building.getLevel());
        add(topLabel, Integer.valueOf(1));
        add(bottomLabel, Integer.valueOf(1));
    }

    public Card(Building building, String whereToBuild) {
        this();
        this.whereToBuild = whereToBuild;
        this.building = building;
        img.setIcon(getIcon(building));
        topLabel.setText(building.getBuildingName());
        bottomLabel.setText("Cost " + building.getCost());
        add(topLabel, Integer.valueOf(1));
        add(bottomLabel, Integer.valueOf(1));
    }

    public Card(Unit unit) {
        this();
        this.unit = unit;
        img.setIcon(getIcon(unit));
        topLabel.setText(unit.getUnitName());
        bottomLabel.setText("Level " + unit.getLevel());
        add(topLabel, Integer.valueOf(1));
        add(bottomLabel, Integer.valueOf(1));
    }

    public Card(Army army) {
        this();
        this.army = army;
        img.setIcon(getIcon(army));
        topLabel.setText("Army");
        bottomLabel.setText(army.getCurrentStatus().toString());
        add(topLabel, Integer.valueOf(1));
        add(bottomLabel, Integer.valueOf(1));
    }

    public ImageIcon getIcon(Building building) {
        ImageIcon icon = new ImageIcon("res/img/" + building.getBuildingName() + ".png");
        return new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
    }

    public ImageIcon getIcon(Unit unit) {
        ImageIcon icon = new ImageIcon("res/img/" + unit.getUnitName() + ".png");
        return new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
    }

    public ImageIcon getIcon(Army army) {
        ImageIcon icon = new ImageIcon("res/img/army.png");
        return new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
    }

    public void setListener(CardListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (building != null) {
            try {
                listener.onBuildingCardClicked(this.building, whereToBuild);
            } catch (NotEnoughGoldException notEnoughGoldException) {
                notEnoughGoldException.printStackTrace();
            }
        } else if (unit != null) {
            listener.onUnitCardClicked(this.unit);
        } else if (army != null) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
