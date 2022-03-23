import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdAstra {
    static class Food{
        String itemName;
        String expiration;
        int calories;

        public String getFoodName() {
            return itemName;
        }

        public String getExpiration() {
            return expiration;
        }

        public int getCalories() {
            return calories;
        }

        public Food(String itemName, String expiration, int calories) {
            this.itemName = itemName;
            this.expiration = expiration;
            this.calories = calories;
        }
    }
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        String regex = "(\\||#)(?<item>[a-zA-z\\s]+)\\1(?<expiration>\\d{2}/\\d{2}/\\d{2})\\1(?<calories>\\d{1,5})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher food = pattern.matcher(input);
        List<Food> foodList = new ArrayList<>();
        int totalCalories = 0;
        while (food.find()){
            String itemName = food.group("item");
            String expiration = food.group("expiration");
            int calories = Integer.parseInt(food.group("calories"));
            Food singleFood = new Food(itemName, expiration, calories);
            foodList.add(singleFood);
            totalCalories += calories;
        }
        System.out.println("You have food to last you for: " + totalCalories/2000 + " days!");
        foodList.forEach(s-> System.out.printf("Item: %s, Best before: %s, Nutrition: %d%n", s.itemName, s.expiration, s.calories));
    }
}
