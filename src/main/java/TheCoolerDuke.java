import java.util.Scanner;

public class TheCoolerDuke {
    private static final String lineBreak = "_".repeat(72);

    private static String inputHandler(Scanner scanner) {
        System.out.println(lineBreak);
        System.out.print("User >>> ");
        return scanner.nextLine();
    }

    private static void outputHandler(String msg) {
        System.out.println(lineBreak);
        System.out.println("Bot >>> " + msg);
    }

    public static void echoFeature(Scanner scanner) {
        String userMessage = inputHandler(scanner);

        while (!userMessage.equals("bye")) {
            //Echo if not "bye"
            outputHandler("I'll say " + userMessage);

            //Ask for input again
            userMessage = inputHandler(scanner);
        }
    }

    public static void storeFeature(Scanner scanner) {
        TextStorage textList = new TextStorage();
        String userMessage = inputHandler(scanner);
        String outputMessage = "";

        while (!userMessage.equals("bye")) {
            if (userMessage.equals("list")) {
                //display whole textList on "list" message
                outputMessage = textList.viewList();

            } else if (userMessage.startsWith("mark ")) {
                //check if mark format is valid
                try {
                    int chosenIndex = Integer.parseInt(userMessage.substring("mark ".length()));
                    outputMessage = textList.markAsDone(chosenIndex);
                } catch (NumberFormatException e) {
                    outputMessage = "Dude, your mark command has the wrong format!";
                }

            } else if (userMessage.startsWith("unmark ")) {
                //check if unmark format is valid
                try {
                    int chosenIndex = Integer.parseInt(userMessage.substring("unmark ".length()));
                    outputMessage = textList.unmarkAsDone(chosenIndex);
                } catch (NumberFormatException e) {
                    outputMessage = "Dude, your unmark command has the wrong format!";
                }

            } else {
                //add item to textList otherwise
                outputMessage = textList.addItem(userMessage);
            }

            //display the output and gather new input
            outputHandler(outputMessage);
            userMessage = inputHandler(scanner);
        }
    }

    public static void main(String[] args) {
        //Initialise scanner for input
        Scanner scanner = new Scanner(System.in);

        System.out.println(lineBreak);
        System.out.println("Hello! I'm TheCoolerDuke");
        System.out.println("What can I do for you?");

        //echoFeature(scanner);
        storeFeature(scanner);

        System.out.println(lineBreak);
        System.out.println("Alright, I guess you're done :(\nGoodbye!");
    }


}
