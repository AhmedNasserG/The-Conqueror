package views;

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

    private UnitsPanel playerUnitsPanel;
    private UnitsPanel targetUnitsPanel;

    private String battleMode;
    private JLabel battleResultsDisplay;

    private JButton startAutoResolveBtn;
    private JButton startManualAttackBtn;

    private CardListener listener;

    static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();


    private Army playerArmy;
    private Army targetArmy;



    public BattleView(String battleMode, Army playerArmy, Army targetArmy, CardListener listener) {
        super();
        this.listener = listener;
        this.playerArmy = playerArmy;
        this.targetArmy = targetArmy;
        this.battleMode = battleMode;
        this.setLayout(new BorderLayout());

        this.battlePanel = new JPanel();
        initLeftPanel();
        initBattlePanel();


        add(battlePanel, BorderLayout.CENTER);
        add(leftSidePanel, BorderLayout.WEST);
        add(new StatusPanel("AUTO RESOLVE"), BorderLayout.NORTH);


        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void initLeftPanel() {
        leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BorderLayout());


        buttonsPanel = new JPanel();
        startAutoResolveBtn = new JButton("START AUTORESOLVE");
        startManualAttackBtn = new JButton("ATTACK");
        buttonsPanel.setPreferredSize(new Dimension(leftSidePanel.getWidth(), leftSidePanel.getHeight()/4));

        if(battleMode.equals("MANUAL ATTACK")) {
            buttonsPanel.add(startManualAttackBtn);
        }
        else {
            buttonsPanel.add(startAutoResolveBtn);
        }


        battleLog = new JTextArea(25, 50);
        battleLog.setEditable(false);
        battleLog.setBorder(BorderFactory.createTitledBorder("BATTLE LOG"));
        battleLog.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));


        unitInfoPanel = new JPanel();
        unitInfoPanel.setBorder(BorderFactory.createTitledBorder("UNIT INFO"));
        unitInfoPanel.setPreferredSize(new Dimension(battleLog.getWidth(), SCREENSIZE.height*5 / 13));


        leftSidePanel.add(unitInfoPanel, BorderLayout.NORTH);
        leftSidePanel.add(buttonsPanel, BorderLayout.CENTER);
        leftSidePanel.add(new JScrollPane(battleLog), BorderLayout.SOUTH);
    }

    public void initBattlePanel() {
        this.battleResultsDisplay = new JLabel();

        battlePanel.setLayout(new BorderLayout());

        playerUnitsPanel = new UnitsPanel("player", playerArmy, listener);
        targetUnitsPanel = new UnitsPanel("target", targetArmy, listener);

        battlePanel.add(targetUnitsPanel, BorderLayout.NORTH);
        battlePanel.add(playerUnitsPanel, BorderLayout.SOUTH);
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
