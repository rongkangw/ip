import java.util.Scanner;

public class TheCoolerDuke {
    private static final String lineBreak = "_".repeat(72);
    private static final String botLine = "Bot >>>";
    private static final String userLine = "User >>>";

    public static void echoFeature(Scanner scanner) {
        System.out.println(userLine);
        System.out.print("\tYou say: ");
        String userMessage = scanner.nextLine();

        while (!userMessage.equals("bye")) {
            //Echo if not "bye"
            System.out.println(lineBreak);
            System.out.println(botLine);
            System.out.println("\tI say: "+ userMessage);
            System.out.println(lineBreak);

            //Ask for input again
            System.out.println(userLine);
            System.out.print("\tYou say: ");
            userMessage = scanner.nextLine();
            System.out.println(lineBreak);
        }
    }

    public static void main(String[] args) {
        System.out.println(lineBreak);
        System.out.println("Hello! I'm TheCoolerDuke");
        System.out.println("I can echo stuff you say!");
        System.out.println(lineBreak);

        //Initialise scanner for input
        Scanner scanner = new Scanner(System.in);
        echoFeature(scanner);

        System.out.println("Alright, guess you don't wanna play anymore :(\nGoodbye!");
    }


}
