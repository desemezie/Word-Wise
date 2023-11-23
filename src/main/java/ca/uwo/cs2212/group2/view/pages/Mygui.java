package ca.uwo.cs2212.group2.view.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.io.File;

public class Mygui implements ActionListener {
    private JFrame frame;
    private JButton button; 
    private JButton button2;
    private PopupFactory pf;
    private JButton close;
    private JPanel panel;
    private JPanel poppanel;
    private JRadioButton engUS;
    private JRadioButton engUK;
    private JRadioButton engCa;
    private JLabel label1;
    private JLabel label2;
    private Popup p;

    // Create a file chooser
    final JFileChooser fileChooser = new JFileChooser();
    
    Mygui(){  

        frame = new JFrame();

        poppanel = new JPanel(new BorderLayout());
        poppanel.setSize(800, 500);
        label1 = new JLabel("<html><div style='text-align: center;'>Welcome to our Spellchecker application! To begin, you can choose to upload an existing .txt or .html file or create your own here.</div></html>");
        Font newLabelFont = new Font(label1.getFont().getName(), label1.getFont().getStyle(), 20);
        label1.setFont(newLabelFont);
        label1.setForeground(Color.WHITE);
        close = new JButton("Begin!");
        Font newCloseFont = new Font(close.getFont().getName(), close.getFont().getStyle(), 20);
        close.setPreferredSize(new Dimension(150, 75));
        close.setMaximumSize(new Dimension(150, 75));
        close.setFont(newCloseFont);
        close.setForeground(new Color(0x993399));
        close.addActionListener(this);
        poppanel.add(label1, BorderLayout.CENTER); // Position label at the top
        poppanel.add(close, BorderLayout.SOUTH); // Position button at the bottom
        poppanel.setBackground(new Color(0x993399));
        poppanel.setPreferredSize(new Dimension(800, 500));
        pf = PopupFactory.getSharedInstance();
        p = pf.getPopup(frame,poppanel,300,100);

        button = new JButton();
        button.setBounds(150,100,400,300);
        button.addActionListener(this);
        button.setText("New File");
        Font newButtonFont=new Font(button.getFont().getName(),button.getFont().getStyle(),20);
        button.setFont(newButtonFont);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setForeground(new Color(0xffffff));
        button.setBackground(new Color(0x993399));

        button2 = new JButton();
        button2.setBounds(880,100,400,300);
        button2.addActionListener(this);
        button2.setText("Upload File");
        button2.setFont(newButtonFont);
        button2.setFocusable(false);
        button2.setBorderPainted(false);
        button2.setOpaque(true);
        button2.setForeground(new Color(0xffffff));
        button2.setBackground(new Color(0x993399));

        engUS = new JRadioButton("English (United States)");
        engUK = new JRadioButton("English (United Kingdom)");
        engCa = new JRadioButton("English (Canada)");
        engUS.addActionListener(this);
        engUK.addActionListener(this);
        engCa.addActionListener(this);
        engUS.setForeground(new Color(0x993399));
        engUK.setForeground(new Color(0x993399));
        engCa.setForeground(new Color(0x993399));
        ButtonGroup group = new ButtonGroup();
        group.add(engUS);
        group.add(engUK);
        group.add(engCa);
        engUS.setBounds(597, 500, 200, 30);
        engUK.setBounds(597, 550, 200, 30);
        engCa.setBounds(597, 600, 200, 30);

        panel = new JPanel();
        panel.setBackground(new Color(0xebebe0));
        panel.setLayout(null);
        panel.add(button);
        panel.add(button2);
        panel.add(engUS);
        panel.add(engUK);
        panel.add(engCa);
        panel.setBounds(0,0,1594,1030);

        frame.setLayout(null);
        frame.setSize(1594,1030);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Spellchecker");
        frame.add(panel);
        p.show();

        frame.setVisible(true);

        

    }  

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == button){
            //do something when create file is clicked
            System.out.println("create file");
            //SpellCheckerUI window = new createAndShowGUI(); 

        }
        else if(e.getSource() == button2){
            //do something when upload file is clicked
            int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getPath());
                    // You can perform operations on the selected file here
            }
        }
        else if(e.getSource() == engUS){
            System.out.println("us");
        }
        else if(e.getSource() == engUK){
            System.out.println("uk");
        }
        else if(e.getSource() == engCa){
            System.out.println("OH CANADA");
        }
        else if(e.getSource() == close){
            p.hide();
        }

        }

    public static void main(String[] args) {
        new Mygui();
    }

}


