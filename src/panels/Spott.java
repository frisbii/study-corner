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
    Image leftSpott;
    Image rightSpott;
    Image cheersSpott;
    
    // constructor
    public Spott(MainPanel mp)
    {
        this.mainPanel = mp;
        
        position = new Pair(0, Main.H - size);
        velocity = new Pair(50, 0);
        size = 200;

        // different from draw() in Pong.java because we're importing graphics
        // importing default image once because spott is created once
        try{
            defaultSpott = ImageIO.read(new File("./resources/images/spott.png"));
            leftSpott = ImageIO.read(new File("./resources/images/left_spott.png"));
            rightSpott = ImageIO.read(new File("./resources/images/right_spott.png"));
            //cheersSpott = ImageIO.read(new File("./resources/images/cheers_spott.png"));
        }
        catch(Exception e){System.out.println("Error with Spott: " + e);}
    }

    public int getHeight() {
        return size;
    }

    public void setLocation(int x, int y) {
        position = new Pair(x, y);
    }

    // graphics
    public void draw(Graphics g)
    {
        // code for placeholder rectangle
        /*
        g.setColor(Color.RED);
        g.fillRect((int) position.x, (int)  position.y, (int) size, (int) size);
        */

        // changing to other images when spott changes direction
        try{
            if(velocity.x > 0)
            {
                state = 1;
                spott = rightSpott;
            }
            else if(velocity.x < 0)
            {
                state = -1;
                spott = leftSpott;
            }
            else
            {
                spott = defaultSpott;
            }
            g.drawImage(spott, (int) position.x, Main.H - size, size, size, null);
        }
        catch(Exception e){System.out.println("Error with background: " + e);}
    }
   
    // default roaming around the screen
    public void update(double time)
    {
        i++;
        double t = time / 1000;
        position.x += velocity.x * t;
        position.y += velocity.y * t;
        turnAround();
        pause();
    }

    // task accomplished --> excited animation
    public void cheers(Graphics g)
    {
        spott = cheersSpott;
        g.drawImage(spott, (int) position.x, Main.H - size, size, size, null);
    }

    // turning around upon reaching the wall
    private void turnAround()
    {
        if((position.x < 0) || (position.x + size > mainPanel.getWidth()))
        {
            velocity.flipX();
        }
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
                velocity.x = 50;
            }
            else if(state == -1)
            {
                velocity.x = -50;
            }
        }
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