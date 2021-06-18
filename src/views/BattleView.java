package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BattleView extends JFrame {
    private MyTextArea battleLog;
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

        battleLog = new MyTextArea(20,40);
        battleLog.setEditable(false);
        battleLog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        battleLog.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        battleLog.setBackground(new Color(1,1,1, (float) 0.01));;
        JScrollPane scrollPane = new JScrollPane(battleLog);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            sb.append("MOVE #" + i + ": SALAH ATTACKED NASSER :(\n\n");
        }
        battleLog.setText(sb.toString());

        leftSidePanel.add(scrollPane, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(leftSidePanel, BorderLayout.WEST);
        this.add(new StatusPanel(), BorderLayout.NORTH);

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
        new BattleView();
    }


    static class MyTextArea extends JTextArea {

        private Image img;

        public MyTextArea(int a, int b) {
            super(a,b);
            try{
                img = ImageIO.read(new File("res/img/nasserFaday7.jpeg"));
            } catch(IOException e) {
                System.out.println(e.toString());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img,0,0,null);
            super.paintComponent(g);
        }
    }
}
