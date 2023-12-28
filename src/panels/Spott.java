import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class Spott {

    // components of Spott's appearance and movement
    private Pair position;
    private double velocity;
    int size; // Spott's sprite is contained in a size x size square
    private int direction = 1; // 1 = right, -1 = left --> easier to import images
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

    boolean isCheering;

    // constructor
    public Spott(MainPanel mp)
    {
        this.mainPanel = mp;
        
        position = new Pair(0, this.mainPanel.getHeight() - size);
        velocity = 70;
        size = 220;
        isCheering = false;

        // importing images to use as Spott's sprites
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

        // setting initial Spott sprite
        this.spott = right1;
    }

    public int getHeight() {
        return size;
    }

    public void setLocation(int x, int y) {
        position = new Pair(x, y);
    }

    // draws Spott (note: does not change Spott's position or direction, ONLY draws him)
    public void draw(Graphics g)
    {
        g.drawImage(spott, (int) position.x, this.mainPanel.getHeight() - size, size, size, null);
    }

    // Spott roaming around the screen
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

    // sets the right image for Spott at the time the method is called --> gives draw() the correct image to draw
    private void switchFrame()
    {
        // if Spott is stopped, set image to default image
        if(velocity == 0 && !isCheering)
        {
            spott = defaultSpott;
        }
        else // if Spott is moving...
        {
            // when Spott starts moving from a stopped position, set image and direction appropriately
            if(spott.equals(defaultSpott))
            {
                if(velocity > 0)
                {
                    spott = right1;
                    direction = 1;
                }
                else
                {
                    spott = left1;
                    direction = -1;
                }
            }
            if(timeOnFrame == 30) // every 30 frames, Spott moves to another stage in the 4-step cycle walking animation
            {
                if(isCheering) isCheering = false;
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

                // reset timeOnFrame once frame is switched --> restarts count to determine when next switch takes place
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
                direction = -1;
                spott = left1; // resets the frame so that switchFrame() works properly
            }
            else if(velocity < 0)
            {
                direction = 1;
                spott = right1;
            }
            velocity *= -1;
        }
    }

    // Spott pauses at pseudo-random intervals
    private void pause()
    {
        if(i % 300 == 0)
        {
            velocity = 0;
        }
        if(i % 500 == 100)
        {
            if(direction == 1)
            {
                velocity = 70;
            }
            if(direction == -1)
            {
                velocity = -70;
            }
        }
    }

    // task accomplished --> excited animation
    public void cheers()
    {
        spott = cheers;
        velocity = 0;
        isCheering = true;
        timeOnFrame = 0;
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

    // adds the x value of pair to the x value of this and the y value of pair to the y value of this
    public Pair add(Pair pair)
    {
        this.x += pair.x;
        this.y += pair.y;
        return this;
    }

    // switches sign of x
    public void flipX()
    {
        this.x *= -1;
    }
}