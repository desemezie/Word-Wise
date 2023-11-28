import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI {
    private static JFrame frame = new JFrame("Simple GUI");
    private static JPanel panelCont = new JPanel();
    private static JPanel panelMain = new JPanel();
    private static JPanel panelLanding = new JPanel();
    private static CardLayout cl = new CardLayout();

    private static JButton button1;
    private static JButton button2;

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(SimpleGUI::create);
//    }

    public static void create() {
        panelCont.setLayout(cl);

        button1 = new JButton("Show Panel Landing");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "landing");
            }
        });

        button2 = new JButton("Show Panel Main");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "main");
            }
        });

        panelMain.add(button1);
        panelLanding.add(button2);

        panelCont.add(panelMain, "main");
        panelCont.add(panelLanding, "landing");

        cl.show(panelCont, "main");

        frame.add(panelCont);
        frame.setLayout(new GridLayout(1, 1));
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

