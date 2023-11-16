import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class MainPanel extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    public JPanel centralPanel;
    public JTextArea bottomPanel;

    public Image bgImage;

    public MainPanel() {
        Fonts.setUIFonts();

        try {
            bgImage = ImageIO.read(new File("./resources/images/cherrytree_bg.png"));
        } catch (IOException e) { e.printStackTrace(); }


        // Central panel
        this.centralPanel = new JPanel(new GridLayout(2,2));
        //this.centralPanel.setPreferredSize(new Dimension(600, 520));

        this.centralPanel.add(new ClockPanel());
        try{
            ToDoPanel toDoPanel = new ToDoPanel();
            this.centralPanel.add(toDoPanel);
        }
        catch(Exception e) {System.out.println("Error: " + e);}
        this.centralPanel.add(new TimerPanel());
        this.centralPanel.add(new TicTacToe());

        // Bottom panel
        this.bottomPanel = new JTextArea("spott's home :]");
        this.bottomPanel.setPreferredSize(new Dimension(this.getWidth(), 40));
        this.bottomPanel.setBackground(new Color(135,166,202));





        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weighty = 0.15;
        this.add(new EmptyPanel(new Color(255, 0, 0, 100)), c);

        double chw = 1.8;
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.35;
        c.weighty = chw;
        this.add(new EmptyPanel(new Color(0, 255, 0, 100)), c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.60;
        c.weighty = chw;
        //this.add(new EmptyPanel(new Color(0, 255, 0, 255)), c);

        // Wrap the centralPanel in a Flow Layout
        JPanel f = new JPanel();
        f.setPreferredSize(new Dimension(0, 0));
        f.setLayout(new BorderLayout());
        f.add(this.centralPanel, BorderLayout.CENTER);
        this.add(f, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.10;
        c.weighty = chw;
        this.add(new EmptyPanel(new Color(0, 0, 255, 100)), c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.20;
        c.insets = new Insets(40, 0, 0, 0);
        //this.add(new JLabel("spott!") {{ this.setOpaque(true); this.setBackground(Color.GREEN); }}, c);
        this.add(new SpottPanel(), c);
        

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
    }

   
}

class EmptyPanel extends JPanel {
    public EmptyPanel() {
        this.setBackground(new Color(0,0,0,0));
    }
    public EmptyPanel(Color c) {
        this.setBackground(new Color(0,0,0,0));
        //this.setBackground(c);
    }
}