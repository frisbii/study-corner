import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class GameInfoPanel implements ActionListener { 
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public JButton ticTacToeButton;
    public JButton sudokuButton;
    public JLabel title;
    public Color lightBlue = new Color (121,183,224);
    public Color purple = new Color (197,153,222);
    static JFrame frame;

    SudokuPanel mainSudoku;
    TicTacToePanel2 mainTic;
    Timer timer = new Timer(1000, this);

    // create frame for pop up
    public GameInfoPanel(){
        
         GridBagLayout layout = new GridBagLayout();
          frame = new JFrame("game info");
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
        ticTacToeButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        sudokuButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        ticTacToeButton.setBackground(purple);
        ticTacToeButton.setOpaque(true);
        sudokuButton.setBackground(purple);
        sudokuButton.setOpaque(true);

        title = new JLabel("Select a Game to Play!");
        title.setFont(Fonts.generateCutiveFont(32, 5));


       
        layout.setConstraints(ticTacToeButton, new GridBagConstraints(
            100, 100,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(200,200,25,200),
            0 , 0
        ));
        layout.setConstraints(sudokuButton, new GridBagConstraints(
            100, 200,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(25,200,100,200),
            0 , 0
        ));
        layout.setConstraints(title, new GridBagConstraints(
            100, 100,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(25,185,100,15),
            0 , 0
        ));
         
       
        infoPanel.add(ticTacToeButton);
        infoPanel.add(sudokuButton);
        infoPanel.add(title);
        infoPanel.setBackground(lightBlue);
        
        
      // adding action listeners to set content pane
        ticTacToeButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // creating instance of ticTacToe panel and setting the panel to show this
              mainTic = new TicTacToePanel2();
              
              frame.add(mainTic);
              frame.remove(infoPanel);
              frame.revalidate();
              frame.repaint();
            }
         }
         );

         //if sudoku button pressed, replace the selection pane with a sudoku panel
         sudokuButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainSudoku = new SudokuPanel();
    
                frame.add(mainSudoku);
                frame.remove(infoPanel);
                frame.revalidate();
                frame.repaint();
            }
         }
         );

         timer.start();
    }

    /**
     * whenever an action is performed, check if the event is timer based, and if so, check if the panels need to be closed
     * @param ev the event that has occurred
     */
    public void actionPerformed(ActionEvent ev){
            if(ev.getSource() == timer){
              checkClosePanel();
            }
        
        }

    //listen for sudoku win or tic tic toe win depending on the panel
    private void checkClosePanel(){
        if(!(mainSudoku == null)){
            if(mainSudoku.isVisible() && mainSudoku.solvedFinal){
                mainSudoku.solvedFinal = false;
                frame.remove(mainSudoku);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
        else if(!(mainTic == null)){
            if(mainTic.isVisible() && mainTic.gameDone){
                mainTic.gameDone = false;
                frame.remove(mainTic);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}
