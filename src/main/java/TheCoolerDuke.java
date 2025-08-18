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

        while (!userMessage.equals("bye")) {

            if (userMessage.equals("list")) {
                //display whole textList on "list" message
                outputHandler(textList.viewList());
            } else {
                //add item to textList otherwise
                outputHandler(textList.addItem(userMessage));
            }
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
