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

    int i = 0; // incremented each time update() is called --> used to determine pauses

    MainPanel mainPanel;

    Image spott;
    
    // constructor
    public Spott(MainPanel mp)
    {
        this.mainPanel = mp;
        
        position = new Pair(0, 0);
        velocity = new Pair(50, 0);
        size = 50;

        // different from draw() in Pong.java because we're importing graphics
        // importing default image once because spott is created once
        try{
            spott = ImageIO.read(new File("./resources/images/spott.png"));
        }
        catch(Exception e){System.out.println("Error with background: " + e);}
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

        // importing other images when spott changes direction
        try{
            Image spott;
            if(velocity.x > 0)
            {
                spott = ImageIO.read(new File("./resources/images/walking_right.png"));
            }
            else if(velocity.x < 0)
            {
                spott = ImageIO.read(new File("./resources/images/walking_left.png"));
            }
            else
            {
                spott = ImageIO.read(new File("./resources/images/spott.png"));
            }
            g.drawImage(spott, (int) position.x, size, size, size, null);
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
    public void cheers()
    {
        // TODO: implement
        // insert different image (again, importing graphics)
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
            // TODO: set states (e.g. "walk right") to make sprite implementation easier (easy, just depends on direction of movement)
        }
        if(i % 500 == 100)
        {
            velocity.x = 50;
            // TODO: can't just set velocity to 50 every time, needs to depend on direction
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