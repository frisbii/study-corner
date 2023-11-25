import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.PrintWriter;

class ToDoData{

    public ArrayList<String> tasks;

    //constructor
    public ToDoData() {
        tasks = new ArrayList<String>();
        getListFromFile();
    }

    public void getListFromFile() {
        tasks.clear();
        try {
            String pathname = "./resources/data/TaskList.txt";
            // File file = new File(pathname); //creates file if no file
            BufferedReader reader = new BufferedReader(new FileReader(pathname));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                tasks.add(currentLine);
                currentLine = reader.readLine();
              }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e);
        }
    }

    public void getFileFromList() {
        try {
            PrintWriter out = new PrintWriter("./resources/data/TaskList.txt");
            //should maybe delete all the tasks in the list before re-writing??
            for(int i = 0; i < tasks.size(); i++){
                out.println(tasks.get(i));
            }
            out.close();
        }
        catch (Exception e){
            System.out.println("Error writing to file: " + e);
        }
    }

    //adds task to list and text file
    public void addTask(String task) {
        tasks.add(task);
        getFileFromList();
    }

    //add task to specific index
    public void addTask(int index, String task) {
        tasks.add(index, task);
        getFileFromList();
    }

    //removes task from list and text file
    public void removeTask(String task) {
        if(tasks.contains(task)){
            tasks.remove(task);
            getFileFromList();
        }
    }

}