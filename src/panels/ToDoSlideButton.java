import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Button panel which attaches to the to-do list. Clicking
 * the button triggers the to-do list sliding animation.
 */
public class ToDoSlideButton extends PanelBase {

    private static final int TDSB_WIDTH = 40;
    private static final int TDSB_HEIGHT = 150;
    private static final Color TDSB_BGCOLOR = new Color(171, 49, 243, 150);

    private JButton button;

    /**
     * Constructs the button
     */
    public ToDoSlideButton() {
        super(TDSB_WIDTH, TDSB_HEIGHT, TDSB_BGCOLOR);

        this.button = new JButton();

        // Using classes downloaded from Rob Camick (cited in bibliography.txt)
        // to rotate the text in the button
        TextIcon t = new TextIcon(this.button, "to-do list", TextIcon.Layout.HORIZONTAL);
        t.setFont(Fonts.generateCutiveFont(20, 3));
        RotatedIcon r = new RotatedIcon(t, RotatedIcon.Rotate.UP);
        this.button.setIcon(r);

        this.button.setFocusPainted(false); // stops the border appearing around the text when clicked

        this.setLayout(new BorderLayout());
        this.add(this.button, BorderLayout.CENTER);
    }

    /**
     * Used by MainPanel to set the action of the button
     * to animate the to-do list.
     * 
     * @param action    ActionListener which responds to the button's events
     */
    public void setAction(ActionListener action) {
        this.button.addActionListener(action);
    }
}
