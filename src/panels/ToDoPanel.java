
import javax.swing.*;
import javax.swing.border.Border;

import javax.imageio.*;
import java.io.File;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

//TO DO: WHY DOES IT DISPLAY THE TIMER IN THE BACKGROUND WHEN ADDING TASKS 2-4 WHY
//does not have anything to do with the timer being in the panel after it, it just happens

//TO DO: maybe make a pretty background outline for some of the JPanels? :)
//TO DO: actual check boxes in the tasks
//TO DO: makes Util Images work properly with background

public class ToDoPanel extends JPanel implements ItemListener, MouseListener, MouseMotionListener {

    public static int FPS = 60;
    static ToDoPanel toDoModel;
    ToDoData data;
    JList taskList;
    DefaultListModel<String> listModel;
    Images images;
    Graphics g;

    JButton addTaskButton;
    JButton deleteTaskButton;
    JTextFieldWithPrompt newTask;
    JPanel flowPanel;

    boolean isMouseDragging;
    int dragInitial;

    //constructor contains everything graphics related in the class essentially so that it can be added to MainPanel
    public ToDoPanel() throws IOException{

        data = new ToDoData();
        flowPanel = new JPanel();
        Images images = new Images();
        isMouseDragging = false;

        //JList containing JCheckBox
        listModel = new DefaultListModel<>();
        for(String s : data.tasks){
            listModel.addElement(s);
        }
        taskList = new JList<>(listModel);
        taskList.addMouseListener(this);
        taskList.addMouseMotionListener(this);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //button to add tasl to the to do list
        addTaskButton = new JButton("Add");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(newTask.getText().length() <= 40){
                        data.addTask(newTask.getText());
                        listModel.addElement(newTask.getText());
                        newTask.setText("Enter task here...");
                    }
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });
        Border emptyBorder = BorderFactory.createEmptyBorder();
        addTaskButton.setBorder(emptyBorder);

        newTask = new JTextFieldWithPrompt(15, "Enter task here...");

        //button to delete tasks
        deleteTaskButton = new JButton("Remove selected task");
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(!taskList.isSelectionEmpty()){
                        String removeMe = (String)taskList.getSelectedValue();
                        data.removeTask(removeMe);
                        listModel.removeElement(removeMe);
                    }
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });
        deleteTaskButton.setBorder(emptyBorder);

        JLabel title = new JLabel("To Do: ");
        title.setHorizontalAlignment(JLabel.CENTER);

        //layout stuff
        GridBagLayout toDoLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        flowPanel.setLayout(toDoLayout);

        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        toDoLayout.setConstraints(title, c);
        this.flowPanel.add(title);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //smaller borders btwn elements
        JPanel panel1 = new JPanel();
        panel1.add(taskList);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(newTask);
        panel2.add(addTaskButton);

        //gridbag stuff for add button pannel
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        toDoLayout.setConstraints(panel2, c);
        this.flowPanel.add(panel2);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //gridbag for delete button
        JPanel panel3 = new JPanel();
        panel3.add(deleteTaskButton);
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        toDoLayout.setConstraints(panel3, c);
        this.flowPanel.add(panel3);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //gridbag for to do list
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 4;
        c.gridheight = 3;
        toDoLayout.setConstraints(panel1, c);
        this.flowPanel.add(panel1);
        c.gridwidth = GridBagConstraints.REMAINDER;


        panel1.setBackground(new Color(0,0,0,0));
        panel2.setBackground(new Color(0,0,0,0));
        panel3.setBackground(new Color(0,0,0,0));
        flowPanel.setBackground(new Color(0,0,0,0));

        this.setLayout(new FlowLayout());
        this.add(flowPanel);
        //setSize(300, 500);
        setVisible(true);
    }

    public void itemStateChanged(ItemEvent event){
        Object source = event.getItemSelectable();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        try{
            Image background = ImageIO.read(new File("./resources/images/temp_background.png"));
            g.drawImage(background, 0, 0, 800, 600, null);
        }
        catch(Exception e){System.out.println("Error with background: " + e);}
    }

    public void mousePressed(MouseEvent e){
        dragInitial = taskList.getSelectedIndex();
    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){
        if (isMouseDragging) {        
            int dragTarget = taskList.getSelectedIndex();
            String dragElement = listModel.get(dragInitial);
            listModel.remove(dragInitial);
            listModel.add(dragTarget, dragElement);
            try{
                data.removeTask(dragElement);
                data.addTask(dragTarget, dragElement);
            }
            catch(Exception ex){System.out.println("Error dragging: " + ex);}
        }
        isMouseDragging = false;

    }
    public void mouseClicked(MouseEvent e){

    }
    public void mouseDragged(MouseEvent e){
        isMouseDragging = true;
    }
    public void mouseMoved(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }
}

//TO DO: maybe make it not have immediate focus upon start up????? how?
class JTextFieldWithPrompt extends JTextField{

    String placeholder;

    public JTextFieldWithPrompt(){
        super();
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if(getText().equals(placeholder)){
                    setText("");
                }
            }
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText(placeholder);
                }
            }

        });
    }

    public JTextFieldWithPrompt(int cols){
        super(cols);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if(getText().equals(placeholder)){
                    setText("");
                }
            }
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText(placeholder);
                }
            }

        });
    }

    public JTextFieldWithPrompt(int cols, String s){
        super(cols);
        placeholder = s;
        setText(placeholder);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if(getText().equals(placeholder)){
                    setText("");
                }
            }
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText(placeholder);
                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);


    }

    public void setPlaceholder(String s){
        placeholder = s;
    }
}
