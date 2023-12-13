import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class Spott {
    // move Spott around the screen

    // instance variables
    private Pair position;
    private double velocity;
    int size; // Spott's sprite is contained in a size x size square
    private int state = 1; // Spott's direction (1 = right, -1 = left) --> easier to import images

    private int i = 0; // incremented each time update() is called --> used to determine pauses

    MainPanel mainPanel;

    // current Spott image
    private Image spott;

    // forward facing Spott
    private Image defaultSpott;

    // 4 frame animation: walking right
    private Image right1;
    private Image right2;
    private Image right3;
    private Image right4;

    // 4 frame animation: walking left
    private Image left1;
    private Image left2;
    private Image left3;
    private Image left4;

    // celebration Spott
    private Image cheers;

    private int timeOnFrame = 0; // amount of time that has been spent on Image spott

    // constructor
    public Spott(MainPanel mp)
    {
        this.mainPanel = mp;
        
        position = new Pair(0, Main.H - size);
        velocity = 70;
        size = 270;

        try{
            defaultSpott = ImageIO.read(new File("./resources/images/spott/default.png"));

            right1 = ImageIO.read(new File("./resources/images/spott/right1.png"));
            right2 = ImageIO.read(new File("./resources/images/spott/right2.png"));
            right3 = ImageIO.read(new File("./resources/images/spott/right3.png"));
            right4 = ImageIO.read(new File("./resources/images/spott/right4.png"));

            left1 = ImageIO.read(new File("./resources/images/spott/left1.png"));
            left2 = ImageIO.read(new File("./resources/images/spott/left2.png"));
            left3 = ImageIO.read(new File("./resources/images/spott/left3.png"));
            left4 = ImageIO.read(new File("./resources/images/spott/left4.png"));

            cheers = ImageIO.read(new File("./resources/images/spott/cheers.png"));
        }
        catch(Exception e){System.out.println("Error with Spott: " + e);}

        this.spott = defaultSpott;
    }

    public int getHeight() {
        return size;
    }

    public void setLocation(int x, int y) {
        position = new Pair(x, y);
    }

    // sole function is to draw Spott
    public void draw(Graphics g)
    {
        g.drawImage(spott, (int) position.x, this.mainPanel.getHeight() - size, size, size, null);
    }

    // default roaming around the screen
    public void update(double time)
    {
        i++;
        double t = time / 1000;
        position.add(new Pair(velocity * t, 0));
        // position.x += velocity * t;
        switchFrame();
        turnAround(); 
        pause();
    }
    // note to Serin: update() is repeatedly called by actionPerformed() in MainPanel.java

    // setting the right image for Spott at the time the method is called --> gives draw() the correct image to draw
    private void switchFrame()
    {
        // if Spott is stopped, set image to default image
        if(velocity == 0)
        {
            spott = defaultSpott;
        }
        else // if Spott is moving...
        {
            // when Spott starts moving from a stopped position
            if(spott.equals(defaultSpott))
            {
                if(velocity > 0)
                {
                    spott = right1;
                    state = 1;
                }
                else // if velocity.x < 0
                {
                    spott = left1;
                    state = -1;
                }
            }
            if(timeOnFrame == 30) // "every x number of frames"
            {
                // 4 frame walking animation: left
                if(spott == left1)
                {
                    spott = left2;
                }
                else if(spott == left2)
                {
                    spott = left3;
                }
                else if(spott == left3)
                {
                    spott = left4;
                }
                else if(spott == left4)
                {
                    spott = left1;
                }

                // 4 frame walking animation: right
                if(spott == right1)
                {
                    spott = right2;
                }
                else if(spott == right2)
                {
                    spott = right3;
                }
                else if(spott == right3)
                {
                    spott = right4;
                }
                else if(spott == right4)
                {
                    spott = right1;
                }

                // reset timeOnFrame once frame is switched
                timeOnFrame = 0;
            }
            else
            {
                timeOnFrame++; // incrementing timeOnFrame --> eventually reaches end and switces frame
            }
        }
    }

    // if Spott has reached a wall, turn around
    private void turnAround()
    {
        if((position.x < 0) || (position.x + size > mainPanel.getWidth()))
        {
            if(velocity > 0)
            {
                state = -1;
                spott = left1; // resetting the frame so that switchFrame() works properly
            }
            else if(velocity < 0)
            {
                state = 1;
                spott = right1;
            }
            velocity *= -1;
        }
    }

    // Spott pauses at "random" intervals (not actually random, appears random)
    private void pause()
    {
        if(i % 300 == 0)
        {
            velocity = 0;
        }
        if(i % 500 == 100)
        {
            if(state == 1)
            {
                velocity = 70;
            }
            if(state == -1)
            {
                velocity = -70;
            }
        }
    }

    // task accomplished --> excited animation
    public void cheers()
    {
        spott = cheers;
    }
}

class Pair
{
    double x;
    double y;

    public Pair(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Pair add(Pair pair)
    {
        this.x += pair.x;
        this.y += pair.y;
        return this;
    }

    public void flipX()
    {
        this.x *= -1;
    }
}