package ca.uwo.cs2212.group2.view.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class Mygui implements ActionListener {
    private JFrame frame;
    private JButton button;
    private JButton button2;
    private JButton popButton;
    private JPanel panel;
    private JRadioButton engUS;
    private JRadioButton engUK;
    private JRadioButton engCa;
    private JLabel label1;
    private JLabel label2;

    Mygui(){

        frame = new JFrame();

        label1 = new JLabel("Welcome to our Spellchecker application!");
        label2 = new JLabel("Click to get started!");

        popButton = new JButton("Welcome! Click to continue.");
        popButton.setBounds(100,50,400,300);
        popButton.addActionListener(this);
        popButton.setFocusable(false);
        Border b = BorderFactory.createLineBorder(new Color(0x732673));
        popButton.setBorder(b);
        popButton.setOpaque(true);
        popButton.setForeground(new Color(0xffffff));
        popButton.setBackground(new Color(0x993399));

        button = new JButton();
        button.setBounds(50,100,200,100);
        button.addActionListener(this);
        button.setText("New File");
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setForeground(new Color(0xffffff));
        button.setBackground(new Color(0x993399));

        button2 = new JButton();
        button2.setBounds(350,100,200,100);
        button2.addActionListener(this);
        button2.setText("Upload File");
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
        engUS.setBounds(200, 250, 200, 30);
        engUK.setBounds(200, 280, 200, 30);
        engCa.setBounds(200, 310, 200, 30);

        panel = new JPanel();
        panel.setBackground(new Color(0xebebe0));
        panel.setLayout(null);
        panel.add(popButton);
        panel.add(button);
        panel.add(button2);
        panel.add(engUS);
        panel.add(engUK);
        panel.add(engCa);

        panel.setBounds(0,0,600,500);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Spellchecker");
        frame.add(panel);

        frame.setSize(600,500);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
            //do something when create file is clicked
            System.out.println("create file");
        }
        else if(e.getSource() == button2){
            //do something when upload file is clicked
            System.out.println("upload file");
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
        else if(e.getSource() == popButton){
            popButton.setVisible(false);
            popButton.invalidate();
        }

        }




    public static void main(String[] args) {
        new Mygui();
    }
}


