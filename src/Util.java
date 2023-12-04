import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.UIManager;

import javax.imageio.*;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineEvent;

class Util {
    public static Color getRandomColor() {
        return new Color((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
    }
}

class Fonts {
    public static Font CUTIVE_UI;

    private static final String cutivePath = "./resources/fonts/Cutive-Regular.ttf";

    public static void setUIFonts() {
        try (InputStream is = new FileInputStream(new File(cutivePath))) {
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

    public static Font generateCutiveFont(int size) {
        try (InputStream is = new FileInputStream(new File(cutivePath))) {
            Font f = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) size).deriveFont(AffineTransform.getTranslateInstance(0, 3));
            return f;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
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

    static File chimeSound;
    static File nothingSound;
    private static AudioInputStream chimeStream;
    private static AudioInputStream audioInputStreamN;
    private static boolean isPlaybackCompleted;

    static {
        isPlaybackCompleted = false;
        chimeSound = new File("./resources/sounds/gentle_end_sound.wav");
        //sound from freesounds.org (copyright free)

        nothingSound = new File("./resources/sounds/nothingness.wav");
    }

    //line listener update method for sound things
    public void update(LineEvent e){
        if (LineEvent.Type.STOP == e.getType()) {
            isPlaybackCompleted = true;
        }
    }

    public void playNothing(){
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
        catch(Exception e){}
    }

}
