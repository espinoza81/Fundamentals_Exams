import java.util.Scanner;

public class SecretChat {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        StringBuilder input = new StringBuilder(console.nextLine());
        String command;
        while (!"Reveal".equals(command = console.nextLine())) {
            String[] commandAttribute = command.split(":\\|:");
            switch (commandAttribute[0]) {
                case "InsertSpace":
                    int index = Integer.parseInt(commandAttribute[1]);
                    input.insert(index, " ");
                    break;
                case "Reverse":
                    StringBuilder strToReverse = new StringBuilder(commandAttribute[1]);
                    index = input.indexOf(String.valueOf(strToReverse));
                    if (index == -1) {
                        System.out.println("error");
                        continue;
                    }
                    else {
                        input.replace(index, index + strToReverse.length(), "");
                        input.append(strToReverse.reverse());
                    }
                    break;
                case "ChangeAll":
                    String substring = commandAttribute[1];
                    String replacement = commandAttribute[2];
                    index = input.indexOf(substring);
                    while (index != -1) {
                        input.replace(index, index + substring.length(), replacement);
                        index = input.indexOf(substring);
                    }
                    break;
            }
            System.out.println(input);
        }
        System.out.println("You have a new text message: " + input);
    }
}
