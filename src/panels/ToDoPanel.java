
import javax.swing.*;
import javax.swing.border.Border;


import java.awt.*;
import java.awt.event.*;

//TO DO: make JList prettier with box outline possibly
//TO DO: actual check boxes in the tasks
//note? someone see if you also occasionally see the tasks becoming three letters and then dot dot dot

public class ToDoPanel extends PanelBase implements ItemListener, MouseListener, MouseMotionListener {

    private static int TODO_WIDTH = 500;
    private static int TODO_HEIGHT = 500;
    private static Color TODO_COLOR = new Color(255, 255, 255, 180);

    public static int FPS = 60;
    static ToDoPanel toDoModel;
    ToDoData data;
    JList<String> taskList; //declare the generic
    DefaultListModel<String> listModel;
    Graphics g;

    JButton addTaskButton;
    JButton deleteTaskButton;
    JTextFieldWithPrompt newTask;
    JPanel flowPanel;
    JPanel putAwayPanel;
    JLabel mainTask;

    boolean isMouseDragging;
    int dragInitial;
    boolean isPutAway;



    //constructor contains everything graphics related in the class essentially so that it can be added to MainPanel
    public ToDoPanel() {
        super(TODO_WIDTH, TODO_HEIGHT, TODO_COLOR);

        data = new ToDoData();
        flowPanel = new JPanel();
        putAwayPanel = new JPanel();
        isMouseDragging = false;
        isPutAway = false;

        //JList containing JCheckBox
        listModel = new DefaultListModel<>();
        for(String s : data.tasks){
            listModel.addElement(s);
        }
        taskList = new JList<String>(listModel);
        taskList.addMouseListener(this);
        taskList.addMouseMotionListener(this);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setMainTask();

        //button to add tasl to the to do list
        addTaskButton = new JButton("Add");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(newTask.getText().length() <= 40 && data.tasks.size() < 20){
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
                setMainTask();
            }
        });
        deleteTaskButton.setBorder(emptyBorder);


        //need to add gaps between components in put away panel
        putAwayPanel.setLayout(new BoxLayout(putAwayPanel, BoxLayout.PAGE_AXIS));
        JPanel emptyPanel = new JPanel();
        putAwayPanel.add(emptyPanel);
        JPanel currentTaskPanel = new JPanel();
        currentTaskPanel.setLayout(new FlowLayout());
        currentTaskPanel.add(new JLabel("Current task: "));
        currentTaskPanel.add(mainTask);
        putAwayPanel.add(currentTaskPanel);

        emptyPanel.setBackground(new Color(0,0,0,0));


        JLabel title = new JLabel("To Do: ");
        title.setHorizontalAlignment(JLabel.CENTER);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(title);

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
        toDoLayout.setConstraints(titlePanel, c);
        this.flowPanel.add(titlePanel);
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

        titlePanel.setBackground(new Color(0,0,0,0));
        panel1.setBackground(new Color(0,0,0,0));
        panel2.setBackground(new Color(0,0,0,0));
        panel3.setBackground(new Color(0,0,0,0));
        flowPanel.setBackground(new Color(0,0,0,0));
        putAwayPanel.setBackground(new Color(0,0,0,0));

        this.add(flowPanel);
    }

    
/*     private void panelSlideTime(){
        isPutAway = !isPutAway;
        if(!isPutAway){
            this.remove(flowPanel);
            this.add(putAwayPanel);
        } else {
            this.remove(putAwayPanel);
            this.add(flowPanel);
        }
        this.revalidate();
        this.repaint();
    } */

    private void setMainTask(){
        if(mainTask == null) mainTask = new JLabel();
        if(!data.tasks.isEmpty()) mainTask.setText(data.tasks.get(0));
        //repaint();
    }

    public void itemStateChanged(ItemEvent event){
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
            taskList.setSelectedIndex(dragTarget);
        }
        isMouseDragging = false;
        setMainTask();

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
