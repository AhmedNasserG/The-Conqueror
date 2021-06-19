package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BattleView extends Frame {
    private JTextArea battleLog;
    private JPanel leftSidePanel;
    private JPanel battlePanel;

    public BattleView(){
        super();
        this.setLayout(new BorderLayout());

        initBattleLog();
        initBattlePanel("AUTO RESOLVE");

        add(battlePanel, BorderLayout.CENTER);
        leftSidePanel.setBackground(Color.black);
        add(leftSidePanel, BorderLayout.WEST);
        add(new StatusPanel(), BorderLayout.NORTH);

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void initBattleLog(){
        leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BorderLayout());

        battlePanel = new JPanel();

        battleLog = new JTextArea(20,40);
        battleLog.setEditable(false);
        battleLog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        battleLog.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            sb.append("MOVE #" + i + ": SALAH ATTACKED NASSER :(\n\n");
        }
        battleLog.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(battleLog);
        leftSidePanel.add(scrollPane, BorderLayout.SOUTH);
    }

    public void initBattlePanel(String attackMode){
        this.battlePanel = new JPanel();

        JLabel modeLabel = new JLabel(attackMode);
        modeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
        modeLabel.setBorder(BorderFactory.createEmptyBorder(25,350,0, 0));

        battlePanel.setLayout(new BorderLayout());
        battlePanel.add(modeLabel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new BattleView();
    }

}
