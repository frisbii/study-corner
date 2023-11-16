import javax.swing.*;

public class Spott {
    // move Spott around the screen

    // instance variables
    Pair position;
    Pair velocity;

    // constructor
    public Spott(Pair p, Pair v)
    {
        position = p;
        velocity = v;
    }

    // graphics
    public void draw()
    {
        // TODO: implement
        // different from draw() in Pong.java because we're importing graphics
    }

    // program running --> default movement around the screen
    public void roaming(World w, double time)
    {
        // TODO: implement
        // should be different from update() in Pong.java --> constant speed, only pausing and changing direction
        position = position.add(velocity.times(time)); // constant velocity
        
        // roaming() is called constantly in the main method, need to implement something with time
        Timer timer = new Timer(speed, this); // timer recurs every speed number of milliseconds
        timer.setInitialDelay(pause); // first timer starts after pause number of milliseconds
        timer.start();
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