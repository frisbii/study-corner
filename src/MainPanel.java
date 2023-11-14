import javax.swing.JPanel;

public class MainPanel extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    public MainPanel() {
        try{
            ToDoModel toDoModel = new ToDoModel();
        }
        catch(Exception e) {System.out.println("Error: " + e);}
    }
}
