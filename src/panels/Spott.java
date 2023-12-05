import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class Spott {
    // move Spott around the screen

    // instance variables
    Pair position;
    Pair velocity;
    double size; // Spott's sprite is contained in a size x size square

    int i = 0; // incremented each time update() is called --> used to determine pauses

    // constructor
    public Spott()
    {
        position = new Pair(0, 0);
        velocity = new Pair(50, 0);
        size = 50;
    }

    // graphics
    public void draw(Graphics g)
    {
        // different from draw() in Pong.java because we're importing graphics
        try{
            Image background = ImageIO.read(new File("./resources/images/spott.png"));
            g.drawImage(background, 0, 0, 800, 600, null);
        }
        catch(Exception e){System.out.println("Error with background: " + e);}

        // code for placeholder rectangle
        /*
        g.setColor(Color.RED);
        g.fillRect((int) position.x, (int)  position.y, (int) size, (int) size);
        */
    }
   
    // default roaming around the screen
    public void update(double time)
    {
        i++;
        System.out.println(position.x);
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
        if((position.x < 0) || (position.x + size > Main.WIDTH))
        {
            System.out.println(position.x);
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