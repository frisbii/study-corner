import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class SettingsFrame extends JDialog implements ActionListener {
    
    public SettingsFrame() {
        super((JFrame) null, true);
        this.setLocation(300, 250);
        
        JToggleButton purple = new JToggleButton();
        purple.setIcon(new ImageIcon(AppTheme.purpleThemeBG.getScaledInstance(384, 216, Image.SCALE_SMOOTH)));
        purple.setMargin(new Insets(10, 10, 10, 10));
        purple.setActionCommand("purple");
        JToggleButton blue = new JToggleButton();
        blue.setIcon(new ImageIcon(AppTheme.blueThemeBG.getScaledInstance(384, 216, Image.SCALE_SMOOTH)));
        blue.setMargin(new Insets(10, 10, 10, 10));
        blue.setActionCommand("blue");
        JToggleButton green = new JToggleButton();
        green.setIcon(new ImageIcon(AppTheme.greenThemeBG.getScaledInstance(384, 216, Image.SCALE_SMOOTH)));
        green.setMargin(new Insets(10, 10, 10, 10));
        green.setActionCommand("green");

        ButtonGroup buttons = new ButtonGroup();
        buttons.add(purple);
        buttons.add(blue);
        buttons.add(green);

        purple.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);
    

        // Layout components
        this.add(new JLabel("Choose a Background!", SwingConstants.CENTER), BorderLayout.PAGE_START);
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        center.add(purple);
        center.add(blue);
        center.add(green);
        this.add(center, BorderLayout.CENTER);

        
        // Set the dialog to be visible
        this.setTitle("Settings");
        this.setSize(1300, 400);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppTheme.setTheme(e.getActionCommand());
    }
    
    
}

class BGChoice extends JPanel {
    public BGChoice() {

    }
}