import java.util.Scanner;

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

    public static void storeFeature(Scanner scanner) {
        TaskManager textList = new TaskManager();
        String outputMessage;

        //Split input into command and modifier
        String[] result = inputHandler(scanner);
        String command = result[0];
        String modifier = result.length > 1 ? result[1] : "";

        while (!command.equals("bye")) {
            switch (command) {
            case "list" :
                outputMessage = textList.viewList();
                break;

            case "todo" :
                outputMessage = textList.addTodoTask(modifier.trim());
                break;

            case "deadline" :
                if (modifier.contains("/by")) {
                    String[] params = modifier.split("/by");
                    outputMessage = textList.addDeadlineTask(params[0].trim(), params[1].trim());
                } else {
                    outputMessage = "Dude, your deadline command has the wrong format!";
                }
                break;

            case "event" :
                if (modifier.contains("/from") && modifier.contains("/to")) {
                    String[] params = modifier.split("/from|/to");
                    outputMessage = textList.addEventTask(params[0].trim(), params[1].trim(), params[2].trim());
                } else {
                    outputMessage = "Dude, your event command has the wrong format!";
                }
                break;

            case "mark" :
                //check if mark format is valid
                try {
                    int chosenIdx = Integer.parseInt(modifier.trim());
                    outputMessage = textList.markAsDone(chosenIdx);
                } catch (NumberFormatException e) {
                    outputMessage = "Dude, your mark command has the wrong format!";
                }
                break;

            case "unmark" :
                //check if unmark format is valid
                try {
                    int chosenIdx = Integer.parseInt(modifier.trim());
                    outputMessage = textList.unmarkAsDone(chosenIdx);
                } catch (NumberFormatException e) {
                    outputMessage = "Dude, your unmark command has the wrong format!";
                }
                break;

            default :
                //default response for invalid input
                outputMessage = "What do you mean? Please try again dude";
            }

            //display the output and gather new input
            outputHandler(outputMessage);
            result = inputHandler(scanner);
            command = result[0];
            modifier = result.length > 1 ? result[1] : "";
        }
    }

    public static void main(String[] args) {
        //Initialise scanner for input
        Scanner scanner = new Scanner(System.in);

        System.out.println(lineBreak);
        System.out.println("Hello! I'm TheCoolerDuke");
        System.out.println("What can I do for you?");

        storeFeature(scanner);

        System.out.println(lineBreak);
        System.out.println("Alright, I guess you're done :(\nGoodbye!");
    }
}
