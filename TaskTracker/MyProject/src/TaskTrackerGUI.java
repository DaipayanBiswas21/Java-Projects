package MyProject.src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class TaskTrackerGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Task Tracker");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input Panel using GridBagLayout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(10);
        JLabel priorityLabel = new JLabel("Priority (HIGH, MEDIUM, LOW):");
        JTextField priorityField = new JTextField(10);
        JLabel deadlineLabel = new JLabel("Deadline (yyyy-mm-dd):");
        JTextField deadlineField = new JTextField(10);

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(titleLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 2;
        inputPanel.add(priorityLabel, gbc);
        gbc.gridx = 3;
        inputPanel.add(priorityField, gbc);

        gbc.gridx = 4;
        inputPanel.add(deadlineLabel, gbc);
        gbc.gridx = 5;
        inputPanel.add(deadlineField, gbc);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Table Setup
        String[] columns = {"Title", "Priority", "Deadline", "Completed"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Complete Selected Task");
        JButton sortDeadlineButton = new JButton("Sort by Deadline");
        JButton sortPriorityButton = new JButton("Sort by Priority");

        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(sortDeadlineButton);
        buttonPanel.add(sortPriorityButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> {
            String title = titleField.getText();
            String priority = priorityField.getText();
            String deadline = deadlineField.getText();
            model.addRow(new Object[]{title, priority, deadline, "No"});
        });

        completeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt("Yes", selectedRow, 3);
            }
        });

        sortDeadlineButton.addActionListener(e -> {
            Vector<Vector> data = model.getDataVector();
            data.sort((a, b) -> a.get(2).toString().compareTo(b.get(2).toString()));
            model.fireTableDataChanged();
        });

        sortPriorityButton.addActionListener(e -> {
            Vector<Vector> data = model.getDataVector();
            data.sort((a, b) -> a.get(1).toString().compareToIgnoreCase(b.get(1).toString()));
            model.fireTableDataChanged();
        });

        frame.setVisible(true);
    }
}

