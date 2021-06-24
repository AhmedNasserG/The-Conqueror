package views;

import buildings.Building;
import engine.City;
import exceptions.NotEnoughGoldException;
import listeners.CardListener;
import units.Army;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Card extends JButton implements ActionListener {
    private JLayeredPane layeredPane;
    private JLabel img;
    private JLabel topLabel;
    private JLabel bottomLabel;
    private String whereToBuild;
    private Building building;
    private JButton initiateArmyButton;


    private Unit unit;
    private Army army;
    private City city;
    private CardListener listener;
    private boolean isEnemyUnit;


    public Card() {
        super();
        layeredPane = new JLayeredPane();
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setPreferredSize(new Dimension(150,10));
        this.setContentAreaFilled(false);
        addActionListener(this);
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

        setSize((500 / 3), (500 / 3));
        ImageIcon icon = new ImageIcon("res/img/grass.png");
        ImageIcon resizedIcon = new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));
        img.setIcon(resizedIcon);
        layeredPane.add(img, Integer.valueOf(0));
        add(layeredPane);
    }

    public Card(Building building) {
        this();
        this.building = building;
        img.setIcon(getIcon(building));
        topLabel.setText(building.getBuildingName());
        bottomLabel.setText("Level: " + building.getLevel());
        layeredPane.add(topLabel, Integer.valueOf(1));
        layeredPane.add(bottomLabel, Integer.valueOf(1));
    }

    public Card(Building building, String whereToBuild) {
        this();
        this.whereToBuild = whereToBuild;
        this.building = building;
        img.setIcon(getIcon(building));
        topLabel.setText(building.getBuildingName());
        bottomLabel.setText("Cost " + building.getCost());
        layeredPane.add(topLabel, Integer.valueOf(1));
        layeredPane.add(bottomLabel, Integer.valueOf(1));
    }

    public Card(Unit unit) {
        this();
        this.unit = unit;
        img.setIcon(getIcon(unit));
        topLabel.setText(unit.getUnitName());
        bottomLabel.setText("Level: " + unit.getLevel());
        layeredPane.add(topLabel, Integer.valueOf(1));
        layeredPane.add(bottomLabel, Integer.valueOf(1));
    }

    public Card(Unit unit, boolean isEnemy) {
        this(unit);
        this.isEnemyUnit = isEnemy;
    }

    public Card(Army army) {
        this();
        this.army = army;
        img.setIcon(getIcon(army));
        topLabel.setText("Army: "+ army.getCurrentLocation());
        bottomLabel.setText(army.getCurrentStatus().toString());
        layeredPane.add(topLabel, Integer.valueOf(1));
        layeredPane.add(bottomLabel, Integer.valueOf(1));
    }


    public Card(City city) {
        this();
        this.city = city;
        img.setIcon(getIcon(city));
        topLabel.setText(city.getName());
        bottomLabel.setText("");
        layeredPane.add(topLabel, Integer.valueOf(1));
        layeredPane.add(bottomLabel, Integer.valueOf(1));
    }

    private Icon getIcon(City city) {
        ImageIcon icon = new ImageIcon("res/img/" + city.getName().toLowerCase() + ".png");
        return new ImageIcon(icon.getImage().getScaledInstance((400 / 3), (400 / 3), Image.SCALE_DEFAULT));

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


    public Unit getUnit() {
        return unit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Unit Card")){
        listener.onNewUnitCardClicked(unit);
        }

        //TODO: Card needs refactor
        if (building != null) {
            try {
                listener.onBuildingCardClicked(this.building, whereToBuild);
            } catch (NotEnoughGoldException notEnoughGoldException) {
                notEnoughGoldException.printStackTrace();
            }
        } else if (unit != null) {
            if(!isEnemyUnit) listener.onFriendlyUnitCardClicked(this);
            else listener.onEnemyUnitCardClicked(this);
        } else if (army != null) {
            listener.onArmyCardClicked(this.army);

        }
        else if (city != null)
        {
            listener.onCityCardClicked(this.city);
        }


    }


    public static void main(String[] args) {
        JFrame newFrame = new Frame();
        Card Army = new Card(new Army("Cairo"));
        newFrame.add(Army);
        newFrame.setVisible(true);
    }
    public City getCity() {
        return city;
    }
}
