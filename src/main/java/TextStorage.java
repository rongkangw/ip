import java.util.ArrayList;

public class TextStorage {
    private final ArrayList<Task> textList = new ArrayList<>();

    public String addItem(String item) {
        textList.add(new Task(item));
        return String.format("i have added task \"%s\"", item);
    }

    public String markAsDone(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1;
            textList.get(actualIdx).markDone();
            return String.format("\nOk! I've marked this task as done:\n%s", viewTask(actualIdx));
        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    public String unmarkAsDone(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1;
            textList.get(actualIdx).unmarkDone();
            return String.format("\nOk! I've removed the mark from this task:\n%s", viewTask(actualIdx));
        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    //returns a single task as "X. taskName"
    public String viewTask(int taskIdx) {
        return String.format("%d. %s", taskIdx + 1, textList.get(taskIdx).showTask());
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
