import java.text.DecimalFormat;
import java.util.*;

public class GalaticZooApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("************ Intergalactic Zoological Tour ************");
        System.out.println("In the year 3025, humanity has ventured beyond the Milky Way, collecting creatures from across the universe.");
        System.out.println("You are a curious intergalactic visitor, eager to explore exotic specimens from distant worlds.");
        System.out.println("Keep your eyes open—rumors whisper of a secret underground betting ring beneath the zoo...");
        System.out.println();

        Zoo zoo = new Zoo("Galactic Zoo");
        zoo.addCreature(new MartianAlien("Zyx", 120, 25));
        zoo.addCreature(new VenusianAlien("Qor", 100, 30));
        zoo.addCreature(new Lion("Simba", 80, 15));
        zoo.addCreature(new Tiger("Shere Khan", 90, 18));
        zoo.addCreature(new Robot("R2-D2", 150, 10));
        zoo.addCreature(new Plant("Venus Flytrap", 50, 0));

        System.out.println("Welcome to the " + zoo.getName() + " guided tour!");
        System.out.println("Follow along as the expert tour guides introduce our unique residents...\n");
        zoo.startTour(sc);

        System.out.println("\nAs you exit the main dome, you notice a hidden door labeled 'Underground Betting Arena'.");
        System.out.println("1. Enter and join the betting ring");
        System.out.println("2. Report it to the authorities and end the tour");
        int choice = readInt(sc, 1, 2);

        if (choice == 1) {
            enterBettingArena(sc, zoo);
        } else {
            System.out.println("\nYou report the illegal betting ring. Security thanks you and escorts you out. Tour over.");
        }

        sc.close();
    }

    private static void enterBettingArena(Scanner sc, Zoo zoo) {
        double balance = 100.0;
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("\nYou slip inside with $" + df.format(balance) + ". Let the games begin!");

        boolean running = true;
        while (running && balance > 0) {
            System.out.println("\n--- Betting Menu ---");
            System.out.println("1. Place a bet");
            System.out.println("2. View creatures");
            System.out.println("3. Upgrade creature stats");
            System.out.println("4. Exit arena (cash out)");
            int menu = readInt(sc, 1, 4);

            switch (menu) {
                case 1:
                    balance = placeBet(sc, zoo, balance);
                    break;
                case 2:
                    zoo.startTour(sc);
                    break;
                case 3:
                    manageCreatures(sc, zoo);
                    break;
                case 4:
                    running = false;
                    System.out.println("\nYou cash out with $" + df.format(balance) + ". Thanks for playing!");
                    break;
            }
        }

        if (balance <= 0) {
            System.out.println("\nYou're broke! The guards remove you from the arena.");
        }
    }

    private static double placeBet(Scanner sc, Zoo zoo, double balance) {
        DecimalFormat df = new DecimalFormat("#.00");
        Creature[] pair = zoo.getRandomFightPair();
        Creature c1 = pair[0], c2 = pair[1];

        System.out.println("\nNext fight: " + c1.getName() + " vs " + c2.getName());
        System.out.println("Who do you bet on?");
        System.out.println("1. " + c1.getName());
        System.out.println("2. " + c2.getName());
        int pick = readInt(sc, 1, 2);

        System.out.print("Enter bet amount (you have $" + df.format(balance) + "): ");
        double bet = readDouble(sc, 0, balance);

        Creature winner = zoo.simulateFightReturnWinner(c1, c2);
        System.out.println("The winner is: " + winner.getName());

        if ((pick == 1 && winner == c1) || (pick == 2 && winner == c2)) {
            balance += bet;
            System.out.println("You won $" + df.format(bet) + "! New balance: $" + df.format(balance));
        } else {
            balance -= bet;
            System.out.println("You lost $" + df.format(bet) + ". New balance: $" + df.format(balance));
        }
        return balance;
    }

    private static void manageCreatures(Scanner sc, Zoo zoo) {
        ArrayList<Creature> list = zoo.getCreatures();
        System.out.println("\n--- Creature Upgrade ---");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i).getInfo());
        }
        System.out.print("Select a creature to upgrade (or 0 to cancel): ");
        int idx = readInt(sc, 0, list.size());
        if (idx == 0) return;

        Creature c = list.get(idx - 1);
        System.out.println("1. Change health");
        System.out.println("2. Change attack power");
        int choice = readInt(sc, 1, 2);
        if (choice == 1) {
            System.out.print("Enter new health value: ");
            int newHealth = readInt(sc, 1, Integer.MAX_VALUE);
            c.setHealth(newHealth);
        } else {
            System.out.print("Enter new attack power: ");
            int newAttack = readInt(sc, 0, Integer.MAX_VALUE);
            c.setAttackPower(newAttack);
        }
        System.out.println("Updated: " + c.getInfo());
    }

    private static int readInt(Scanner sc, int min, int max) {
        int val;
        while (true) {
            try {
                System.out.print("> ");
                val = Integer.parseInt(sc.nextLine().trim());
                if (val < min || val > max) throw new NumberFormatException();
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    private static double readDouble(Scanner sc, double min, double max) {
        double val;
        while (true) {
            try {
                System.out.print("> ");
                val = Double.parseDouble(sc.nextLine().trim());
                if (val < min || val > max) throw new NumberFormatException();
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        }
    }
}

class Zoo {
    private final String name;
    private final ArrayList<Creature> creatures;
    private final Random rand = new Random();

    public Zoo(String name) {
        this.name = name;
        this.creatures = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCreature(Creature c) {
        creatures.add(c);
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void startTour(Scanner sc) {
        int station = 1;
        for (Creature c : creatures) {
            System.out.println("Exhibit " + station++ + ": " + c.getName());
            System.out.println("  " + c.getInfo());
            if (c instanceof MartianAlien) {
                System.out.println("  Insight: This Martian harnesses plasma energy to defend its territory.");
            } else if (c instanceof VenusianAlien) {
                System.out.println("  Insight: Venusian biology produces corrosive acids in its vascular system.");
            } else if (c instanceof Lion) {
                System.out.println("  Insight: Lions are social predators that coordinate in prides.");
            } else if (c instanceof Tiger) {
                System.out.println("  Insight: Each tiger's stripe pattern is as unique as a fingerprint.");
            } else if (c instanceof Robot) {
                System.out.println("  Insight: This robot was engineered for interstellar reconnaissance missions.");
            } else if (c instanceof Plant) {
                System.out.println("  Insight: Venus Flytraps can digest small creatures in nutrient-poor soil.");
            }

            System.out.print("Ready to move onto the next exhibit? ");
            sc.nextLine();

            System.out.println();
        }
        System.out.println("End of tour. Thank you for exploring the cosmic menagerie!");
    }

    public Creature[] getRandomFightPair() {
        ArrayList<Creature> fighters = new ArrayList<>();
        for (Creature c : creatures) {
            if (c.getAttackPower() > 0) fighters.add(c);
        }
        Creature c1 = fighters.get(rand.nextInt(fighters.size()));
        Creature c2;
        do {
            c2 = fighters.get(rand.nextInt(fighters.size()));
        } while (c2 == c1);
        return new Creature[]{c1, c2};
    }

    public Creature simulateFightReturnWinner(Creature c1, Creature c2) {
        int h1 = c1.getHealth(), h2 = c2.getHealth();
        System.out.println("FIGHT!");
        while (c1.getHealth() > 0 && c2.getHealth() > 0) {
            int d1 = c1.attack();
            c2.takeDamage(d1);
            System.out.println(c1.getName() + " hits " + c2.getName() + " for " + d1 + " damage (" + c2.getHealth() + " HP left)");
            if (c2.getHealth() <= 0) break;
            int d2 = c2.attack();
            c1.takeDamage(d2);
            System.out.println(c2.getName() + " hits " + c1.getName() + " for " + d2 + " damage (" + c1.getHealth() + " HP left)");
        }
        Creature winner = c1.getHealth() > 0 ? c1 : c2;
        c1.setHealth(h1);
        c2.setHealth(h2);
        return winner;
    }
}

class Creature {
    protected String name;
    protected int health;
    protected int attackPower;

    public Creature(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getInfo() {
        return name + " (HP: " + health + ", ATK: " + attackPower + ")";
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int attack() {
        return attackPower;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }
}

class Alien extends Creature {
    public Alien(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getInfo() {
        return "Alien - " + super.getInfo();
    }
}

class MartianAlien extends Alien {
    public MartianAlien(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getInfo() {
        return "Martian " + super.getInfo();
    }
}

class VenusianAlien extends Alien {
    public VenusianAlien(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getInfo() {
        return "Venusian " + super.getInfo();
    }
}

class Animal extends Creature {
    public Animal(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getInfo() {
        return "Animal - " + super.getInfo();
    }
}

class Lion extends Animal {
    public Lion(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getInfo() {
        return "Lion " + super.getInfo();
    }
}

class Tiger extends Animal {
    public Tiger(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getInfo() {
        return "Tiger " + super.getInfo();
    }
}

class Robot extends Creature {
    public Robot(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getInfo() {
        return "Robot - " + super.getInfo();
    }
}

class Plant extends Creature {
    public Plant(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getInfo() {
        return "Plant - " + name + " (HP: " + health + ")";
    }

    @Override
    public int attack() {
        return 0;
    }
}
