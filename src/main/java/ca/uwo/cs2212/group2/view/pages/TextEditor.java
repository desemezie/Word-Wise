import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.border.Border;

public class TextEditor extends JFrame {

    private JTextArea textArea;

    /**
     * 
     */
    public TextEditor() {
        setTitle("Simple Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        // Create a panel to hold components with beige background
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        contentPane.setBackground(new Color(245, 245, 220));
        
        
        
        // Create a panel to hold components
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a JTextArea for text editing
        textArea = new JTextArea();
        textArea.setBackground(Color.WHITE);
        textArea.setPreferredSize(new Dimension(450,450));
        textArea.setLineWrap(true);
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(50, 50,50,50));
        
        textArea.setBorder(border);
        
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create a menu bar and menu
        JMenuBar menuBar = new JMenuBar();
        UIManager.put("menuBar.background", Color.ORANGE);
        
        
        JMenu settings = new JMenu("Settings");
        //settings.setOpaque(true);
        settings.setBackground(Color.pink);

        JMenu fileMenu = new JMenu("File");
        //fileMenu.setOpaque(true);
        fileMenu.setBackground(Color.pink);

        JMenu Metrics = new JMenu("Metrics");
        //Metrics.setOpaque(true);
        Metrics.setBackground(Color.pink);
        
        JMenu spellCheck = new JMenu("Spellcheck");
        //spellCheck.setOpaque(true);
        spellCheck.setBackground(Color.pink);
        
        JMenu save = new JMenu("Save");
        //save.setOpaque(true);
        save.setBackground(Color.pink);
        
        menuBar.setPreferredSize(new Dimension(getWidth(), 75));
        
        //menuBar.setOpaque(true);
       
        
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem stuff = new JMenuItem("Stuff");
        JMenuItem userDict = new JMenuItem("View User Dictionary");
        JMenuItem exit = new JMenuItem("Exit Checker");
        JMenuItem addWord = new JMenuItem("Add Word to Dictionary");
        JMenuItem removeWord = new JMenuItem("Remove Word from Dictionary");
        
        
        // Add action listeners for menu items
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        // Add menu items to the menu
        settings.add(userDict);
        settings.add(exit);
        settings.add(addWord);
        settings.add(removeWord);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        Metrics.add(stuff);

        // Add the menu to the menu bar
        menuBar.add(settings);
        //menuBar.add(new JPanel());
        menuBar.add(fileMenu);
        //menuBar.add(new JPanel());
        menuBar.add(Metrics);
        //menuBar.add(new JPanel());
        menuBar.add(spellCheck);
        menuBar.add(new JPanel());
        menuBar.add(save);
        

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Add components to the panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the frame
        add(panel);

        setVisible(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();

        // Show open dialog
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Read the selected file and set the text in the JTextArea
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                StringBuilder text = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line).append("\n");
                }
                textArea.setText(text.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();

        // Show save dialog
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Write the text from the JTextArea to the selected file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}

