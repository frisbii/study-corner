import java.awt.Color;
import java.awt.Graphics;

public class Spott {
    // move Spott around the screen

    // instance variables
    Pair position;
    Pair velocity;
    double size; // Spott's sprite is contained in a size x size square

    // constructor
    /*
    public Spott(Pair p, Pair v, double s)
    {
        position = p;
        velocity = v;
        size = s;
    }
    */
    
    public Spott()
    {
        position = new Pair(0, 0);
        velocity = new Pair(100, 0);
        size = 50;
    }

    // graphics
    public void draw(Graphics g)
    {
        // different from draw() in Pong.java because we're importing graphics
        // code for when we're using the actual imported images
        /*
        try{
            Image background = ImageIO.read(new File("./resources/images/file_name.png"));
            g.drawImage(background, 0, 0, 800, 600, null);
        }
        catch(Exception e){System.out.println("Error with background: " + e);}
        */

        // code for placeholder rectangle
        g.setColor(Color.RED);
        g.fillRect((int) position.x, (int)  position.y, (int) size, (int) size);
    }
   
    // default roaming around the screen
    public void update(double time)
    {
        double t = time / 1000;
        position.x += velocity.x * t;
        position.y += velocity.y * t;
        turnAround();
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
        // left wall
        if((position.x - (size / 2) < 0) || position.x + (size / 2) > Main.WIDTH)
        {
            velocity.flipX();
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