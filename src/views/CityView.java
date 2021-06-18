package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CityView extends JFrame {
    private StatusPanel statusPanel;

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

        statusPanel = new StatusPanel();

        statusPanel.setBounds(0, 0, screenSize.width - 100, 100);
        worldMapLabel.setBounds(screenSize.width - 100, 0, 100, 100);

        add(statusPanel);
        add(worldMapLabel);
        setVisible(true);
    }


    public void setPlayerName(String playerName) {
        statusPanel.getPlayerNameLabel().setText(playerName);
    }

    public void setCurrentTurnCount(int currentTurnCount) {
        statusPanel.getTurnCountLabel().setText("Turn: " + currentTurnCount);
    }

    public void setTreasury(double treasury) {
        statusPanel.getGoldCountLabel().setText(treasury + "");
    }

    public void setFood(double food) {
        statusPanel.getFoodCountLabel().setText(food + "");
    }

    public static void main(String[] args) {
        new CityView();
    }
}
