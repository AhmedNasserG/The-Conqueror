package views;

import javax.swing.*;
import java.awt.*;

public class BattleView extends JFrame {
    private JTextArea battleLog;
    private JPanel leftSidePanel;
    private JPanel centerPanel;
    public BattleView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);

        this.setLayout(new BorderLayout());

        leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BorderLayout());
        centerPanel = new JPanel();

        battleLog = new JTextArea(20,40);
        battleLog.setEditable(false);
        battleLog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        battleLog.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(battleLog);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("HI IM LOG\n\nIM BORING\n\nJUST LIKE NASSER\n\nI SUCK\n\n");
        }

        battleLog.setText(sb.toString());

        leftSidePanel.add(scrollPane, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(leftSidePanel, BorderLayout.WEST);

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }


    public static void main(String[] args) {
        new BattleView();
    }



}
