import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JFileChooser fileChooser;

    public TextEditor() {
        super("Text Editor");

        // Create a new text area
        textArea = new JTextArea();

        // Create a new file chooser
        fileChooser = new JFileChooser();

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the file menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Create the open file menu item
        JMenuItem openFileMenuItem = new JMenuItem("Open");
        openFileMenuItem.addActionListener(this);
        fileMenu.add(openFileMenuItem);

        // Create the save file menu item
        JMenuItem saveFileMenuItem = new JMenuItem("Save");
        saveFileMenuItem.addActionListener(this);
        fileMenu.add(saveFileMenuItem);

        // Set the menu bar for the JFrame
        setJMenuBar(menuBar);

        // Add the text area to the JFrame
        add(textArea);

        // Set the size of the JFrame
        setSize(500, 500);

        // Make the JFrame visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle the open file menu item
        if (e.getActionCommand().equals("Open")) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileReader reader = new FileReader(file);
                    textArea.read(reader, null);
                    reader.close();
                } catch (IOException ex) {
                    System.out.println("Problem accessing file: " + file.getAbsolutePath());
                }
            }
        }
        // Handle the save file menu item
        else if (e.getActionCommand().equals("Save")) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    textArea.write(writer);
                    writer.close();
                } catch (IOException ex) {
                    System.out.println("Problem accessing file: " + file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}
