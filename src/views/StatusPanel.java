package views;

import engine.Game;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    private Game game;
    private JLabel playerNameLabel;
    private JLabel turnCountLabel;
    private JLabel goldCountLabel;
    private JLabel foodCountLabel;
    private JButton endTurnButton;

    public StatusPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
        layout.setHgap(20);
        this.setLayout(layout);
        this.setBackground(Color.lightGray);

        playerNameLabel = new JLabel();
        turnCountLabel = new JLabel();
        goldCountLabel = new JLabel();
        foodCountLabel = new JLabel();
        endTurnButton = new JButton("End Turn");

        ImageIcon goldIcon = new ImageIcon("res/img/coin.png");
        ImageIcon foodIcon = new ImageIcon("res/img/food.png");

        goldCountLabel.setIcon(new ImageIcon(goldIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        foodCountLabel.setIcon(new ImageIcon(foodIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

        add(playerNameLabel);
        add(turnCountLabel);
        add(goldCountLabel);
        add(foodCountLabel);
        add(endTurnButton);
    }

    public StatusPanel(String battleMode) {
        this();

        JLabel modeLabel = new JLabel(battleMode);
        modeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));

        this.add(modeLabel);
        modeLabel.setBorder(BorderFactory.createEmptyBorder(0, 700, 0, 0));

    }

    public void updateStatusPanel() {
        if (game != null) {
            playerNameLabel.setText(game.getPlayer().getName());
            turnCountLabel.setText("Turn: " + game.getCurrentTurnCount());
            goldCountLabel.setText(game.getPlayer().getTreasury() + "");
            foodCountLabel.setText(game.getPlayer().getFood() + "");
        } else {
            System.out.println("Game Is Null In Status Panel");
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
