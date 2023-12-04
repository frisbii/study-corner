import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

    public Image bgImage;
    public ClockPanel clockPanel;
    public GamesButton gb;
    public TimerPanel timerPanel;

    public MainPanel() {
        this.setLayout(null);
    
        // Initialization tasks
        try {
            bgImage = ImageIO.read(new File("./resources/images/cherry2.png"));
        } catch (IOException e) { e.printStackTrace(); }
        
        this.clockPanel = new ClockPanel();
        this.clockPanel.setLocation(300, 70);
        this.add(this.clockPanel);

        this.gb = new GamesButton();
        this.gb.setLocation(0, this.clockPanel.getLowerY());
        this.gb.centerAlignHorizontal(this.clockPanel);
        this.add(this.gb);
        
        this.timerPanel = new TimerPanel();
        this.timerPanel.setLocation(0, this.clockPanel.getLowerY() + 60);
        this.timerPanel.centerAlignHorizontal(this.clockPanel);
        this.add(this.timerPanel);
    }

    @Override
    public void paintComponent(Graphics g) {

        // Draw the background image below other components
        g.drawImage(bgImage, 0, 0, Main.WIDTH, Main.HEIGHT, null);

        // Draw the clock panel background rectangle
        paintRoundRectBehindPanel(g, this.clockPanel, 15, 15);
        paintRoundRectBehindPanel(g, this.timerPanel, 15, 15);
        paintRoundRectBehindPanel(g, this.gb, 0, 0);
    }

    private void paintRoundRectBehindPanel(Graphics g, PanelBase panel, int aw, int ah) {
        g.setColor(panel.bgColor);
        g.fillRoundRect(panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), aw, ah);
    }

}
