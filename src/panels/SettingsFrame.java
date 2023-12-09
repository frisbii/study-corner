import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class SettingsFrame extends JDialog {
    
    public SettingsFrame() {
        super((JFrame) null, true);
        
        JToggleButton purple = new JToggleButton();
        purple.setIcon(new ImageIcon(MainPanel.purpleBGImage.getScaledInstance(384, 216, Image.SCALE_SMOOTH)));
        purple.setMargin(new Insets(10, 10, 10, 10));
        JToggleButton blue = new JToggleButton();
        blue.setIcon(new ImageIcon(MainPanel.blueBGImage.getScaledInstance(384, 216, Image.SCALE_SMOOTH)));
        blue.setMargin(new Insets(10, 10, 10, 10));
        JToggleButton green = new JToggleButton();
        green.setIcon(new ImageIcon(MainPanel.blueBGImage.getScaledInstance(384, 216, Image.SCALE_SMOOTH)));
        green.setMargin(new Insets(10, 10, 10, 10));
    
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(new JLabel("Choose a Background!", SwingConstants.CENTER));

        add(centerPanel);
        
        // Set the dialog to be visible
        setTitle("Settings");
        setSize(1000, 400);
        setVisible(true);

    }
    
    
}

class BGChoice extends JPanel {
    public BGChoice() {

    }
}