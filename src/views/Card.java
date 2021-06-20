package views;

import buildings.Building;
import listeners.CardListener;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends JLayeredPane implements MouseListener {
    private JLabel img;
    private JLabel name;
    private JLabel level;
    private Building building;
    private Unit unit;
    private CardListener listener;

    public Card(){
        super();
        addMouseListener(this);
        img = new JLabel();
        name = new JLabel("", SwingConstants.CENTER);
        level = new JLabel("", SwingConstants.CENTER);

        img.setBounds(0, 0, (400 / 3), (400 / 3));
        name.setBounds(0, (400 / 3) - 30, (400 / 3), 15);
        level.setBounds(0, (400 / 3) - 15, (400 / 3), 15);


        name.setOpaque(true);
        name.setBackground(Color.BLACK);
        name.setForeground(Color.white);

        level.setOpaque(true);
        level.setBackground(Color.BLACK);
        level.setForeground(Color.white);

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
        name.setText(building.getBuildingName());
        level.setText("Level " + building.getLevel());
        add(name, Integer.valueOf(1));
        add(level, Integer.valueOf(1));
    }

    public Card(Unit unit) {
        this();
        this.unit = unit;
        img.setIcon(getIcon(unit));
        name.setText(unit.getUnitName());
        level.setText("Level " + unit.getLevel());
        add(name, Integer.valueOf(1));
        add(level, Integer.valueOf(1));
    }

    public ImageIcon getIcon(Building building) {
        ImageIcon icon = new ImageIcon("res/img/" + building.getBuildingName() + ".png");
        return new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
    }

    public ImageIcon getIcon(Unit unit) {
        ImageIcon icon = new ImageIcon("res/img/" + unit.getUnitName() + ".png");
        return new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
    }

    public void setListener(CardListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (building != null) {
            listener.onCardClicked(this.building);
        }else if (unit != null) {
            listener.onCardClicked(this.unit);
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
