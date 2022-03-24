import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        String regex = "(=|/)(?<location>[A-Z][a-zA-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher location = pattern.matcher(input);
        List<String> destinations = new ArrayList<>();
        int travelPoints = 0;
        while (location.find()){
            String singleLocation = location.group("location");
            destinations.add(singleLocation);
            travelPoints += singleLocation.length();
        }
        System.out.println("Destinations: " + String.join(", ", destinations));
        System.out.println("Travel Points: " + travelPoints);
    }
}
