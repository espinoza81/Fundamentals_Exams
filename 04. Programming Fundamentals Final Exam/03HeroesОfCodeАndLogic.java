import java.util.*;

public class HeroesOfCodeAndLogic {
    static class Hero {
        String name;
        int hitPoints;
        int manaPoints;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHitPoints() {
            return hitPoints;
        }

        public void setHitPoints(int hitPoints) {
            this.hitPoints = hitPoints;
        }

        public int getManaPoints() {
            return manaPoints;
        }

        public void setManaPoints(int manaPoints) {
            this.manaPoints = manaPoints;
        }

        public Hero(String name, int hitPoints, int manaPoints) {
            this.name = name;
            this.hitPoints = hitPoints;
            this.manaPoints = manaPoints;
        }
    }
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int number = Integer.parseInt(console.nextLine());
        HashMap<String, Hero> heroList = new LinkedHashMap<>();
        for(int i =0; i<number; i++){
            String[] heroAttribute = console.nextLine().split(" ");
            Hero singleHero = new Hero(heroAttribute[0], Integer.parseInt(heroAttribute[1]),Integer.parseInt(heroAttribute[2]));
            heroList.put(heroAttribute[0], singleHero);
        }
        int maxHitPoints = 100;
        int maxManaPoints = 200;
        String input;
        while (!"End".equals(input=console.nextLine())){
            String[] commandAttributes = input.split(" - ");
            String command = commandAttributes[0];
            String name = commandAttributes[1];
            switch (command){
                case "CastSpell":
                    int manaNeeded = Integer.parseInt(commandAttributes[2]);
                    String spell = commandAttributes[3];
                    if(heroList.get(name).getManaPoints() - manaNeeded >= 0) {
                        heroList.get(name).setManaPoints(heroList.get(name).getManaPoints() - manaNeeded);
                        System.out.printf("%s has successfully cast %s and now has %d MP!%n", name, spell, heroList.get(name).getManaPoints());
                    }
                    else System.out.printf("%s does not have enough MP to cast %s!%n", name, spell);
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(commandAttributes[2]);
                    String attacker = commandAttributes[3];
                    if(heroList.get(name).getHitPoints() - damage > 0) {
                        heroList.get(name).setHitPoints(heroList.get(name).getHitPoints() - damage);
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n", name, damage, attacker, heroList.get(name).getHitPoints());
                    }
                    else {
                        heroList.remove(name);
                        System.out.printf("%s has been killed by %s!%n", name, attacker);
                    }
                    break;
                case "Recharge":
                    int mana = Integer.parseInt(commandAttributes[2]);
                    if(heroList.get(name).getManaPoints()+mana < maxManaPoints){
                        heroList.get(name).setManaPoints(heroList.get(name).getManaPoints()+mana);
                    }
                    else {
                        mana = 200 - heroList.get(name).getManaPoints();
                        heroList.get(name).setManaPoints(200);
                    }
                    System.out.printf("%s recharged for %d MP!%n", name, mana);
                    break;
                case "Heal":
                    int hit = Integer.parseInt(commandAttributes[2]);
                    if(heroList.get(name).getHitPoints()+hit < maxHitPoints){
                        heroList.get(name).setHitPoints(heroList.get(name).getHitPoints()+hit);
                    }
                    else {
                        hit = 100 - heroList.get(name).getHitPoints();
                        heroList.get(name).setHitPoints(100);
                    }
                    System.out.printf("%s healed for %d HP!%n", name, hit);
                    break;
            }
        }
        heroList.forEach((k,v) -> {
            System.out.println(k);
            System.out.println("  HP: " + v.getHitPoints());
            System.out.println("  MP: " + v.getManaPoints());
        });
    }
}
