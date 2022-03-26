import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Pirates {
    static class City{
        private int population;
        private int gold;

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public City(int population, int gold) {
            this.population = population;
            this.gold = gold;
        }
    }
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        HashMap<String, City> citiesList= new LinkedHashMap<>();
        String cities;
        while (!"Sail".equals(cities=console.nextLine())){
            String[] cityAttribute = cities.split("\\|\\|");
            String name = cityAttribute[0];
            int population = Integer.parseInt(cityAttribute[1]);
            int gold = Integer.parseInt(cityAttribute[2]);
            citiesList.putIfAbsent(name, new City(0,0));
            citiesList.get(name).setPopulation(citiesList.get(name).getPopulation()+population);
            citiesList.get(name).setGold(citiesList.get(name).getGold()+gold);
        }
        String command;
        while (!"End".equals(command=console.nextLine())){
            String[] commandAttribute = command.split("=>");
            String event = commandAttribute[0];
            String town = commandAttribute[1];
            switch (event){
                case "Plunder":
                    int people = Integer.parseInt(commandAttribute[2]);
                    int gold = Integer.parseInt(commandAttribute[3]);
                    System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", town, gold, people);
                    if(citiesList.get(town).getGold()-gold==0 || citiesList.get(town).getPopulation()-people==0) {
                        System.out.println(town + " has been wiped off the map!");
                        citiesList.remove(town);
                    }
                    else {
                        citiesList.get(town).setGold(citiesList.get(town).getGold()-gold);
                        citiesList.get(town).setPopulation(citiesList.get(town).getPopulation()-people);
                    }
                    break;
                case "Prosper":
                    gold = Integer.parseInt(commandAttribute[2]);
                    if (gold<0) System.out.println("Gold added cannot be a negative number!");
                    else {
                        citiesList.get(town).setGold(citiesList.get(town).getGold()+gold);
                        System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n", gold, town, citiesList.get(town).getGold());
                    }
                    break;

            }

        }
        if(citiesList.isEmpty()) System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        else {
            System.out.println("Ahoy, Captain! There are " + citiesList.size() + " wealthy settlements to go to:");
            citiesList.forEach((k, v) -> System.out.println(k + " -> Population: " + v.getPopulation() + " citizens, Gold: " + v.getGold() + " kg"));
        }
    }
}
