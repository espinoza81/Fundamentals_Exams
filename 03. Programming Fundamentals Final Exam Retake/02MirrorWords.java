import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MirrorWords {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        String regex = "(@|#)(?<wordOne>[a-zA-z]{3,})\\1{2}(?<wordTwo>[a-zA-z]{3,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher mirrorWord = pattern.matcher(input);
        int count = 0;
        List<String> mirror = new ArrayList<>();
        while (mirrorWord.find()) {
            count ++;
            StringBuilder wordOne = new StringBuilder(mirrorWord.group("wordOne"));
            StringBuilder wordTwo = new StringBuilder(mirrorWord.group("wordTwo"));
            if(wordOne.reverse().compareTo(wordTwo)==0) {
                wordOne.reverse().append(" <=> ").append(wordTwo);
                mirror.add(wordOne.toString());
            }
        }
        if(count==0) System.out.println("No word pairs found!");
        else System.out.println(count + " word pairs found!");
        if(mirror.size()==0) System.out.println("No mirror words!");
        else {
            System.out.println("The mirror words are: ");
            System.out.println(String.join(", ", mirror));
        }
    }
}
