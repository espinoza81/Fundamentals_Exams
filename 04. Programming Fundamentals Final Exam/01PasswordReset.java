import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        StringBuilder password = new StringBuilder(console.nextLine());
        String command;
        while (!"Done".equals(command=console.nextLine())) {
            String[] commandAttribute = command.split("\\s+");
            switch (commandAttribute[0]){
                case "TakeOdd":
                    for(int i=0; i<password.length(); i++){
                        password.deleteCharAt(i);
                    }
                    System.out.println(password);
                    break;
                case "Cut":
                    int index = Integer.parseInt(commandAttribute[1]);
                    int length = Integer.parseInt(commandAttribute[2]);
                    password.delete(index,index+length);
                    System.out.println(password);
                    break;
                case "Substitute":
                    String strToReplace = commandAttribute[1];
                    String strToAdd = commandAttribute[2];
                    if( password.toString().equals(password.toString().replace(strToReplace, strToAdd))) System.out.println("Nothing to replace!");
                    else {
                        password = new StringBuilder(password.toString().replace(strToReplace, strToAdd));
                        System.out.println(password);
                    }
                    break;
            }
        }
        System.out.println("Your password is: " + password);
    }
}
