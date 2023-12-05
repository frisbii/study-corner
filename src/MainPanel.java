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
    public MenuButtonsPanel menuButtonsPanel;
    public TimerPanel timerPanel;
    public ToDoSlideButton toDoSlideButton;
    public ToDoPanel todoPanel;
    public Spott spott;

    private Timer toDoSlideTimer;
    private boolean toDoPanelIsOpen;
    private Timer spottTimer;

    /**
     * Constructs the main panel. This is where instances of all the 
     * panels within the main panel are instantiated and added.
     */
    public MainPanel() {
        // Allows the use of absolute positioning
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
                // Sets direction
                if (toDoPanelIsOpen) {
                    // Draw the to-do panel at an offset location each time
                    todoPanel.setX(todoPanel.getX() + velocity);
                    toDoSlideButton.setX(toDoSlideButton.getX() + velocity);
                    if (todoPanel.getX() >= Main.WIDTH) {
                        // Correct overshooting possibilities
                        todoPanel.setX(Main.WIDTH);
                        toDoSlideButton.setX(Main.WIDTH - toDoSlideButton.getWidth());
                        // Stop firing the timer
                        toDoSlideTimer.stop();
                        toDoPanelIsOpen = false;
                    }
                } else {
                    // Same as above, just in the other direction
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

        this.spottTimer = new Timer(5, new ActionListener() {
            int delay;
            {
                delay = 5;
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                spott.update(delay);
                repaint();
            }
        });
        this.spottTimer.start();

        // Add components and lay them out
        // setLocation calls position each component within MainPanel
        this.clockPanel = new ClockPanel();
        this.clockPanel.setLocation(300, 70);
        this.add(this.clockPanel);

        this.menuButtonsPanel = new MenuButtonsPanel();
        this.menuButtonsPanel.setLocation(0, this.clockPanel.getLowerY());
        // centerAlignHorizontal allows for relative positioning
        this.menuButtonsPanel.centerAlignHorizontal(this.clockPanel);
        this.add(this.menuButtonsPanel);
        
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
                // If the toggle button is pressed, start movement
                toDoSlideTimer.start();
            }
            
        });
        this.add(this.toDoSlideButton);

        this.spott = new Spott();
        this.spott.setLocation(0, Main.HEIGHT - 100);

    }


    @Override
    public void paintComponent(Graphics g) {

        // Draw the background image below other components
        g.drawImage(bgImage, 0, 0, Main.WIDTH, Main.HEIGHT, null);

        // Draw the background rectangles under the components
        paintRoundRectBehindPanel(g, this.clockPanel, 15, 15);
        paintRoundRectBehindPanel(g, this.timerPanel, 15, 15);
        paintRoundRectBehindPanel(g, this.todoPanel, 0, 0);

        this.spott.draw(g);


    }

    /**
     * Draws a rectangle onto the mainPanel behind the passed panel object.
     * This is separate from each panel so as to avoid conflicts with
     * Swing's painting order and transluscent colours.
     * 
     * @param g       Graphics object used to draw to screen
     * @param panel   The panel behind which to draw the rectangle
     * @param aw      Arc width (rounded corners)
     * @param ah      Arc height (rounded corners)
     * 
     */
    private void paintRoundRectBehindPanel(Graphics g, PanelBase panel, int aw, int ah) {
        g.setColor(panel.bgColor);
        g.fillRoundRect(panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), aw, ah);
    }

}
