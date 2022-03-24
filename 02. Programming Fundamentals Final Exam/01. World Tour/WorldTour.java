import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        StringBuilder input = new StringBuilder(console.nextLine());
        String command;
        while (!"Travel".equals(command = console.nextLine())) {
            String[] commandAttribute = command.split(":");
            switch (commandAttribute[0]) {
                case "Add Stop":
                    int index = Integer.parseInt(commandAttribute[1]);
                    String strToInsert = commandAttribute[2];
                    if(index>=0 && index<input.length()){
                        input.insert(index, strToInsert);
                    }
                    break;
                case "Remove Stop":
                    index = Integer.parseInt(commandAttribute[1]);
                    int indexEnd = Integer.parseInt(commandAttribute[2]);
                    if(index>=0 && index<input.length() && indexEnd>=0 && indexEnd<input.length()){
                        input.delete(index, indexEnd+1);
                    }
                    break;
                case "Switch":
                    String oldString = commandAttribute[1];
                    String newString = commandAttribute[2];
                    index = input.indexOf(oldString);
                    while (index != -1) {
                        input.replace(index, index + oldString.length(), newString);
                        index = input.indexOf(oldString,index+newString.length());
                    }
                    break;
            }
            System.out.println(input);
        }
        System.out.println("Ready for world tour! Planned stops: " + input);
    }
}
