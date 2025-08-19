import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> textList = new ArrayList<>();


    //returns a single task as "X. taskName"
    private String viewTask(int taskIdx) {
        return String.format("%d. %s", taskIdx + 1, textList.get(taskIdx).showTask());
    }

    //returns size of list
    private int getListSize() {
        return textList.size();
    }

    public String addTodoTask(String task) {
        ToDo newTask = new ToDo(task);
        textList.add(newTask);
        return String.format(
                "\nAlright, I have added a new todo:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(textList.indexOf(newTask)),
                getListSize()
        );
    }

    public String addDeadlineTask(String task, String completeBy) {
        Deadline newTask = new Deadline(task, completeBy);
        textList.add(newTask);
        return String.format(
                "\nAlright, I have added a new deadline:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(textList.indexOf(newTask)),
                getListSize()
        );
    }

    public String addEventTask(String task, String start, String end) {
        Event newTask = new Event(task, start, end);
        textList.add(newTask);
        return String.format(
                "\nAlright, I have added a new event:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(textList.indexOf(newTask)),
                getListSize()
        );
    }
    public String markAsDone(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1; //account for display vs actual index
            textList.get(actualIdx).markDone();
            return String.format("\nOk! I've marked this task as done:\n%s", viewTask(actualIdx));
        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    public String unmarkAsDone(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1; //account for display vs actual index
            textList.get(actualIdx).unmarkDone();
            return String.format("\nOk! I've removed the mark from this task:\n%s", viewTask(actualIdx));
        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    //returns a list of tasks as "X. taskName" per row
    public String viewList() {
        if (textList.isEmpty()) {
            //Account for empty textList
            return "The list is empty!";
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("\n");

            for (int i = 0; i < textList.size(); i++) {
                msg.append(viewTask(i)).append("\n");
            }

            //Remove last "\n" for formatting purposes
            return msg.substring(0, msg.length() - 1);
        }
    }
}
