package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NewGameView extends JFrame implements ActionListener {
    JRadioButton[] newRadios;


    public  JPanel newRadioGroup (String[] names){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        ButtonGroup cityGroup = new ButtonGroup();
        JLabel cityLabel  = new JLabel(names[0]);
        newPanel.add(cityLabel);
        newRadios = new JRadioButton[names.length-1];
        for(int i=0; i<newRadios.length;i++) {
            newRadios[i] = new JRadioButton(names[i+1]);
            newRadios[i].addActionListener(this);
            cityGroup.add(newRadios[i]);
            newPanel.add(newRadios[i]);
        }
        return newPanel;
    }
    public NewGameView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);*/
        this.pack();
        this.setTitle("Choosing City");
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));

        JPanel panelName = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel nameLabel  = new JLabel("Enter your name: ");
        JTextField nameTextField = new JTextField(15);
        panelName.add(nameLabel);
        panelName.add(nameTextField);

        JPanel panelCity = newRadioGroup(new String[]{"Choose your city:", "Cairo", "Rome", "Sparta"});
        JButton newGame = new JButton("Play");
        newGame.setSize(15,20);
        newGame.setActionCommand("Play");
        newGame.addActionListener(this);

        add(panelName);
        add(panelCity);
        add(newGame);
        this.validate();
        this.repaint();
    }

    public static void main(String[] args) {
        new NewGameView();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Play")) {
            if( newRadios[0].isSelected()) {
                System.out.println("Cairo is selected");
            }
        }
    }
}
