import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalDiaryApp extends JFrame {
    private JTextArea diaryTextArea;

    public PersonalDiaryApp() {
        super("My Personal Diary");

        diaryTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(diaryTextArea);

        JButton saveButton = new JButton("Save Entry");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEntry();
            }
        });

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveEntry() {
        String entry = diaryTextArea.getText();
        if (!entry.isEmpty()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            Date date = new Date();
            String fileName = "DiaryEntry_" + dateFormat.format(date) + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(entry);
                JOptionPane.showMessageDialog(this, "Entry saved successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving entry. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Diary entry is empty. Nothing to save.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonalDiaryApp();
            }
        });
    }
}
