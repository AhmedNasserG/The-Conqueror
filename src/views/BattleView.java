package views;

import engine.City;
import listeners.CardListener;
import units.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BattleView extends Frame {


    private JPanel leftSidePanel;
    private JTextArea battleLog;
    private JPanel unitInfoPanel;
    private JPanel buttonsPanel;

    private JPanel battlePanel;
    private StatusPanel statusPanel;


    private UnitsPanel playerUnitsPanel;
    private UnitsPanel targetUnitsPanel;

    private String battleMode;
    private JLabel battleResultsDisplay;

    private JButton startAutoResolveBtn;
    private JButton startManualAttackBtn;

    private JButton exitBattleViewBtn;

    private CardListener listener;

    static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();


    private Army playerArmy;
    private Army targetArmy;


    public BattleView(String battleMode, Army playerArmy, City targetCity, CardListener listener) {
        super();
        this.listener = listener;
        this.playerArmy = playerArmy;
        this.targetArmy = targetCity.getDefendingArmy();
        this.battleMode = battleMode;
        this.setLayout(new BorderLayout());

        this.battlePanel = new JPanel();
        initLeftPanel();
        initBattlePanel();


        add(battlePanel, BorderLayout.CENTER);
        add(leftSidePanel, BorderLayout.WEST);



        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void initLeftPanel() {
        leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BorderLayout());


        buttonsPanel = new JPanel();
        startAutoResolveBtn = new JButton("START AUTORESOLVE");
        startAutoResolveBtn.setActionCommand("START_AUTORESOLVE");
        startManualAttackBtn = new JButton("ATTACK");
        startManualAttackBtn.setActionCommand("START_MANUAL_ATTACK");
        exitBattleViewBtn = new JButton("Go Back");
        exitBattleViewBtn.setActionCommand("EXIT_BATTLE_VIEW");

        buttonsPanel.setPreferredSize(new Dimension(leftSidePanel.getWidth(), leftSidePanel.getHeight() / 4));

        if (battleMode.equals("MANUAL ATTACK")) {
            buttonsPanel.add(startManualAttackBtn);
        } else {
            buttonsPanel.add(startAutoResolveBtn);
        }

        exitBattleViewBtn.setEnabled(false);
        buttonsPanel.add(exitBattleViewBtn);

        battleLog = new JTextArea("",18, 50);
        battleLog.setEditable(false);
        battleLog.setBorder(BorderFactory.createTitledBorder("BATTLE LOG"));
        battleLog.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));


        unitInfoPanel = new JPanel();
        unitInfoPanel.setBorder(BorderFactory.createTitledBorder("UNIT INFO"));
        unitInfoPanel.setPreferredSize(new Dimension(battleLog.getWidth(), SCREENSIZE.height * 5 / 13));


        leftSidePanel.add(unitInfoPanel, BorderLayout.NORTH);
        leftSidePanel.add(buttonsPanel, BorderLayout.CENTER);
        leftSidePanel.add(new JScrollPane(battleLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.SOUTH);
    }

    public void initBattlePanel() {
        this.battleResultsDisplay = new JLabel();

        battlePanel.setLayout(new BorderLayout());

        playerUnitsPanel = new UnitsPanel("player", playerArmy, listener);
        targetUnitsPanel = new UnitsPanel("target", targetArmy, listener);

        battlePanel.add(new JScrollPane(targetUnitsPanel), BorderLayout.NORTH);
        battlePanel.add(new JScrollPane(playerUnitsPanel), BorderLayout.SOUTH);
        battlePanel.add(battleResultsDisplay, BorderLayout.CENTER);

        battlePanel.revalidate();
        battlePanel.repaint();
    }

    public JLabel getBattleResultsDisplay() {
        return battleResultsDisplay;
    }

    public JPanel getBattlePanel() {
        return battlePanel;
    }

    public JButton getStartAutoResolveBtn() {
        return startAutoResolveBtn;
    }

    public void setPlayerArmy(Army playerArmy) {
        this.playerArmy = playerArmy;
    }

    public void setTargetArmy(Army targetArmy) {
        this.targetArmy = targetArmy;
    }

    public JTextArea getBattleLog() {
        return battleLog;
    }

    public JPanel getUnitInfoPanel() {
        return unitInfoPanel;
    }

    public UnitsPanel getPlayerUnitsPanel() {
        return playerUnitsPanel;
    }

    public UnitsPanel getTargetUnitsPanel() {
        return targetUnitsPanel;
    }

    public JButton getStartManualAttackBtn() {
        return startManualAttackBtn;
    }

    public JButton getExitBattleViewBtn() {
        return exitBattleViewBtn;
    }

    public void setStatusPanel(StatusPanel statusPanel) {
        this.statusPanel = statusPanel;
        statusPanel.getEndTurnButton().setVisible(false);
        add(statusPanel, BorderLayout.NORTH);
    }

    public String getBattleMode() {
        return battleMode;
    }

}
