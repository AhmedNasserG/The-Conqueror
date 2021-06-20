package views;

import javax.swing.*;
import java.awt.*;

public class ArmiesPanel extends JPanel{

    public ArmiesPanel(){
        super();
        this.setSize(new Dimension(200,100));
        for(int i =150;i>=0;i--){
            JButton armyButton = new JButton("Army" + i);
            armyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(armyButton);

        }
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

        public static void main(String[] args){
            JFrame armyFrame = new JFrame();
            JScrollPane scroll = new JScrollPane(new ArmiesPanel());
            armyFrame.setContentPane(scroll);
            armyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            armyFrame.setSize((400 / 3), (400 / 3));
            armyFrame.setVisible(true);
        }



}
