import java.util.*;

public class ThePianist {
    static class Piece{
        String piece;
        String composer;
        String key;

        public String getComposer() {
            return composer;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Piece(String piece, String composer, String key) {
            this.piece = piece;
            this.composer = composer;
            this.key = key;
        }
    }
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int number = Integer.parseInt(console.nextLine());
        HashMap<String, Piece> pieceList = new LinkedHashMap<>();
        for (int i = 0; i < number; i++) {
            String[] input = console.nextLine().split("\\|");
            String piece = input[0];
            String composer = input[1];
            String key = input[2];
            Piece singlePiece = new Piece(piece, composer, key);
            pieceList.put(piece, singlePiece);
        }
        String event;
        while (!"Stop".equals(event = console.nextLine())) {
            String command = event.split("\\|")[0];
            String piece = event.split("\\|")[1];

            switch (command) {
                case "Add":
                    String composer = event.split("\\|")[2];
                    String key = event.split("\\|")[3];
                    Piece singlePiece = new Piece(piece, composer, key);
                    if(pieceList.putIfAbsent(piece, singlePiece)==null) {
                        System.out.printf("%s by %s in %s added to the collection!%n", piece, composer, key);
                    }
                    else {
                        System.out.println(piece + " is already in the collection!");
                    }
                    break;
                case "Remove":
                    if(pieceList.remove(piece) != null) {
                        System.out.printf("Successfully removed %s!%n", piece);
                    }
                    else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    }
                    break;
                case "ChangeKey":
                    String newKey = event.split("\\|")[2];
                    if(pieceList.containsKey(piece)){
                        pieceList.get(piece).setKey(newKey);
                        System.out.printf("Changed the key of %s to %s!%n", piece, newKey);
                    }
                    else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    }
                    break;
            }
        }
        pieceList.forEach((k,v) -> System.out.printf("%s -> Composer: %s, Key: %s%n", k, v.getComposer(), v.getKey()));
    }
}
