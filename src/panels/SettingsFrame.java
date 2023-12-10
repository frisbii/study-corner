import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class SettingsFrame extends JDialog implements ActionListener {
    
    public SettingsFrame() {
        super((JFrame) null, true);
        this.setLocation(150, 150);
        
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
    
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        center.add(purple);
        center.add(blue);
        center.add(green);

        JLabel header = new JLabel("Choose a background!");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Layout components
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        wrapper.add(Box.createVerticalGlue());
        wrapper.add(header);
        wrapper.add(Box.createRigidArea(new Dimension(0, 10)));
        wrapper.add(center);
        wrapper.add(Box.createVerticalGlue());
        
        this.add(wrapper, BorderLayout.CENTER);

        
        // Set the dialog to be visible
        this.setTitle("Settings");
        this.setSize(1300, 330);
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