public class World {
    
    // instance variables
    int length;
    int height;

    Spott spott;

    // constructor
    public World(int x, int y)
    {
        length = x;
        height = y;

        spott = new Spott(new Pair(x, y), new Pair(x, y));
        // TODO: call with actual arguments
    }

    // graphics
    public void drawShapes()
    {
        // TODO: implement
        // again, should be different from the one in Pong.java --> we're importing our own graphics
    }

    // movement
    public void updateShapes(double time)
    {
        spott.roaming(this, time);
    }
}
