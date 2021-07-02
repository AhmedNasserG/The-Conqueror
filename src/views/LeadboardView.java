package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class LeadboardView extends Frame implements ActionListener {

    public LeadboardView(TreeMap<Integer, String> data) {
        setLayout(null);
        this.setBackground("res/backgrounds/start_menu.jpg");
        JTable LeadboardTable = new JTable(toTableModel(data));

        JButton closeButton = new newButton("Close");
        closeButton.addActionListener(this);

        LeadboardTable.setBounds(10,10,getWidth()- 10, getHeight() - 150);
        LeadboardTable.setOpaque(false);
        closeButton.setBounds(getWidth()/2,getHeight() - 140, 180, 70);

        add(LeadboardTable);
        add(closeButton);
        setVisible(true);
    }

    public static TableModel toTableModel(Map<Integer, String> map) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Rank", "Name", "Turns To End The Game"}, 0
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        model.addRow(new Object[]{"Rank", "Name", "Turns To End The Game"});
        int rank = 1;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            model.addRow(new Object[]{rank++, entry.getValue(), entry.getKey()});
        }
        return model;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Close")) {
            this.dispose();
        }
    }

    public static TreeMap<Integer, String> loadLeadboard() throws IOException {
        String currentLine;
        FileReader fileReader = new FileReader("leadboard.csv");
        BufferedReader br = new BufferedReader(fileReader);
        TreeMap<Integer, String> map = new TreeMap<>();
        while ((currentLine = br.readLine()) != null) {
            String[] line = currentLine.split(",");
            map.put(Integer.parseInt(line[1]), line[0]);
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        new LeadboardView(loadLeadboard());
    }
}
