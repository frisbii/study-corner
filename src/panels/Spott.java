import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class Spott {
    // move Spott around the screen

    // instance variables
    Pair position;
    Pair velocity;
    int size; // Spott's sprite is contained in a size x size square
    int state = 1; // Spott's direction (1 = right, -1 = left) --> easier to import images

    int i = 0; // incremented each time update() is called --> used to determine pauses

    MainPanel mainPanel;

    Image spott;
    Image defaultSpott;
    Image leftSpott1;
    Image leftSpott2;
    Image rightSpott1;
    Image rightSpott2;
    Image cheersSpott;

    int timeOnFrame = 0; // amount of time that has been spent on Image spott
    int timePaused = 0; // amount of time that Spott has stopped for

    // constructor
    public Spott(MainPanel mp)
    {
        this.mainPanel = mp;
        
        position = new Pair(0, Main.H - size);
        velocity = new Pair(400, 0);
        size = 270;

        // different from draw() in Pong.java because we're importing graphics
        // importing default image once because spott is created once
        try{
            defaultSpott = ImageIO.read(new File("./resources/images/spott/default.png"));
            leftSpott1 = ImageIO.read(new File("./resources/images/spott/left1.png"));
            leftSpott2 = ImageIO.read(new File("./resources/images/spott/left2.png"));
            rightSpott1 = ImageIO.read(new File("./resources/images/spott/right1.png"));
            rightSpott2 = ImageIO.read(new File("./resources/images/spott/right2.png"));
            //cheersSpott = ImageIO.read(new File("./resources/images/cheers_spott.png"));
        }
        catch(Exception e){System.out.println("Error with Spott: " + e);}

        this.spott = defaultSpott;
    }

    // sole function is to draw Spott
    public void draw(Graphics g)
    {
        g.drawImage(spott, (int) position.x, this.mainPanel.getHeight() - size, size, size, null);
    }

    // setting the right image for Spott at the time the method is called --> gives draw() the correct image to draw
    private void switchFrame()
    {
        if(velocity.x == 0) {
            spott = defaultSpott;
        }
        else
        {
            // when Spott starts moving from a stopped position
            if(spott.equals(defaultSpott))
            {
                if(velocity.x > 0)
                {
                    spott = rightSpott1;
                    state = 1;
                }
                else // if velocity.x < 0
                {
                    spott = leftSpott1;
                    state = -1;
                }
            }
            if(timeOnFrame == 25) // "every 25 frames"
            {
                if(spott == leftSpott1)
                {
                    spott = leftSpott2;
                }
                else if(spott == leftSpott2)
                {
                    spott = leftSpott1;
                }
                else if(spott == rightSpott1)
                {
                    spott = rightSpott2;
                }
                else if(spott == rightSpott2)
                {
                    spott = rightSpott1;
                }
                timeOnFrame = 0; // resetting timeOnFrame once frame is switched
            }
            else
            {
                timeOnFrame++; // incrementing timeOnFrame --> eventually reaches 50 and switces frame
            }
        }
    }
   
    // default roaming around the screen
    // note to Serin: update() is repeatedly called by actionPerformed() in MainPanel.java
    public void update(double time)
    {
        i++;
        double t = time / 1000;
        position.x += velocity.x * t;
        position.y += velocity.y * t;
        switchFrame();
        turnAround(); // if Spott has reached a wall, turn around
        pause(); // Spott pauses at random intervals
    }

    private void pause()
    {
        if(i % 300 == 0)
        {
            velocity.x = 0;
        }
        if(i % 500 == 100)
        {
            if(state == 1)
            {
                velocity.x = 400;
            }
            if(state == -1)
            {
                velocity.x = -400;
            }
        }

        /*
        int checker = (int)(Math.random() * 10); // randomized number to decide whether Spott stops

        // pause when i / 300 is proportional to randomized checker
        if(i % 10 == checker)
        {
            velocity.x = 0;
            timePaused = 0;
        }

        // resume when timePaused is equal to randomized checker
        else if(timePaused == checker)
        {
            if(state == 1)
            {
                velocity.x = 50;
            }
            else if(state == -1)
            {
                velocity.x = -50;
            }
        }
        
        // increment timePaused while paused
        while(velocity.x == 0)
        {
            timePaused++;
        }
        */
    }

    // task accomplished --> excited animation
    public void cheers(Graphics g)
    {
        spott = cheersSpott;
        g.drawImage(spott, (int) position.x, this.mainPanel.getHeight() - size, size, size, null);
    }

    // turning around upon reaching the wall
    private void turnAround()
    {
        if((position.x < 0) || (position.x + size > mainPanel.getWidth()))
        {
            // velocity.flipX();
            if(velocity.x > 0)
            {
                state = -1;
                velocity.x = -400;
                spott = leftSpott1;
            }
            else if(velocity.x < 0)
            {
                state = 1;
                velocity.x = 400;
                spott = rightSpott1;
            }
        }
    }

    public int getHeight() {
        return size;
    }

    public void setLocation(int x, int y) {
        position = new Pair(x, y);
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

    public Pair times(double time)
    {
        return new Pair(this.x * time, this.y * time);
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

    public void flipY()
    {
        this.y *= -1;
    }
}