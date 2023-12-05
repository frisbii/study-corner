import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel {

    public Image bgImage;
    public ClockPanel clockPanel;
    public GamesButton gb;
    public TimerPanel timerPanel;
    public ToDoSlideButton toDoSlideButton;
    public ToDoPanel todoPanel;

    private Timer toDoSlideTimer;
    private boolean toDoPanelIsOpen;
    private boolean sliding;

    public MainPanel() {
        this.setLayout(null);
    
        // Initialization tasks
        try {
            bgImage = ImageIO.read(new File("./resources/images/cherry2.png"));
        } catch (IOException e) { e.printStackTrace(); }

        // Timer which implements to-do list sliding
        this.toDoSlideTimer = new Timer(5, new ActionListener() {
            int velocity;
            {
                // Determines how many pixels the list moves when the timer fires
                velocity = 41;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toDoPanelIsOpen) {
                    todoPanel.setX(todoPanel.getX() + velocity);
                    toDoSlideButton.setX(toDoSlideButton.getX() + velocity);
                    if (todoPanel.getX() >= Main.WIDTH) {
                        todoPanel.setX(Main.WIDTH);
                        toDoSlideButton.setX(Main.WIDTH - toDoSlideButton.getWidth());
                        
                        toDoSlideTimer.stop();
                        toDoPanelIsOpen = false;
                    }
                } else {
                    todoPanel.setX(todoPanel.getX() - velocity);
                    toDoSlideButton.setX(toDoSlideButton.getX() - velocity);
                    if (todoPanel.getX() <= Main.WIDTH - todoPanel.getWidth()) {
                        toDoSlideTimer.stop();
                        todoPanel.setX(Main.WIDTH - todoPanel.getWidth());
                        toDoSlideButton.setX(Main.WIDTH - todoPanel.getWidth() - toDoSlideButton.getWidth());
                        toDoPanelIsOpen = true;
                    }
                }
            }
            
        });
        this.toDoPanelIsOpen = true;


        // Add components and lay them out
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

        this.todoPanel = new ToDoPanel();
        this.todoPanel.setLocation(Main.WIDTH - this.todoPanel.getWidth(), this.clockPanel.getY());
        this.add(this.todoPanel);

        this.toDoSlideButton = new ToDoSlideButton();
        this.toDoSlideButton.setLocation(this.todoPanel.getX() - this.toDoSlideButton.getWidth(), this.todoPanel.getY());
        this.toDoSlideButton.setAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sliding) {
                    toDoSlideTimer.start();
                }
            }
            
        });
        this.add(this.toDoSlideButton);
    }


    @Override
    public void paintComponent(Graphics g) {

        // Draw the background image below other components
        g.drawImage(bgImage, 0, 0, Main.WIDTH, Main.HEIGHT, null);

        // Draw the background rectangles
        paintRoundRectBehindPanel(g, this.clockPanel, 15, 15);
        paintRoundRectBehindPanel(g, this.timerPanel, 15, 15);
        paintRoundRectBehindPanel(g, this.gb, 0, 0);
        paintRoundRectBehindPanel(g, this.todoPanel, 0, 0);

    }

    private void paintRoundRectBehindPanel(Graphics g, PanelBase panel, int aw, int ah) {
        g.setColor(panel.bgColor);
        g.fillRoundRect(panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), aw, ah);
    }

}
