package thecoolerduke.main;

import java.nio.file.Paths;

import thecoolerduke.feature.TaskHistoryManager;
import thecoolerduke.feature.TaskManager;

/**
 * A chatbot that helps keep track of your tasks, with persistent storage functionality.
 */
public class TheCoolerDuke {
    private static final String HOME_DIR = System.getProperty("user.dir");
    private static final String PATH_DIR = Paths.get(
            HOME_DIR, "src", "main", "data", "TaskHistory.txt"
    ).toString();
    private final String lineBreak = "_".repeat(72);
    private final Scanner scanner;
    private final TaskManager tm;
    /**
     * Initialises the chatbot.
     *
     * @param scanner Scanner object for handling text input by the user
     */
    public TheCoolerDuke(Scanner scanner) {
        TaskHistoryManager thm = new TaskHistoryManager(PATH_DIR);
        this.tm = new TaskManager(thm);
        this.scanner = scanner;
    }

    private String[] inputHandler(Scanner scanner) {
        System.out.println(lineBreak);
        System.out.print("User >>>\n");

        //Split all inputs into [command, modifier]
        return scanner.nextLine().trim().split(" ", 2);
    }

    private void outputHandler(String msg) {
        System.out.println(lineBreak);
        System.out.println("Bot >>>\n" + msg);
    }

    /**
     * Provides the Task Manager feature for the chatbot.
     */
    private void taskManagerFeature(Scanner scanner) {
        String outputMessage;

        //Startup task manager and display output message
        outputHandler(tm.startupTaskManager());

        //Split input into command and modifier
        String[] input = inputHandler(scanner);
        String command = input[0];
        String modifier = input.length > 1 ? input[1] : "";

        while (!command.equals("bye")) {
            Command result = Command.validateCommand(command);

            if (result == null) {
                //command not recognised
                outputMessage = "What do you mean? Please try again...";
            } else {
                //valid command, execute
                outputMessage = result.execute(new String[]{modifier}, tm);
            }

            //display the output and gather new input
            outputHandler(outputMessage);
            input = inputHandler(scanner);
            command = input[0];
            modifier = input.length > 1 ? input[1] : "";
        }
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        System.out.println(lineBreak);
        System.out.println("Hello! I'm TheCoolerDuke");
        System.out.println("What can I do for you?");

        taskManagerFeature(scanner);

        System.out.println(lineBreak);
        System.out.println("Alright, I guess you're done :(\nGoodbye!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new TheCoolerDuke(scanner).run();
    }
}
