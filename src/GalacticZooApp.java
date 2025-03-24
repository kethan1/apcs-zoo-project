import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

abstract class TerrestrialAnimal {
    protected String name;
    protected String species;
    protected int age;
    protected double weight;
    protected double height;
    protected String color;
    protected String sound;
    protected String habitat;
    protected int health;
    protected int attackPower;
    
    public TerrestrialAnimal() {
        this("Unknown", "Unknown", 0, 0.0, 0.0, "Unknown", "Silent", "Unknown", 100, 10);
    }
    public TerrestrialAnimal(String name, String species, int age, double weight, double height, String color, String sound, String habitat, int health, int attackPower) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.color = color;
        this.sound = sound;
        this.habitat = habitat;
        this.health = health;
        this.attackPower = attackPower;
    }
    public TerrestrialAnimal(String name, int age) {
        this(name, "Unknown", age, 0.0, 0.0, "Unknown", "Silent", "Unknown", 100, 10);
    }
    public abstract void makeSound();
    public abstract void displayInfo();
    public int attack() {
        Random rand = new Random();
        return attackPower + rand.nextInt(5);
    }
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0)
            health = 0;
    }
    public int getHealth() {
        return health;
    }
}

class EarthAnimal extends TerrestrialAnimal {
    public EarthAnimal() {
        super();
    }
    public EarthAnimal(String name, String species, int age, double weight, double height, String color, String sound, String habitat, int health, int attackPower) {
        super(name, species, age, weight, height, color, sound, habitat, health, attackPower);
    }
    public EarthAnimal(String name, int age) {
        super(name, age);
    }
    @Override
    public void makeSound() {
        System.out.println(name + " the " + species + " roars: '" + sound + "'!");
    }
    @Override
    public void displayInfo() {
        System.out.println("Name: " + name + " | Species: " + species + " | Age: " + age);
        System.out.println("Weight: " + weight + "kg | Height: " + height + "m | Color: " + color);
        System.out.println("Sound: " + sound + " | Habitat: " + habitat);
        System.out.println("Health: " + health + " | Attack Power: " + attackPower);
    }
}

abstract class AlienAnimal {
    protected String name;
    protected String species;
    protected int age;
    protected double weight;
    protected double height;
    protected String originPlanet;
    protected String color;
    protected String sound;
    protected int health;
    protected int attackPower;
    
    public AlienAnimal() {
        this("Unknown Alien", "Unknown", 0, 0.0, 0.0, "Unknown", "Unknown", "Silent", 100, 12);
    }
    public AlienAnimal(String name, String species, int age, double weight, double height, String originPlanet, String color, String sound, int health, int attackPower) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.originPlanet = originPlanet;
        this.color = color;
        this.sound = sound;
        this.health = health;
        this.attackPower = attackPower;
    }
    public AlienAnimal(String name, String species) {
        this(name, species, 0, 0.0, 0.0, "Unknown", "Unknown", "Silent", 100, 12);
    }
    public abstract void makeSound();
    public abstract void displayInfo();
    public int attack() {
        Random rand = new Random();
        return attackPower + rand.nextInt(5);
    }
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0)
            health = 0;
    }
    public int getHealth() {
        return health;
    }
}

class SpaceAnimal extends AlienAnimal {
    public SpaceAnimal() {
        super();
    }
    public SpaceAnimal(String name, String species, int age, double weight, double height, String originPlanet, String color, String sound, int health, int attackPower) {
        super(name, species, age, weight, height, originPlanet, color, sound, health, attackPower);
    }
    public SpaceAnimal(String name, String species) {
        super(name, species);
    }
    @Override
    public void makeSound() {
        System.out.println(name + " the " + species + " emits a cosmic sound: " + sound);
    }
    @Override
    public void displayInfo() {
        System.out.println("Name: " + name + " | Species: " + species + " | Age: " + age);
        System.out.println("Weight: " + weight + "kg | Height: " + height + "m | Origin Planet: " + originPlanet);
        System.out.println("Color: " + color + " | Sound: " + sound);
        System.out.println("Health: " + health + " | Attack Power: " + attackPower);
    }
}

class GalacticZoo {
    private ArrayList<Object> animals;
    
