import java.util.Scanner;

/**
 * A chatbot that helps keep track of your tasks, with persistent storage functionality.
 */
public class TheCoolerDuke {
    private static final String lineBreak = "_".repeat(72);

    private static String[] inputHandler(Scanner scanner) {
        System.out.println(lineBreak);
        System.out.print("User >>>\n");

        //Split all inputs into [command, modifier]
        return scanner.nextLine().trim().split(" ",2);
    }

    private static void outputHandler(String msg) {
        System.out.println(lineBreak);
        System.out.println("Bot >>>\n" + msg);
    }

    /**
     * Provides the Task Manager feature for the chatbot.
     *
     * @param scanner Scanner object passed for user inputs
     */
    public static void taskManagerFeature(Scanner scanner) {
        TaskHistoryManager thm = new TaskHistoryManager();
        TaskManager tm = new TaskManager(thm);
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

    public static void main(String[] args) {
        //Initialise scanner for input
        Scanner scanner = new Scanner(System.in);

        System.out.println(lineBreak);
        System.out.println("Hello! I'm TheCoolerDuke");
        System.out.println("What can I do for you?");

        taskManagerFeature(scanner);

        System.out.println(lineBreak);
        System.out.println("Alright, I guess you're done :(\nGoodbye!");
    }
}