import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JComponent;

import javax.imageio.*;
import java.awt.Image;

import javax.sound.sampled.*;

/**
 * Contains useful methods for use in debugging and in the program
 */
class Util {
    
    /**
     * Get a random colour object
     * 
     * @return      Returns a colour object representing a random colour
     */
    public static Color getRandomColor() {
        return new Color((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
    }

    /**
     * Set the alpha of the given colour object
     * 
     * @param c     The colour object to edit
     * @param a     The alpha to set
     * @return      The colour object with the given colour and alpha
     */
    public static Color setAlpha(Color c, int a) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
    }
}

/**
 * Loads fonts into the program, and creates fonts of different sizes for
 * use throughout the program
 */
class Fonts {

    private static final String CUTIVE = "./resources/fonts/Cutive-Regular.ttf";
    private static final String SPACEMONO = "./resources/fonts/SpaceMono-Regular.ttf";
    private static final String SPACEMONO_BOLD = "./resources/fonts/SpaceMono-Bold.ttf";

    /**
     * Generate a Font object from a path (defined in this class).
     * 
     * @param path      The path to load from (defined in this class)
     * @param size      The point size of the font to generate
     * @param vShift    The vertical shift of the font to generate
     * @return          Font object of specified path, size, and vShift
     */
    private static Font generateFontFromPath(String path, int size, int vShift) {
        try (InputStream is = new FileInputStream(new File(path))) {
            Font f = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) size).deriveFont(AffineTransform.getTranslateInstance(0, vShift));
            return f;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generate a font from the SpaceMono file
     * 
     * @param size      The size to generate
     * @return          Corresponding Font object
     */
    public static Font generateSpaceMonoFont(int size) {
        return generateFontFromPath(SPACEMONO, size, 0);
    }

    /**
     * Generate a font from the SpaceMonoBold file
     * 
     * @param size      The size to generate
     * @return          Corresponding Font object
     */
    public static Font generateSpaceMonoBoldFont(int size) {
        return generateFontFromPath(SPACEMONO_BOLD, size, 0);
    }

    /**
     * Generate a font from the Cutive file
     * 
     * @param size      The size to generate
     * @param vShift    The vShift of the Font
     * @return          Corresponding Font object
     */
    public static Font generateCutiveFont(int size, int vShift) {
        return generateFontFromPath(CUTIVE, size, vShift);
    }

}


class Sounds implements LineListener{

    File chimeSound;
    File nothingSound;
    private AudioInputStream chimeStream;
    private AudioInputStream audioInputStreamN;

    public Sounds(){
        chimeSound = new File("./resources/sounds/gentle_end_sound_16bit.wav");
        //sound from freesounds.org (copyright free)

        nothingSound = new File("./resources/sounds/nothingness.wav");

        try{
            audioInputStreamN = AudioSystem.getAudioInputStream(nothingSound);
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(audioInputStreamN);
            clip.start();
            audioInputStreamN.close();
        }
        catch(Exception e){}
    }

    public void playChimes(){
        try{
            chimeStream = AudioSystem.getAudioInputStream(chimeSound);
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(chimeStream);
            clip.start();
            chimeStream.close();
        }
        catch(Exception e){System.out.println("Error with sound: " + e);}
    }
    
    @Override
    public void update(LineEvent e){
    }
}


/**
 * Contains theme assets and methods to set the theme of the app
 */
class AppTheme {

    public static Color PRIMARY;

    public static Image purpleThemeBG;
    public static Image blueThemeBG;
    public static Image greenThemeBG;

    public static ArrayList<JComponent> themedComponents;

    /**
     * Loads theme assets into memory 
     */
    public static void loadThemeAssets() {
        
        // Loads the backgrounds into memory
        try {
            AppTheme.purpleThemeBG = ImageIO.read(new File("./resources/images/purple_bg.png"));
            AppTheme.blueThemeBG = ImageIO.read(new File("./resources/images/blue_bg.png"));
            AppTheme.greenThemeBG = ImageIO.read(new File("./resources/images/green_bg.png"));
        } catch (IOException e) { e.printStackTrace(); }

        AppTheme.themedComponents = new ArrayList<JComponent>();

        setTheme("purple");
        
    }

    /**
     * Set the app theme from a string denoting the theme
     * 
     * @param theme     The theme to set
     */
    public static void setTheme(String theme) {
        switch (theme) {
            case "purple":
                PRIMARY = new Color(171, 49, 243);
                MainPanel.setBG(AppTheme.purpleThemeBG);
                break;
            case "blue":
                PRIMARY = new Color(0, 81, 255);
                MainPanel.setBG(AppTheme.blueThemeBG);
                break;
            case "green":
                PRIMARY = new Color(13, 191, 39);
                MainPanel.setBG(AppTheme.greenThemeBG);
                break;
        }
        AppTheme.repaintThemedComponents();
    }

    /**
     * Add a component which needs theming to the themedComponents arrayList
     *  
     * @param c     The component to add
     */
    public static void addThemedComponent(JComponent c) {
        AppTheme.themedComponents.add(c);
    }

    /**
     * Repaints the components in the themedComponents arrayList when the theme colour changes
     */
    public static void repaintThemedComponents() {
        for (JComponent c : AppTheme.themedComponents) {
            c.setBackground(AppTheme.PRIMARY);
        }
    }


}