import java.util.PriorityQueue;

public class ToyShop {
    private String[] ids;
    private String[] names;
    private int[] frequencies;

    public ToyShop(String[] ids, String[] names, int[] frequencies) {
        this.ids = ids;
        this.names = names;
        this.frequencies = frequencies;
    }

    public void addToyToCollection() {
        PriorityQueue<Toy> toyCollection = new PriorityQueue<>();

        for (int i = 0; i < ids.length; i++) {
            Toy toy = new Toy(ids[i], names[i], frequencies[i]);
            toyCollection.add(toy);
        }

        System.out.println("Toys added to the collection:");
        while (!toyCollection.isEmpty()) {
            Toy toy = toyCollection.poll();
            System.out.println(toy);
        }
    }

    public static void main(String[] args) {
        String[] ids = {"1", "2", "3"};
        String[] names = {"Teddy Bear", "Lego Set", "Doll"};
        int[] frequencies = {3, 2, 4};

        ToyShop toyShop = new ToyShop(ids, names, frequencies);
        toyShop.addToyToCollection();
    }
}

class Toy implements Comparable<Toy> {
    private String id;
    private String name;
    private int frequency;

    public Toy(String id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Toy other) {
        return Integer.compare(this.frequency, other.frequency);
    }

    @Override
    public String toString() {
        return "Toy [id=" + id + ", name=" + name + ", frequency=" + frequency + "]";
    }
}
