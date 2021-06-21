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
    private JPanel battleActionPanel;
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
        GridLayout gl = new GridLayout(1,2);
        gl.setHgap(10);
        buttonsPanel.setLayout(gl);
        buttonsPanel.add(startAutoResolveBtn);
        buttonsPanel.add(startManualAttackBtn);


        battleLog = new JTextArea(25, 50);
        battleLog.setEditable(false);
        battleLog.setBorder(BorderFactory.createTitledBorder("BATTLE LOG"));
        battleLog.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));


        unitInfoPanel = new JPanel();
        unitInfoPanel.setLayout(new BoxLayout(unitInfoPanel, BoxLayout.Y_AXIS));
        unitInfoPanel.setPreferredSize(new Dimension(battleLog.getWidth(), SCREENSIZE.height*5 / 13));
        Card c = new Card(new Archer(1));
        c.setAlignmentX(Component.CENTER_ALIGNMENT);
        unitInfoPanel.add(c);
        c.setOpaque(true);



        leftSidePanel.add(unitInfoPanel, BorderLayout.NORTH);
        leftSidePanel.add(buttonsPanel, BorderLayout.CENTER);
        leftSidePanel.add(new JScrollPane(battleLog), BorderLayout.SOUTH);
    }

    public void initBattlePanel() {
        this.battlePanel = new JPanel();
        battlePanel.setBackground(Color.orange);


        battlePanel.setLayout(new BorderLayout());

        playerUnitsPanel = new UnitsPanel("player", playerArmy, listener);
        targetUnitsPanel = new UnitsPanel("target", targetArmy, listener);



        battlePanel.add(targetUnitsPanel, BorderLayout.NORTH);
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

    public void setBattleLog(JTextArea battleLog) {
        this.battleLog = battleLog;
    }

    public Army getPlayerArmy() {
        return playerArmy;
    }

    public Army getTargetArmy() {
        return targetArmy;
    }

    public JPanel getUnitInfoPanel() {
        return unitInfoPanel;
    }

    public void setUnitInfoPanel(JPanel unitInfoPanel) {
        this.unitInfoPanel = unitInfoPanel;
    }

    public UnitsPanel getPlayerUnitsPanel() {
        return playerUnitsPanel;
    }

    public void setPlayerUnitsPanel(UnitsPanel playerUnitsPanel) {
        this.playerUnitsPanel = playerUnitsPanel;
    }

    public UnitsPanel getTargetUnitsPanel() {
        return targetUnitsPanel;
    }

    public void setTargetUnitsPanel(UnitsPanel targetUnitsPanel) {
        this.targetUnitsPanel = targetUnitsPanel;
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
