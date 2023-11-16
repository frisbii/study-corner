
import javax.swing.*;
import javax.swing.border.Border;

import javax.imageio.*;
import java.io.File;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//TO DO: maybe make a pretty background outline for some of the JPanels? :)
//TO DO: be able to rearrange the tasks
//TO DO: actual check boxes in the tasks
public class ToDoPanel extends JPanel implements ItemListener {

    public static int FPS = 60;
    static ToDoPanel toDoModel;
    ToDoData data;
    JList taskList;
    JButton addTaskButton;
    JButton deleteTaskButton;
    JTextField newTask;
    JPanel flowPanel;

    //constructor contains everything graphics related in the class essentially so that it can be added to MainPanel
    public ToDoPanel() throws IOException{

        data = new ToDoData();
        flowPanel = new JPanel();

        //JList containing JCheckBox
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(String s : data.tasks){
            listModel.addElement(s);
        }
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addTaskButton = new JButton("Add");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    data.addTask(newTask.getText());
                    listModel.addElement(newTask.getText());
                    newTask.setText("Enter task here...");
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });
        Border emptyBorder = BorderFactory.createEmptyBorder();
        addTaskButton.setBorder(emptyBorder);

        newTask = new JTextField(15);
        //apparently this makes it only accept 15 characters
        newTask.setText("Enter task here...");

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
        try{
            if(source == addTaskButton) data.addTask(newTask.getText());
        }
        catch(Exception e) {System.out.println("Error: " + e);}
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        try{
            Image background = ImageIO.read(new File("./resources/images/temp_background.png"));
            g.drawImage(background, 0, 0, 800, 600, null);
        }
        catch(Exception e){System.out.println("Error: " + e);}
    }
}

//TO DO: finish this so I can have nice placeholder text :)))
class JTextFieldWithPrompt extends JTextField{

    String placeholder;

    public JTextFieldWithPrompt(String s){
        placeholder = s;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

    }

    public void setPlaceholder(String s){
        placeholder = s;
    }
}
