package views;

import listeners.UnitPopUpListener;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class UnitPopUp extends Frame implements ActionListener {

    private UnitPopUpListener listener;
    private Unit enemyUnit;

    public UnitPopUp(Unit u){
        super(u.getUnitName());
        this.enemyUnit = u;

        setBounds(getWidth()/3, getHeight()/3,getWidth()/5, getHeight()/5);
        setLayout(null);

        JLabel enemyUnitLabel = new JLabel("ENEMY UNIT");
        enemyUnitLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        enemyUnitLabel.setForeground(new Color(153, 6, 6));
        enemyUnitLabel.setBounds(420 / 2 - 85, 1, getWidth(), 50);

        Card unitCard = new Card(u);
        unitCard.setBounds(420 / 2 - 95, 40, 400 / 3, 400 / 3);
        unitCard.removeMouseListener(unitCard);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);

        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(this);



        JPanel buttonsContainer = new JPanel();
        FlowLayout fl = new FlowLayout();
        fl.setHgap(25);
        buttonsContainer.setLayout(fl);
        buttonsContainer.setOpaque(true);
        buttonsContainer.setBounds(0, 180, 420, 420 - 200);

        buttonsContainer.add(attackButton);
        buttonsContainer.add(closeButton);


        add(enemyUnitLabel);
        add(unitCard);
        add(buttonsContainer);

        setUndecorated(true);
        setAlwaysOnTop(true);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Close": {
                this.dispose();
                break;
            }

            case "Attack": {
                listener.onAttackPressed(enemyUnit);
            }

        }
    }

    public void setListener(UnitPopUpListener listener) {
        this.listener = listener;
    }
}
