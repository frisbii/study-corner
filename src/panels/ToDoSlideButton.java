import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ToDoSlideButton extends PanelBase {

    private static int TDSB_WIDTH = 40;
    private static int TDSB_HEIGHT = 150;
    private static Color TDSB_BGCOLOR = new Color(171, 49, 243, 150);

    private JButton button;

    public ToDoSlideButton() {
        super(TDSB_WIDTH, TDSB_HEIGHT, TDSB_BGCOLOR);

        this.button = new JButton();
        TextIcon t = new TextIcon(this.button, "to-do list", TextIcon.Layout.HORIZONTAL);
        t.setFont(Fonts.generateCutiveFont(20, 3));
        RotatedIcon r = new RotatedIcon(t, RotatedIcon.Rotate.UP);
        this.button.setIcon(r);
        this.button.setFocusPainted(false);
        this.button.setPreferredSize(new Dimension(TDSB_WIDTH, TDSB_HEIGHT));

        this.setLayout(new BorderLayout());
        this.add(this.button, BorderLayout.CENTER);
    }

    public void setAction(ActionListener action) {
        this.button.addActionListener(action);
    }
}
