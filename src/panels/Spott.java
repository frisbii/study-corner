import java.awt.Color;
import java.awt.Graphics;

public class Spott {
    // move Spott around the screen

    // instance variables
    Pair position;
    Pair velocity;
    double size; // Spott's sprite is contained in a size x size square

    // constructor
    public Spott(Pair p, Pair v, double s)
    {
        position = p;
        velocity = v;
        size = s;
    }

    // graphics
    /*
    public void draw()
    {
        // TODO: implement
        // different from draw() in Pong.java because we're importing graphics
        try{
            Image background = ImageIO.read(new File("./resources/images/file_name.png"));
            g.drawImage(background, 0, 0, 800, 600, null);
        }
        catch(Exception e){System.out.println("Error with background: " + e);}
    }
    */

    // TODO: placeholder for Spott graphics
    public void tempDraw(Graphics g)
    {
        Color c = g.getColor();
        g.fillOval((int)(position.x - size / 2), (int)(position.y - size / 2), (int)(size), (int)(size));
        g.drawOval((int)(position.x - size / 2), (int)(position.y - size / 2), (int)(size), (int)(size));
        g.setColor(c);
    }

    // program running --> default movement around the screen
    public void roaming(double time)
    {
        // TODO: implement
        // should be different from update() in Pong.java --> constant speed, only pausing and changing direction
        position = position.add(velocity.times(time)); // constant velocity
    }

    // task accomplished --> excited animation
    public void cheers()
    {
        // TODO: implement
        // insert different image (again, importing graphics)
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