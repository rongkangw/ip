import java.util.Arrays;
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

    //checks modifier is of correct format based on provided acceptedModifiers
    //returns the separated String[] version of the modifier if valid
    private static String[] validateAndFormatModifier(String modifier, String[] acceptedModifiers) throws InvalidFormatException {

        //check that modifier contains the accepted modifiers provided
        String missing = "";
        for (String accepted: acceptedModifiers) {
            if (!modifier.contains(accepted)) {
                missing += " " + accepted;
            }
        }

        //throw if any missing modifiers
        if (!missing.isEmpty()) {
            throw new InvalidFormatException(String.format("missing the modifier(s):%s!", missing));
        }

        //format the modifier into its parameters based on the accepted modifiers
        String regex = String.join("|", acceptedModifiers);
        String[] params = modifier.split(regex, acceptedModifiers.length + 1);

        //ensure that none of the parameters are empty, otherwise trim leading and trailing whitespaces as well
        for (int i = 0; i< params.length; i++) {
            if (params[i].isEmpty()) {
                throw new InvalidFormatException("one of the parameters is missing!");
            } else {
                params[i] = params[i].trim();
            }
        }
        return params;
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
                try {
                    result = validateAndFormatModifier(modifier, new String[]{});
                    outputMessage = textList.addTodoTask(result[0]);

                } catch (InvalidFormatException e) {
                    outputMessage = e.getMessage();
                }
                break;

            case "deadline" :
                try {
                    result = validateAndFormatModifier(modifier, new String[]{"/by"});
                    outputMessage = textList.addDeadlineTask(result[0], result[1]);

                } catch (InvalidFormatException e) {
                    outputMessage = e.getMessage();
                }
                break;

            case "event" :
                try {
                    result = validateAndFormatModifier(modifier, new String[]{"/from", "/to"});
                    outputMessage = textList.addEventTask(result[0], result[1], result[2] );

                } catch (InvalidFormatException e) {
                    outputMessage = e.getMessage();
                }
                break;

            case "mark" :
                try {
                    result = validateAndFormatModifier(modifier, new String[]{});
                    int chosenIdx = Integer.parseInt(result[0]); //throws NumberFormatException if not a number
                    outputMessage = textList.markTaskAsDone(chosenIdx);
                } catch (InvalidFormatException e) {
                    outputMessage = e.getMessage();
                } catch (NumberFormatException e) {
                    outputMessage = "that command isn't right: only numbers are allowed!";
                }
                break;

            case "unmark" :
                try {
                    result = validateAndFormatModifier(modifier, new String[]{});
                    int chosenIdx = Integer.parseInt(result[0]); //throws NumberFormatException if not a number
                    outputMessage = textList.unmarkTaskAsDone(chosenIdx);
                } catch (InvalidFormatException e) {
                    outputMessage = e.getMessage();
                } catch (NumberFormatException e) {
                    outputMessage = "that command isn't right: only numbers are allowed!";
                }
                break;

            default :
                //default response for invalid input
                outputMessage = "What do you mean? Please try again...";
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
