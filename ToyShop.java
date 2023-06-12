/*Задание 2. Магазин игрушек (Java)
Информация о проекте
Необходимо написать проект, для розыгрыша в магазине игрушек. Функционал
должен содержать добавление новых игрушек и задания веса для выпадения
игрушек.
Как сдавать проект
Для сдачи проекта необходимо создать отдельный общедоступный
репозиторий(Github, gitlub, или Bitbucket). Разработку вести в этом
репозитории, использовать пул реквесты на изменения. Программа должна
запускаться и работать, ошибок при выполнении программы быть не должно.
Программа, может использоваться в различных системах, поэтому необходимо
разработать класс в виде конструктора
Задание
1) Напишите класс-конструктор у которого принимает минимум 3 строки,
содержащие три поля id игрушки, текстовое название и частоту выпадения
игрушки
2) Из принятой строки id и частоты выпадения(веса) заполнить минимум три
массива.
3) Используя API коллекцию: java.util.PriorityQueue добавить элементы в
коллекцию
4) Организовать общую очередь 5) Вызвать Get 10 раз и записать результат в
файл
Подсказка:
В метод put передаете последовательно несколько строк
1 2 конструктор;
2 2 робот;
3 6 кукла.
Метод Get должен случайно вернуть либо “2”, либо “3” и соответствии с весом.
В 20% случаях выходит единица
В 20% двойка
И в 60% тройка.
Критерии оценки
Приложение должно запускаться, записывать значения в файл. */



/*В этом примере класс ToyShop представляет магазин игрушек. Он содержит очередь toyQueue, в которую добавляются игрушки с помощью метода addToy(). 
Метод getRandomToyId() выбирает случайный идентификатор игрушки в соответствии с их весом.
 Метод generateResults() генерирует 10 случайных идентификаторов игрушек и записывает их в файл "results.txt". 
 В примере создаются три игрушки и добавляются в магазин, затем генерируются результат
Когда код выше вызывается, он создает экземпляр ToyShop и добавляет игрушки в магазин. 
Затем он вызывает метод generateResults(), который генерирует 10 случайных идентификаторов игрушек и записывает их в файл "results.txt". 
После выполнения кода, результаты будут записаны в файл "results.txt" и сообщение "Results have been written to the file." будет выведено в консоль. */



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
