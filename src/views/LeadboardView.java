package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class LeadboardView extends Frame {
    private ActionListener listener;

    public LeadboardView(TreeMap<Integer, String> data) {
        JTable LeadboardTable = new JTable(toTableModel(data));
        add(LeadboardTable);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(listener);
        add(closeButton);
        setVisible(true);
    }

    public static TableModel toTableModel(Map<Integer, String> map) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Rank", "Name", "Turns To End The Game"}, 0
        );
        model.addRow(new Object[]{"Rank", "Name", "Turns To End The Game"});
        int rank = 1;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            model.addRow(new Object[]{rank++, entry.getValue(), entry.getKey()});
        }
        return model;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }
}
