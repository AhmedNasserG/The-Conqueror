package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class HelpView extends Frame implements ActionListener {
    private final Font BOLD_LABEL = new Font(Font.MONOSPACED, Font.BOLD, 30);
    private Color TRANSPARENT_WHITE = new Color(0,0,0,70);

    public HelpView() {
        setLayout(new FlowLayout());
        setBackground("res/backgrounds/help.jpg");
        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel,BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea(20,100);
        textArea.setText("Intro\n\n\n" +
                "A single player turn-based empire building game. A player\n" +
                "initially chooses one historical city to start his empire with. The goal is to conquer the whole\n" +
                "world by taking control over every other city under a certain amount of turns. In order to\n" +
                "achieve this goal, the player have the option of building various types of building in any city he\n" +
                "has control over and also build armies in order to conquer other cities.\n\n\n" +
                "Winning/Loosing\n\n\n" +
                "The player will win if he managed to conquer all cities available in the game under the deter-\n" +
                "mined amounts of turns. If these turns passed and the player did not achieve this goal, this is considered a loss \n\n\n"+
                "Conquering a city : \n\n\n" +
                "Each city has an army that defends it from conquerors. The player needs to build a powerful\n" +
                "army and defeat the defending army in order to take control over the city. Once the attacking\n" +
                "army reaches the city, it can either lay siege on the target city trying to starve them out or\n" +
                "directly attack the defending army. If the player chooses to besiege the city, the defending\n" +
                "army will gradually lose soldiers each turn the city is under siege. However, the player can\n" +
                "only besiege a city for a max of three turns. During these three turns, the player can choose\n" +
                "to break the siege and leave the city or directly fight the defending army. If these three turns\n" +
                "passed, the player has to fight the defending army in a decisive battle. When the player engages\n" +
                "the defending army in a battle, he can choose to either automatically resolves the battle or\n" +
                "he manually commands his units during it. In auto resolve mode, a random unit from the\n" +
                "attacking army will attack another random unit from the defending army then, same action\n" +
                "happens but this time the defending army will be the attacker and so on till one of the two\n" +
                "armies is completely destroyed. In all cases, only the attacked unit receives damage and have\n" +
                "some of its soldiers lost during the attack. The attacking unit does not receive any damage\n" +
                "in the process. In the manual mode, the battle starts by the player choosing one of his units\n" +
                "to attack another unit in the defending army followed by a random unit from the defending \n" +
                "army attack another random unit from the player’s army and so on till one of the two armies is\n" +
                "destroyed. Once the city’s defending army is defeated, the player takes control over it and can.\n\n\n"+
                "Available resources\n\n\n" +
                "Throughout his journey of conquering the world, the player has two major resources that he\n" +
                "can spend in order to achieve his goal namely gold and food.\n" +
                "1. Gold is the main resource needed to build or upgrade any building and also to recruit\n" +
                "units. Gold will also be used to maintain and upkeep any army the player controls. The\n" +
                "player has to pay a certain amount of gold each turn for this purpose. If the player does\n" +
                "not have enough gold to maintain/upkeep his army, soldiers in his armies will gradually\n" +
                "lose faith and leave the army each turn until the player has enough gold to maintain his\n" +
                "army again.\n" +
                "2. Food, on the other hand, is used to keep the soldiers in the player’s army alive.\n" +
                "All soldiers consume a certain amount of food each turn. They consume more while marching\n" +
                "to a city and will consume the most while besieging the city. If the player does not have\n" +
                "sufficient amount of food for all of his soldiers, they will gradually die each turn until he"
        );
        textArea.setFont(BOLD_LABEL);
        textArea.setForeground(Color.white);
        textArea.setOpaque(false);
        textArea.repaint();
        JScrollPane textPane = new JScrollPane(textArea) {
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    Composite composite = ((Graphics2D)g).getComposite();

                    ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    g.setColor(getBackground());
                    g.fillRect(0, 0, getWidth(), getHeight());

                    ((Graphics2D)g).setComposite(composite);
                    paintChildren(g);
                }
                catch(IndexOutOfBoundsException e) {
                    super.paintComponent(g);
                }
            }
        };

        textPane.getViewport().setOpaque(false);
        textPane.setOpaque(false);
        textPane.repaint();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);

        closeButton.setSize(new Dimension(150,100));

        allPanel.add(textPane);
        allPanel.add(Box.createVerticalStrut(20));
        allPanel.add(closeButton);
        allPanel.setBackground(TRANSPARENT_WHITE);

        add(allPanel);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Close")) {
            this.dispose();
        }
    }


    public static void main(String[] args) throws IOException {
        new HelpView();
    }
}
