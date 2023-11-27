import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel {
    public JPanel centralPanel;
    public JPanel bottomPanel;

    public Image bgImage;

    public final int centralPanelWidth = 1000;
    public final int centralPanelHeight = 600;
    public final int centralPanelXOffset = 440;
    public final int centralPanelYOffset = 140;
    public final int bottomPanelHeight = 100;

    public MainPanel() {
        // Initialization tasks
        try {
            bgImage = ImageIO.read(new File("./resources/images/cherrytree_bg.png"));
        } catch (IOException e) { e.printStackTrace(); }
        //
        

        // Create the centralPanel
        this.centralPanel = new JPanel(new GridLayout(2, 2));
        this.centralPanel.add(new ClockPanel());
        this.centralPanel.add(new ToDoPanel());
        this.centralPanel.add(new TimerPanel());
        this.centralPanel.add(new TicTacToe());

        // Bottom panel
        this.bottomPanel = new JTextArea("spott's home :]");
        this.bottomPanel.setPreferredSize(new Dimension(this.getWidth(), 40));
        this.bottomPanel.setBackground(new Color(135,166,202));





        GridBagLayout gridBag = new GridBagLayout();
        this.setLayout(gridBag);
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weighty = 0.15;
        this.add(new EmptyPanel(new Color(255, 0, 0, 100)), c);

        double chw = 1.8;
        
        // Responsible for correctly spacing out all the components onto the window
        this.setLayout(new BorderLayout());
        JPanel horizontalBoxSpacer = new JPanel();
        horizontalBoxSpacer.setOpaque(false);
        horizontalBoxSpacer.setLayout(new BoxLayout(horizontalBoxSpacer, BoxLayout.X_AXIS));
        horizontalBoxSpacer.add(Box.createRigidArea(new Dimension(centralPanelXOffset, 0)));
        horizontalBoxSpacer.add(this.centralPanel);
        horizontalBoxSpacer.add(Box.createRigidArea(new Dimension(Main.WIDTH - (centralPanelXOffset + centralPanelWidth), 0)));
        
        JPanel verticalBoxSpacer = new JPanel();
        verticalBoxSpacer.setOpaque(false);
        verticalBoxSpacer.setLayout(new BoxLayout(verticalBoxSpacer, BoxLayout.Y_AXIS));
        verticalBoxSpacer.add(Box.createRigidArea(new Dimension(0, (Main.HEIGHT - centralPanelHeight - bottomPanelHeight) / 2)));
        verticalBoxSpacer.add(horizontalBoxSpacer);
        verticalBoxSpacer.add(Box.createRigidArea(new Dimension(0, (Main.HEIGHT - centralPanelHeight - bottomPanelHeight) / 2)));
        this.add(verticalBoxSpacer, BorderLayout.CENTER);
        
        this.bottomPanel = new SpottPanel(bottomPanelHeight);
        this.add(this.bottomPanel, BorderLayout.PAGE_END);
        // ------
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bgImage, 0, 0, Main.WIDTH, Main.HEIGHT, null);
    }
}

class EmptyPanel extends JPanel {
    public EmptyPanel() {
        this(new Color((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random())));
    }
    public EmptyPanel(Color c) {
        this.setBackground(c);
    }
}