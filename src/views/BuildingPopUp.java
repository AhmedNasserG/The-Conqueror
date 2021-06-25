package views;

import buildings.*;
import listeners.BuildingPopUpListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingPopUp extends Frame implements ActionListener {
    private Building buildingToShow;
    private final int width;
    private final int height;
    private BuildingPopUpListener listener;

    public BuildingPopUp(Building buildingToShow) {
        super(buildingToShow.getBuildingName());
        this.buildingToShow = buildingToShow;
        this.width = (getWidth() - 420) / 2;
        this.height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);
        Card buildingTile = new Card(buildingToShow);
        buildingTile.setEnabled(false);
        buildingTile.setBounds(420 / 2 - 200 / 3, 10, 400 / 3, 400 / 3);

        JLabel buildingStatus = new JLabel(buildingToShow.isCoolDown() ? "Cooling Down" : "IDLE", SwingConstants.CENTER);
        buildingStatus.setBounds(420 / 2 - 200 / 3, 150, 400 / 3, 20);


        JPanel container1 = new JPanel();
        container1.setBounds(0, 0, 420, 170);
        container1.setBackground(Color.ORANGE);
        container1.setOpaque(true);

        JPanel container2 = new JPanel();
        container2.setBounds(0, 170, 420, 420 - 170);
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));


        if (buildingToShow.getLevel() != 3){
            container2.add(Box.createRigidArea(new Dimension(0, 20)));
            container2.add(getUpgradePanel());
        }
        container2.add(Box.createRigidArea(new Dimension(0, 40)));
        if (buildingToShow instanceof MilitaryBuilding) {
            container2.add(getRecruitPanel());
            container2.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        container2.add(closeButton);

        add(buildingTile);
        add(buildingStatus);
        add(container1);
        add(container2);
        setVisible(true);
    }

    private JPanel getUpgradePanel() {
        JPanel upgradePanel = new JPanel();

        upgradePanel.setLayout(new BoxLayout(upgradePanel, BoxLayout.Y_AXIS));
        upgradePanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));


        JLabel upgradeLabel = new JLabel("Upgrade to Level " + (buildingToShow.getLevel() + 1)
                + " With " + buildingToShow.getUpgradeCost() + " Of Gold"
        );

        JButton upgradeButton = new JButton("Upgrade");
        upgradeButton.addActionListener(this);

        upgradeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upgradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        upgradePanel.add(upgradeLabel);
        upgradePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        upgradePanel.add(upgradeButton);
        return upgradePanel;
    }

    private JPanel getRecruitPanel() {
        JPanel recruitPanel = new JPanel();
        recruitPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        recruitPanel.setLayout(new BoxLayout(recruitPanel, BoxLayout.Y_AXIS));

        JLabel recruitLabel = new JLabel("Recruit new " + ((MilitaryBuilding) buildingToShow).getRecruitableUnitTypeName()
                + " With " + ((MilitaryBuilding) buildingToShow).getRecruitmentCost() + " Of Gold"
        );

        JButton recruitButton = new JButton("Recruit");
        recruitButton.addActionListener(this);

        recruitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recruitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        recruitPanel.add(recruitLabel);
        recruitPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        recruitPanel.add(recruitButton);
        return recruitPanel;
    }

    public static void main(String[] args) {
        new BuildingPopUp(new Barracks());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Upgrade":
                try {
                    listener.onUpgradeClicked(this);
                } catch (Exception exception) {
                }
                break;
            case "Recruit":
                try {
                    listener.onRecruitClicked(this);
                } catch (Exception exception) {
                }
                break;
            case "Close":
                this.dispose();
                break;
        }
    }

    public void setListener(BuildingPopUpListener listener) {
        this.listener = listener;
    }

    public Building getBuildingToShow() {
        return buildingToShow;
    }

}
