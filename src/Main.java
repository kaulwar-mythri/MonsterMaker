import java.lang.reflect.*;
import java.util.*;
class Monster {
    private String eyeColor;
    private String featherColor;
    private String magicalAbilities;
    private int size;
    private int strength;
    private int durability;
    private int weakness;
    private int aggressionLevel;

    public Monster(String eyeColor, String featherColor, String magicalAbilities, int size, int strength, int durability, int weakness, int aggressionLevel) {
        this.eyeColor = eyeColor;
        this.featherColor = featherColor;
        this.magicalAbilities = magicalAbilities;
        this.size = size;
        this.strength = strength;
        this.durability = durability;
        this.weakness = weakness;
        this.aggressionLevel = aggressionLevel;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getFeatherColor() {
        return featherColor;
    }

    public String getMagicalAbilities() {
        return magicalAbilities;
    }

    public int getSize() {
        return size;
    }

    public int getStrength() {
        return strength;
    }

    public int getDurability() {
        return durability;
    }

    public int getWeakness() {
        return weakness;
    }

    public int getAggressionLevel() {
        return aggressionLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return size == monster.size && strength == monster.strength && durability == monster.durability && weakness == monster.weakness && aggressionLevel == monster.aggressionLevel && Objects.equals(eyeColor, monster.eyeColor) && Objects.equals(featherColor, monster.featherColor) && Objects.equals(magicalAbilities, monster.magicalAbilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eyeColor, featherColor, magicalAbilities, size, strength, durability, weakness, aggressionLevel);
    }

}
public class Main {
    public static Monster createFirstGenMonster(Scanner scanner) {
        System.out.println("Give inputs to create a new first generation monster: ");

        System.out.println("Eye color of the monster: ");
        String eyeColor = scanner.nextLine();

        System.out.println("Feather color of the monster: ");
        String featherColor = scanner.nextLine();

        System.out.println("Magical Abilities of the monster: ");
        String magicalAbilities = scanner.nextLine();

        System.out.println("Size of the monster (between 1 to 5 feet): ");
        int size = scanner.nextInt();

        System.out.println("Strength of the monster(between 1 to 10): ");
        int strength = scanner.nextInt();

        System.out.println("Durability of the monster(between 20 to 30 hours): ");
        int durability = scanner.nextInt();

        System.out.println("Weakness of the monster(between 1 to 10): ");
        int weakness = scanner.nextInt();

        System.out.println("Aggression level of the monster(between 1 to 10): ");
        int aggressionLevel = scanner.nextInt();

        return new Monster(eyeColor, featherColor, magicalAbilities, size, strength, durability, weakness, aggressionLevel);
    }

    public static void printMonster(Monster monster) {
        //accessing fields using reflection
        Field[] fields = Monster.class.getDeclaredFields();

        for(Field f: fields) {
            f.setAccessible(true);

            try {
                System.out.println(f.getName() + ": " + f.get(monster));
            } catch(IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static Monster createBabyMonster(Monster par1, Monster par2) {
        Random rand = new Random();

        String eyeColor = rand.nextBoolean() ? par1.getEyeColor() : par2.getEyeColor();
        String featherColor = rand.nextBoolean() ? par1.getFeatherColor() : par2.getFeatherColor();
        String magicalAbilities = rand.nextBoolean() ? par1.getMagicalAbilities() : par2.getMagicalAbilities();
        int size = rand.nextBoolean() ? par1.getSize() : par2.getSize();
        int strength = rand.nextBoolean() ? par1.getStrength() : par2.getStrength();
        int durability = rand.nextBoolean() ? par1.getDurability() : par2.getDurability();
        int weakness = rand.nextBoolean() ? par1.getWeakness() : par2.getWeakness();
        int aggressionLevel = rand.nextBoolean() ? par1.getAggressionLevel() : par2.getAggressionLevel();

        return new Monster(eyeColor, featherColor, magicalAbilities, size, strength, durability, weakness, aggressionLevel);
    }

    public static void main(String[] args) {
        List<Monster> firstGen = new ArrayList<>();
        HashSet<Monster> babyMonsters = new HashSet<>();

        Scanner scanner = new Scanner(System.in);

        for(int i=0; i<5; i++) {
            Monster monster = createFirstGenMonster(scanner);
            firstGen.add(monster);
            printMonster(monster);
        }

        for(int i=0; i<5; i++) {
            for(int j=i+1; j<5; j++) {
                Monster par1 = firstGen.get(i), par2 = firstGen.get(j);

                Monster babyMonster = createBabyMonster(par1, par2);

                babyMonsters.add(babyMonster);
            }
        }

        List<Monster> secondGen = new ArrayList<>(babyMonsters);
        for(int i=0; i<secondGen.size(); i++) {
            printMonster(secondGen.get(i));
        }
        scanner.close();
    }
}