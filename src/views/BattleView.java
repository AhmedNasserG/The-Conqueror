package views;

import units.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BattleView extends Frame {
    private JTextArea battleLog;
    private JPanel leftSidePanel;
    private JPanel battlePanel;
    private UnitsPanel playerUnitsPanel;
    private UnitsPanel targetUnitsPanel;
    private String battleMode;
    private JLabel battleResultsDisplay;

    private JButton nextMoveBtn;

    private Army playerArmy;
    private Army targetArmy;

    public void setPlayerArmy(Army playerArmy) {
        this.playerArmy = playerArmy;
    }

    public void setTargetArmy(Army targetArmy) {
        this.targetArmy = targetArmy;
    }

    public BattleView(String battleMode, Army playerArmy, Army targetArmy) {
        super();
        this.playerArmy = playerArmy;
        this.targetArmy = targetArmy;
        this.battleMode = battleMode;
        this.setLayout(new BorderLayout());

//        leftSidePanel.setBackground(Color.black);
        initBattleLog();
        initBattlePanel();

        add(battlePanel, BorderLayout.CENTER);
        add(leftSidePanel, BorderLayout.WEST);
        add(new StatusPanel("AUTO RESOLVE"), BorderLayout.NORTH);


        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void initBattleLog() {
        leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BorderLayout());

        battlePanel = new JPanel();

        battleLog = new JTextArea(20, 40);
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

    public void initBattlePanel() {
        this.battlePanel = new JPanel();
        battlePanel.setBackground(Color.orange);

        nextMoveBtn = new JButton("NEXT MOVE");
        battlePanel.add(nextMoveBtn);


        battlePanel.setLayout(new BorderLayout());

        playerUnitsPanel = new UnitsPanel("player", playerArmy);
        battlePanel.add(nextMoveBtn, BorderLayout.CENTER);
        battlePanel.add(playerUnitsPanel, BorderLayout.SOUTH);
    }

    public JLabel getBattleResultsDisplay() {
        return battleResultsDisplay;
    }

    public void setBattleResultsDisplay(JLabel battleResultsDisplay) {
        this.battleResultsDisplay = battleResultsDisplay;
    }

    public JPanel getBattlePanel() {
        return battlePanel;
    }

    public void setBattlePanel(JPanel battlePanel) {
        this.battlePanel = battlePanel;
    }

    public JButton getNextMoveBtn() {
        return nextMoveBtn;
    }


    public static void main(String[] args) throws IOException {
//        Game g = new Game("Omar", "Cairo");
//        Player p = g.getPlayer();
//
//        Army a = new Army("Cairo");
//        Army b = new Army("Sparta");
//
//        Archer u1 = new Archer(1, 5, 2.0, 2.0, 2.0);
//        Cavalry u2 = new Cavalry(1, 5, 2.0, 2.0, 2.0);
//        Infantry u3 = new Infantry(1, 5, 2.0, 2.0, 2.0);
//
//        u1.setParentArmy(a);
//        u2.setParentArmy(a);
//        u3.setParentArmy(a);
//
//        ArrayList<Unit> list = new ArrayList<>();
//        list.add(u1); list.add(u2); list.add(u3);
//
//        a.setUnits(list);
//
//        for(Unit u : a.getUnits()){
//            System.out.println(u.getUnitName());
//        }
//
//

//        new BattleView("AUTO RESOLVE");

//        JFrame frame = new JFrame();
//        frame.add(new Card(new Archer(1, 5, 2.0, 2.0, 2.0)));
//        frame.setVisible(true);
    }




}
