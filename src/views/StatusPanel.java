package views;

import engine.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel {

    private Game game;
    private JLabel playerNameLabel;
    private JLabel turnCountLabel;
    private JLabel goldCountLabel;
    private JLabel foodCountLabel;
    private newButton endTurnButton;
    private ActionListener listener;
    private Color TRANSPARENT_WHITE = new Color(255,255,255,150);
    private final Font BOLD_LABEL = new Font(Font.MONOSPACED, Font.BOLD, 22);

    public StatusPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
        layout.setHgap(20);
        this.setLayout(layout);
        this.setBackground(Color.lightGray);

        playerNameLabel = new JLabel();
        turnCountLabel = new JLabel();
        goldCountLabel = new JLabel();
        foodCountLabel = new JLabel();
        endTurnButton = new newButton("End Turn");

        ImageIcon goldIcon = new ImageIcon("res/img/coin.png");
        ImageIcon foodIcon = new ImageIcon("res/img/food.png");

        goldCountLabel.setIcon(new ImageIcon(goldIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        foodCountLabel.setIcon(new ImageIcon(foodIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

        playerNameLabel.setFont(BOLD_LABEL);
        turnCountLabel.setFont(BOLD_LABEL);
        goldCountLabel.setFont(BOLD_LABEL);
        foodCountLabel.setFont(BOLD_LABEL);
        endTurnButton.setFont(BOLD_LABEL);
        playerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        goldCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        foodCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(playerNameLabel);
        add(turnCountLabel);
        add(goldCountLabel);
        add(foodCountLabel);
        add(endTurnButton);
        this.setOpaque(false);
        this.setBackground(TRANSPARENT_WHITE);
    }

    public StatusPanel(String battleMode) {
        this();

        JLabel modeLabel = new JLabel(battleMode);
        modeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));

        this.add(modeLabel);
        modeLabel.setBorder(BorderFactory.createEmptyBorder(0, 700, 0, 0));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

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

    public newButton getEndTurnButton() {
        return endTurnButton;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
        endTurnButton.addActionListener(listener);
    }
}
