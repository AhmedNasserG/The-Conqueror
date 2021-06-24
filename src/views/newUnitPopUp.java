package views;

import listeners.UnitPopUpListener;
import units.Archer;
import units.Cavalry;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;


public class newUnitPopUp extends Frame implements ActionListener {

    private UnitPopUpListener listener;

    private Unit unit;

    public newUnitPopUp(Unit unit){
        //TODO: fix the button only appears on hover
        super(unit.getUnitName());
        this.unit = unit;
        int width = (getWidth() - 420) / 2;
        int height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);

        JLabel unitName = new JLabel(unit.getUnitName().toUpperCase());
        unitName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));

        unitName.setBounds(160, 1, getWidth(), 50);
        unitName.setAlignmentX(Component.CENTER_ALIGNMENT);

        Card unitCard = new Card(unit);
        unitCard.setBounds(135, 40, 400 / 3, 400 / 3);

        JPanel status = new JPanel();
        status.setLayout(new BoxLayout(status,BoxLayout.Y_AXIS));

        JLabel unitLevel = new JLabel("Unit Level: "+unit.getLevel());
        JLabel unitCurrentSoliderCount = new JLabel("Current Solider Count: "+ unit.getCurrentSoldierCount());
        unitLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        unitCurrentSoliderCount.setAlignmentX(Component.CENTER_ALIGNMENT);

        status.add(unitLevel);
        status.add(unitCurrentSoliderCount);
        status.setBounds(0, 230, 420, 420 - 200);

        JButton relocateButton = new JButton("Relocate Unit");
        relocateButton.addActionListener(this);
        relocateButton.setActionCommand("Relocate");
        relocateButton.setBounds(120, 290, 150, 40);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setBounds(145, 335, 100, 40);

        add(relocateButton);
        add(status);
        add(unitName);
        add(unitCard);
        add(closeButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Close": {
                this.dispose();
                break;
            }
            case "Relocate": {
                new RelocateView(unit.getParentArmy());
                break;
            }
        }
    }

    public void setListener(UnitPopUpListener listener) {
        this.listener = listener;
    }

    public static void main(String[] args) {
        new newUnitPopUp(new Cavalry(2));
    }
}
