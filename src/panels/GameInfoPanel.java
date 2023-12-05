import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GameInfoPanel {
    // lag when pushing games button on main??
    //do we want the frame to be the same size or different?
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public JButton ticTacToeButton;
    public JButton sudokuButton;

    // create frame for pop up
    public GameInfoPanel(){
        
         GridBagLayout layout = new GridBagLayout();
         JFrame frame = new JFrame("game info");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
           JPanel infoPanel = new JPanel(layout);
        
            //Size and display the window.
            frame.add(infoPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
            frame.setResizable(false);
         
            // setting up buttons   
        ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setFont(Fonts.generateCutiveFont(24, 5));
        sudokuButton = new JButton("Sudoku");
        sudokuButton.setFont(Fonts.generateCutiveFont(24, 5));

       
        layout.setConstraints(ticTacToeButton, new GridBagConstraints(
            100, 100,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,3),
            0 , 0
        ));
        layout.setConstraints(sudokuButton, new GridBagConstraints(
            200, 200,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,3),
            0 , 0
        ));
         
        ticTacToeButton.setBorder(new BevelBorder(BevelBorder.RAISED));
         sudokuButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        infoPanel.add(ticTacToeButton);
        infoPanel.add(sudokuButton);
        
      // adding action listeners to set content pane
        ticTacToeButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // creating instance of ticTacToe panel and setting the panel to show this
              TicTacToePanel main = new TicTacToePanel();
              
              frame.add(main.overall);
              System.out.println("tic tac toe button pressed");
              frame.remove(infoPanel);
              frame.revalidate();
              frame.repaint();
            }
         }
         );

         sudokuButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             // TODO: switch content pane to sodoku panel
            }
         }
         );
    
   
    }

    
}
