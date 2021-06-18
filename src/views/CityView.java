package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CityView extends JFrame {
    private String playerName;
    private int currentTurnCount;
    private double treasury;
    private double food;

    public CityView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        this.setLayout(null);


        ImageIcon worldMapIcon = new ImageIcon("res/img/map.png");
        JLabel worldMapLabel = new JLabel(new ImageIcon(worldMapIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));


        worldMapLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("CLICKED");
            }
        });

        worldMapLabel.setBackground(Color.DARK_GRAY);

        StatusPanel statusPanel = new StatusPanel(playerName, 50, 100, 6000);

        statusPanel.setBounds(0, 0, screenSize.width - 100, 100);
        worldMapLabel.setBounds(screenSize.width - 100, 0, 100, 100);

        add(statusPanel);
        add(worldMapLabel);
        setVisible(true);
    }

    class StatusPanel extends JPanel {
        public StatusPanel(String playerName, int turnCount, int goldCount, int foodCount) {
            FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
            layout.setHgap(20);
            this.setLayout(layout);
            this.setBackground(Color.CYAN);

            JLabel playerNameLabel = new JLabel(playerName);
            JLabel turnCountLabel = new JLabel("Turns : " + turnCount);
            JLabel goldCountLabel = new JLabel(goldCount + "");
            JLabel foodCountLabel = new JLabel(foodCount + "");

            ImageIcon goldIcon = new ImageIcon("res/img/coin.png");
            ImageIcon foodIcon = new ImageIcon("res/img/food.png");

            goldCountLabel.setIcon(new ImageIcon(goldIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
            foodCountLabel.setIcon(new ImageIcon(foodIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

            add(playerNameLabel);
            add(turnCountLabel);
            add(goldCountLabel);
            add(foodCountLabel);

        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCurrentTurnCount(int currentTurnCount) {
        this.currentTurnCount = currentTurnCount;
    }

    public void setTreasury(double treasury) {
        this.treasury = treasury;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public static void main(String[] args) {
        new CityView();
    }
}
