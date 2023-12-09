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
import javax.swing.UIManager;

import javax.imageio.*;
import java.awt.Image;

import javax.sound.sampled.*;

class Util {
    public static Color getRandomColor() {
        return new Color((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
    }

    public static Color setAlpha(Color c, int a) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
    }
}

class Fonts {
    public static Font CUTIVE_UI;

    private static final String CUTIVE = "./resources/fonts/Cutive-Regular.ttf";
    private static final String SPACEMONO = "./resources/fonts/SpaceMono-Regular.ttf";
    private static final String SPACEMONO_BOLD = "./resources/fonts/SpaceMono-Bold.ttf";

    public static void setUIFonts() {
        try (InputStream is = new FileInputStream(new File(CUTIVE))) {
            CUTIVE_UI = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(15f).deriveFont(AffineTransform.getTranslateInstance(0, 3));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        java.util.Enumeration<?> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put (key, CUTIVE_UI);
            }
        }
    }

    private static Font generateFontFromPath(String path, int size, int vShift) {
        try (InputStream is = new FileInputStream(new File(path))) {
            Font f = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) size).deriveFont(AffineTransform.getTranslateInstance(0, vShift));
            return f;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Font generateSpaceMonoFont(int size) {
        return generateFontFromPath(SPACEMONO, size, 0);
    }

    public static Font generateSpaceMonoBoldFont(int size) {
        return generateFontFromPath(SPACEMONO_BOLD, size, 0);
    }

    public static Font generateCutiveFont(int size, int vShift) {
        return generateFontFromPath(CUTIVE, size, vShift);
    }

    

}

class Images{

    Image toDoBackground;

    public Images() {
        try {
            toDoBackground = ImageIO.read(new File("./resources/images/temp_background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Sounds implements LineListener{

    File chimeSound;
    File nothingSound;
    private AudioInputStream chimeStream;
    private AudioInputStream audioInputStreamN;
    private boolean isPlaybackCompleted;

    public Sounds(){
        isPlaybackCompleted = false;
        chimeSound = new File("./resources/sounds/gentle_end_sound_16bit.wav");
        //sound from freesounds.org (copyright free)

        nothingSound = new File("./resources/sounds/nothingness.wav");

        try{
            isPlaybackCompleted = false;
            audioInputStreamN = AudioSystem.getAudioInputStream(nothingSound);
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(audioInputStreamN);
            clip.start();
            audioInputStreamN.close();
        }
        catch(Exception e){}
    }

    //line listener update method for sound things
    public void update(LineEvent e){
        if (LineEvent.Type.STOP == e.getType()) {
            isPlaybackCompleted = true;
        }
    }

    public void playChimes(){
        try{
            isPlaybackCompleted = false;
            chimeStream = AudioSystem.getAudioInputStream(chimeSound);
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(chimeStream);
            clip.start();
            chimeStream.close();
        }
        catch(Exception e){System.out.println("Error with sound: " + e);}
    }

}


class AppTheme {

    public static Color PRIMARY;

    public static Image purpleThemeBG;
    public static Image blueThemeBG;
    public static Image greenThemeBG;

    public static ArrayList<JComponent> themedComponents;

    public static void loadThemeAssets() {
        
        // Loads the background into memory
        try {
            AppTheme.purpleThemeBG = ImageIO.read(new File("./resources/images/purple_bg.png"));
            AppTheme.blueThemeBG = ImageIO.read(new File("./resources/images/blue_bg.png"));
            AppTheme.greenThemeBG = ImageIO.read(new File("./resources/images/green_bg.png"));
        } catch (IOException e) { e.printStackTrace(); }

        AppTheme.themedComponents = new ArrayList<JComponent>();

        setTheme("purple");
        
    }

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

    public static void addThemedComponent(JComponent c) {
        AppTheme.themedComponents.add(c);
    }

    public static void repaintThemedComponents() {
        for (JComponent c : AppTheme.themedComponents) {
            c.setBackground(AppTheme.PRIMARY);
        }
    }


}