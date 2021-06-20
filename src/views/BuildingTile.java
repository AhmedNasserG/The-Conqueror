package views;

import buildings.*;
import listeners.BuildingTileListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BuildingTile extends JLayeredPane implements MouseListener {
    private Building building;
    private JLabel buildingImg;
    private JLabel buildingName;
    private JLabel buildingLevel;
    private BuildingTileListener listener;

    public BuildingTile() {
        super();
        setLayout(null);
        addMouseListener(this);
        buildingImg = new JLabel();
        buildingName = new JLabel("", SwingConstants.CENTER);
        buildingLevel = new JLabel("", SwingConstants.CENTER);

        buildingImg.setBounds(0, 0, (400 / 3), (400 / 3));
        buildingName.setBounds(0, (400 / 3) - 30, (400 / 3), 15);
        buildingLevel.setBounds(0, (400 / 3) - 15, (400 / 3), 15);


        buildingName.setOpaque(true);
        buildingName.setBackground(Color.BLACK);
        buildingName.setForeground(Color.white);

        buildingLevel.setOpaque(true);
        buildingLevel.setBackground(Color.BLACK);
        buildingLevel.setForeground(Color.white);

        setSize((400 / 3), (400 / 3));
        ImageIcon icon = new ImageIcon("res/img/grass.png");
        ImageIcon resizedIcon = new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
        buildingImg.setIcon(resizedIcon);
        add(buildingImg, Integer.valueOf(0));
    }

    public BuildingTile(Building building) {
        this();
        setBuilding(building);
    }


    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
        buildingImg.setIcon(getBuildingIcon(building));
        buildingName.setText(building.getBuildingName());
        buildingLevel.setText("Level " + building.getLevel());
        add(buildingName, Integer.valueOf(1));
        add(buildingLevel, Integer.valueOf(1));
    }

    public ImageIcon getBuildingIcon(Building building) {
        ImageIcon icon = new ImageIcon("res/img/" + building.getBuildingName() + ".png");
        return new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        listener.onTileClicked(this.building);
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

    public void setListener(BuildingTileListener listener) {
        this.listener = listener;
    }
}

