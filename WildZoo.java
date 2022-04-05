import java.util.*;

public class WildZoo {
    static class Animal {
        String animalName;
        int food;
        String area;

        public String getAnimalName() {
            return animalName;
        }

        public void setAnimalName(String animalName) {
            this.animalName = animalName;
        }

        public int getFood() {
            return food;
        }

        public void setFood(int food) {
            this.food = food;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Animal(String animalName, int food, String area) {
            this.animalName = animalName;
            this.food = food;
            this.area = area;
        }
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input;
        Map<String, Animal> animalList = new LinkedHashMap<>();
        HashMap<String, Integer> areaHungry = new LinkedHashMap<>();
        while (!"EndDay".equals(input = console.nextLine())) {
            String command = input.substring(0, input.indexOf(':'));
            String[] animalInfo = input.substring(input.indexOf(':') + 2).split("-");
            String animalName = animalInfo[0];
            int food = Integer.parseInt(animalInfo[1]);
            if (command.equals("Add")) {
                String area = animalInfo[2];
                Animal singleAnimal = new Animal(animalName, food, area);
                if (animalList.putIfAbsent(animalName, singleAnimal) == null) {
                    areaHungry.put(area, areaHungry.get(area) != null ? areaHungry.get(area) + 1 : 1);
                } else {
                    animalList.get(animalName).setFood(animalList.get(animalName).getFood() + food);
                }
            } else {
                if (animalList.containsKey(animalName)) {
                    int tempFood = animalList.get(animalName).getFood() - food;
                    if (tempFood <= 0) {
                        String area = animalList.get(animalName).getArea();
                        areaHungry.put(area, areaHungry.get(area) - 1);
                        animalList.remove(animalName);
                        System.out.println(animalName + " was successfully fed");
                    } else {
                        animalList.get(animalName).setFood(tempFood);
                    }
                }
            }
        }
        System.out.println("Animals:");
        animalList.entrySet().stream().
                sorted(Comparator.comparingInt(s -> s.getValue().getFood())).
                forEach(s-> System.out.printf("%s -> %dg%n", s.getKey(), s.getValue().getFood()));
        System.out.println("Areas with hungry animals:");
        areaHungry.entrySet().stream().sorted((s1, s2) -> Integer.compare(s2.getValue(), s1.getValue())).
                forEach(s -> {
                    if(s.getValue()>0) System.out.printf("%s : %d%n", s.getKey(), s.getValue());
                });
    }
}
