import java.util.ArrayList;

/**
 * Acts as a list-based task manager, with ability to add, view, mark/unmark, and delete tasks
 */
public class TaskManager {
    private final ArrayList<Task> taskList = new ArrayList<>();
    
    //returns a single task as "X. taskName"
    private String viewTask(int taskIdx) {
        return String.format("%d. %s", taskIdx + 1, taskList.get(taskIdx).showTask());
    }

    //returns size of list
    private int getListSize() {
        return taskList.size();
    }

    /**
     * Creates a new ToDo task and adds to the task list.
     *
     * @param task Name of the task
     * @return Output string for display
     */
    public String addTodoTask(String task) {
        ToDo newTask = new ToDo(task);
        taskList.add(newTask);
        return String.format(
                "Alright, I have added a new todo:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(taskList.indexOf(newTask)),
                getListSize()
        );
    }

    /**
     * Creates a new Deadline task and adds to the task list.
     *
     * @param task Name of the task
     * @param completeBy Completion date/time of the task as String
     * @return Output string for display
     */
    public String addDeadlineTask(String task, String completeBy) {
        Deadline newTask = new Deadline(task, completeBy);
        taskList.add(newTask);
        return String.format(
                "Alright, I have added a new deadline:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(taskList.indexOf(newTask)),
                getListSize()
        );
    }

    /**
     * Creates a new Event task and adds to the task list.
     *
     * @param task Name of the task
     * @param start Start date/time of the task as String
     * @param end End date/time of the task as String
     * @return Output string for display
     */
    public String addEventTask(String task, String start, String end) {
        Event newTask = new Event(task, start, end);
        taskList.add(newTask);
        return String.format(
                "Alright, I have added a new event:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(taskList.indexOf(newTask)),
                getListSize()
        );
    }

    /**
     * Deletes a task at the specified index from the task list.
     * Checks for invalid index and returns appropriate String response.
     *
     * @param taskIdx The index of the task to be deleted
     * @return Output string for display
     */
    public String deleteTask(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1; //account for display vs actual index
            String deleted = viewTask(taskIdx - 1);
            taskList.remove(actualIdx);

            return String.format(
                    "Alright, I've have removed this task:\n\t%s\nYou now have %d tasks in the list.",
                    deleted,
                    getListSize()
            );

        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    /**
     * Marks a task as done at the specified index from the task list.
     * Checks for invalid index and returns appropriate String response.
     *
     * @param taskIdx The index of the task to be marked
     * @return Output string for display
     */
    public String markTaskAsDone(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1; //account for display vs actual index
            taskList.get(actualIdx).markDone();
            return String.format("Ok! I've marked this task as done:\n%s", viewTask(actualIdx));

        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    /**
     * Unmarks a task as done at the specified index from the task list.
     * Checks for invalid index and returns appropriate String response.
     *
     * @param taskIdx The index of the task to be unmarked
     * @return Output string for display
     */
    public String unmarkTaskAsDone(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1; //account for display vs actual index
            taskList.get(actualIdx).unmarkDone();
            return String.format("Ok! I've removed the mark from this task:\n%s", viewTask(actualIdx));
        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    //returns a list of tasks as "X. taskName" per row

    /**
     * Displays the list of tasks in the task list.
     * Checks for empty list and returns appropriate String response.
     *
     * @return Output string for display
     */
    public String viewList() {
        if (taskList.isEmpty()) {
            //Account for empty taskList
            return "The list is empty!";
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                msg.append(viewTask(i)).append("\n");
            }

            //Remove last "\n" for formatting purposes
            return msg.substring(0, msg.length() - 1);
        }
    }
}
