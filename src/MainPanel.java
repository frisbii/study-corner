import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class MainPanel extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    public JPanel centralPanel;
    public JTextArea bottomPanel;

    public MainPanel() {
        Fonts.setUIFonts();
        this.setLayout(new BorderLayout());

        // Central panel
        this.centralPanel = new JPanel(new GridLayout(2,2));
        this.add(this.centralPanel, BorderLayout.CENTER);

        this.centralPanel.add(new JTextArea("top left"));
        try{
            ToDoPanel toDoPanel = new ToDoPanel();
            this.centralPanel.add(toDoPanel);
        }
        catch(Exception e) {System.out.println("Error: " + e);}
        this.centralPanel.add(new JTextArea("bottom left"));
        this.centralPanel.add(new JTextArea("bottom right"));

        // Bottom panel
        this.bottomPanel = new JTextArea("spott's home :]");
        this.add(this.bottomPanel, BorderLayout.PAGE_END);
        this.bottomPanel.setPreferredSize(new Dimension(this.getWidth(), 40));
        this.bottomPanel.setBackground(Color.GREEN);

    }
}