    public GalacticZoo() {
        animals = new ArrayList<>();
        initializeAnimals();
    }
    public GalacticZoo(ArrayList<Object> animals) {
        this.animals = animals;
    }
    public GalacticZoo(int capacity) {
        animals = new ArrayList<>(capacity);
        initializeAnimals();
    }
    private void initializeAnimals() {
        animals.add(new EarthAnimal("Leo", "Lion", 5, 190.0, 1.2, "Golden", "Roar", "Savannah", 100, 20));
        animals.add(new EarthAnimal("Stripe", "Tiger", 4, 220.0, 1.1, "Orange-Black", "Growl", "Jungle", 100, 18));
        animals.add(new EarthAnimal("Trunk", "Elephant", 10, 5400.0, 3.0, "Gray", "Trumpet", "Grasslands", 150, 15));
        animals.add(new EarthAnimal("Swift", "Cheetah", 3, 72.0, 0.9, "Spotted", "Chirp", "Savannah", 80, 22));
        animals.add(new EarthAnimal("Horn", "Rhino", 8, 2300.0, 1.8, "Gray", "Snort", "Plains", 120, 17));
        animals.add(new SpaceAnimal("Zarg", "Xenomorph", 50, 150.0, 1.5, "Zebulon", "Green", "Screech", 110, 25));
        animals.add(new SpaceAnimal("Nebula", "CosmoCat", 30, 80.0, 0.7, "Andromeda", "Blue", "Meow", 90, 20));
        animals.add(new SpaceAnimal("Quark", "AstroBeast", 40, 200.0, 1.2, "Orion", "Red", "Howl", 130, 23));
        animals.add(new SpaceAnimal("Galex", "StarWolf", 35, 100.0, 1.0, "Sirius", "Silver", "Howl", 95, 21));
        animals.add(new SpaceAnimal("Orbit", "MoonRabbit", 2, 15.0, 0.4, "Luna", "White", "Squeak", 70, 15));
    }
    public void listAnimals() {
        for (Object animal : animals) {
            if (animal instanceof EarthAnimal)
                ((EarthAnimal) animal).displayInfo();
            else if (animal instanceof SpaceAnimal)
                ((SpaceAnimal) animal).displayInfo();
            System.out.println("-------------------------------------");
        }
    }
    public Object findAnimalByName(String name) {
        for (Object animal : animals) {
            if (animal instanceof EarthAnimal && ((EarthAnimal) animal).name.equalsIgnoreCase(name))
                return animal;
            if (animal instanceof SpaceAnimal && ((SpaceAnimal) animal).name.equalsIgnoreCase(name))
                return animal;
        }
        return null;
    }
    public void battle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the terrestrial animal: ");
        String terraName = scanner.nextLine();
        System.out.print("Enter the name of the alien animal: ");
        String alienName = scanner.nextLine();
        EarthAnimal terrestrial = null;
        SpaceAnimal alien = null;
        for (Object animal : animals) {
            if (animal instanceof EarthAnimal && ((EarthAnimal) animal).name.equalsIgnoreCase(terraName))
                terrestrial = (EarthAnimal) animal;
            if (animal instanceof SpaceAnimal && ((SpaceAnimal) animal).name.equalsIgnoreCase(alienName))
                alien = (SpaceAnimal) animal;
        }
        if (terrestrial == null || alien == null) {
            System.out.println("One or both animals not found. Please try again.");
            return;
        }
        System.out.println("Battle begins: " + terrestrial.name + " vs " + alien.name);
        while (terrestrial.getHealth() > 0 && alien.getHealth() > 0) {
            int damageToAlien = terrestrial.attack();
            alien.takeDamage(damageToAlien);
            System.out.println(terrestrial.name + " attacks " + alien.name + " for " + damageToAlien + " damage. " + alien.name + "'s health: " + alien.getHealth());
            if (alien.getHealth() <= 0)
                break;
            int damageToTerra = alien.attack();
            terrestrial.takeDamage(damageToTerra);
            System.out.println(alien.name + " attacks " + terrestrial.name + " for " + damageToTerra + " damage. " + terrestrial.name + "'s health: " + terrestrial.getHealth());
        }
        if (terrestrial.getHealth() > 0)
            System.out.println(terrestrial.name + " wins the battle!");
        else if (alien.getHealth() > 0)
            System.out.println(alien.name + " wins the battle!");
        else
            System.out.println("Both animals have perished in battle!");
    }
    public void interact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Zane, your tour guide for today's adventure at the Galactic Zoo.");
        System.out.println("Welcome to a one-of-a-kind experience featuring amazing terrestrial and alien animals!");
        System.out.println("Here are some of our stars:");
        for (Object animal : animals) {
            if (animal instanceof EarthAnimal) {
                EarthAnimal ea = (EarthAnimal) animal;
                System.out.println("Terrestrial: " + ea.name + " the " + ea.species);
            } else if (animal instanceof SpaceAnimal) {
                SpaceAnimal sa = (SpaceAnimal) animal;
                System.out.println("Alien: " + sa.name + " the " + sa.species);
            }
        }
        boolean continueTour = true;
        while (continueTour) {
            System.out.println("\nWould you like to know more about any animal? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                System.out.print("Enter the name of the animal: ");
                String animalName = scanner.nextLine();
                Object animal = findAnimalByName(animalName);
                if (animal != null) {
                    if (animal instanceof EarthAnimal)
                        ((EarthAnimal) animal).displayInfo();
                    else if (animal instanceof SpaceAnimal)
                        ((SpaceAnimal) animal).displayInfo();
                } else {
                    System.out.println("Sorry, I couldn't find that animal.");
                }
            }
            System.out.println("\nWould you like to hear an animal sound? (yes/no)");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                System.out.print("Enter the name of the animal: ");
                String animalName = scanner.nextLine();
                Object animal = findAnimalByName(animalName);
                if (animal != null) {
                    if (animal instanceof EarthAnimal)
                        ((EarthAnimal) animal).makeSound();
                    else if (animal instanceof SpaceAnimal)
                        ((SpaceAnimal) animal).makeSound();
                } else {
                    System.out.println("Sorry, I couldn't find that animal.");
                }
            }
            System.out.println("\nWould you like to see a battle between a terrestrial and an alien animal? (yes/no)");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes"))
                battle();
            System.out.println("\nWould you like to continue the tour? (yes/no)");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("no"))
                continueTour = false;
        }
        System.out.println("Thank you for joining the Galactic Zoo tour. Goodbye!");
        scanner.close();
    }
}

public class GalacticZooApp {
    public static void main(String[] args) {
        GalacticZoo zoo = new GalacticZoo();
        zoo.interact();
    }
}
