import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class ToyShop {
    private PriorityQueue<Toy> toyQueue;

    public ToyShop() {
        toyQueue = new PriorityQueue<>();
    }

    public void addToy(Toy toy) {
        toyQueue.add(toy);
    }

    public int getRandomToyId() {
        int totalWeight = 0;
        for (Toy toy : toyQueue) {
            totalWeight += toy.getWeight();
        }

        int randomNumber = (int) (Math.random() * totalWeight) + 1;
        int cumulativeWeight = 0;

        for (Toy toy : toyQueue) {
            cumulativeWeight += toy.getWeight();
            if (randomNumber <= cumulativeWeight) {
                return toy.getId();
            }
        }

        // This line should never be reached
        return -1;
    }

    public void generateResults() {
        try {
            FileWriter writer = new FileWriter("results.txt");

            for (int i = 0; i < 10; i++) {
                int randomToyId = getRandomToyId();
                writer.write(Integer.toString(randomToyId));
                writer.write(System.lineSeparator());
            }

            writer.close();
            System.out.println("Results have been written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the results to the file.");
        }
    }

    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();

        Toy toy1 = new Toy(1, "Игрушка 1", 2);
        Toy toy2 = new Toy(2, "Игрушка 2", 2);
        Toy toy3 = new Toy(3, "Игрушка 3", 6);

        toyShop.addToy(toy1);
        toyShop.addToy(toy2);
        toyShop.addToy(toy3);

        toyShop.generateResults();
    }
}

class Toy implements Comparable<Toy> {
    private int id;
    private String name;
    private int weight;

    public Toy(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Toy other) {
        return Integer.compare(this.weight, other.weight);
    }
}
