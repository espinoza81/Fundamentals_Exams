import java.util.*;

public class PlantDiscovery {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int number = Integer.parseInt(console.nextLine());
        HashMap<String, Integer> plantList = new LinkedHashMap<>();
        for (int i = 0; i < number; i++) {
            String[] input = console.nextLine().split("<->");
            String plant = input[0];
            int rarity = Integer.parseInt(input[1]);
            plantList.put(plant, rarity);
        }
        String event;
        HashMap<String, List<Double>> plantRating = new LinkedHashMap<>();
        while (!"Exhibition".equals(event = console.nextLine())) {
            String command = event.substring(0, event.indexOf(":"));
            String plant = event.substring(event.indexOf(":") + 2, (event.contains("-"))? event.indexOf("-") - 1: event.length());
            String ratingRarity = event.substring(event.indexOf("-") + 2);
            if (!plantList.containsKey(plant)) {
                System.out.println("error");
                continue;
            }
            switch (command) {
                case "Rate":
                    double rating = Double.parseDouble(ratingRarity);
                    plantRating.putIfAbsent(plant, new ArrayList<>());
                    plantRating.get(plant).add(rating);
                    break;
                case "Update":
                    int newRarity = Integer.parseInt(ratingRarity);
                    plantList.put(plant, newRarity);
                    break;
                case "Reset":
                    plantRating.remove(plant);
                    break;
            }
        }
        System.out.println("Plants for the exhibition:");
        final OptionalDouble[] average_rating = new OptionalDouble[1];
        plantList.forEach((k,v) -> {
            if(plantRating.containsKey(k)) average_rating[0] = plantRating.get(k).stream().mapToDouble(d -> d).average();
            else average_rating[0] = OptionalDouble.of(0.00);
            System.out.printf("- %s; Rarity: %d; Rating: %.2f%n", k, v, average_rating[0].getAsDouble());
        });
    }
}
