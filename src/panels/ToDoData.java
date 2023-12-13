import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.io.PrintWriter;

class ToDoData{

    public LinkedList<String> tasks;
    private String pathname = "./resources/data/TaskList.txt";

    /**
     * instantiates tasks, creates textFile and then puts strings in the list if the file is not empty
     */
    public ToDoData() {
        tasks = new LinkedList<String>();
        File textFile = new File(pathname);
        if(textFile.length() != 0) getListFromFile();
    }

    /**
     * fills the ArrayList tasks with strings by reading the task list file line by line
     */
    private void getListFromFile() {
        tasks.clear();
        try {
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

    /**
     * edits the file TaskList.txt based on what is in the tasks ArrayList
     */
    public void getFileFromList() {
        try {
            PrintWriter out = new PrintWriter("./resources/data/TaskList.txt");
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

    //add task at a specific index to both list and text file
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