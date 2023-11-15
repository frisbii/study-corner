import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.UIManager;

class Fonts {
    public static Font CUTIVE_UI;

    public static void setUIFonts() {
        System.out.println(Fonts.class.getResource("Fonts.class"));
        try (InputStream is = Fonts.class.getResourceAsStream("./Cutive-Regular.ttf")) {
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
        try (InputStream is = Fonts.class.getClassLoader().getResourceAsStream("./Cutive-Regular.ttf")) {
            Font f = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) size).deriveFont(AffineTransform.getTranslateInstance(0, 3));
            return f;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
