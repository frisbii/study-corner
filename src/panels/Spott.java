public class Spott {
    // TODO: move Spott around the screen

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
    public void update(World w, double time)
    {
        // TODO: implement
        // position = position.add(velocity.times(time));
    }

    // task accomplished --> excited animation
    public void cheers()
    {
        // TODO: implement
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