package taskFeature;

import org.junit.jupiter.api.Test;
import taskFeature.tasks.Deadline;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskHistoryManagerTest {

    @Test
    public void testRetrieveTaskHistory() {
        String pathDir = Paths.get("src", "test", "resources", "TaskHistoryRetrieve.txt").toString();
        TaskHistoryManager thm = new TaskHistoryManager(pathDir);

        ArrayList<Task> taskList = new ArrayList<>();

        //test file is found
        assertTrue(thm.retrieveTaskHistory(taskList));

        //1 task retrieved
        assertEquals(1, taskList.size());
    }

    @Test
    public void testUpdateHistory() {
        String pathDir = Paths.get("src", "test", "resources", "TaskHistoryUpdate.txt").toString();
        TaskHistoryManager thm = new TaskHistoryManager(pathDir);

        //initialise array of 1 task
        ArrayList<Task> taskList = new ArrayList<>();
        LocalDateTime date = LocalDateTime.parse("2007-12-03T10:15:30");
        taskList.add(new Deadline("getFood", false, date));

        //ensure history updated without error
        assertDoesNotThrow(() -> thm.updateHistory(taskList));


    }

}
