import java.util.Scanner;

public class TheImitationGame {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        StringBuilder input = new StringBuilder(console.nextLine());
        String command;
        while (!"Decode".equals(command = console.nextLine())) {
            String[] commandAttribute = command.split("\\|");
            switch (commandAttribute[0]) {
                case "Move":
                    int letters = Integer.parseInt(commandAttribute[1]);
                    input.append(input.substring(0, letters));
                    input.delete(0, letters);
                    break;
                case "Insert":
                    int index = Integer.parseInt(commandAttribute[1]);
                    String strToInsert = commandAttribute[2];
                    if(index>=0 && index<=input.length()){
                        input.insert(index, strToInsert);
                    }
                    break;
                case "ChangeAll":
                    String oldString = commandAttribute[1];
                    String newString = commandAttribute[2];
                    index = input.indexOf(oldString);
                    while (index != -1) {
                        input.replace(index, index + oldString.length(), newString);
                        index = input.indexOf(oldString,index+newString.length());
                    }
                    break;
            }
        }
        System.out.println("The decrypted message is: " + input);
    }
}
