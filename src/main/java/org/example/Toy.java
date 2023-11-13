package org.example;
import java.util.concurrent.atomic.AtomicInteger;

public class Toy {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int id;
    private String name;
    private int weight;

    public Toy(int id, String name, int weight) {
        this.id = COUNTER.getAndIncrement();
        this.name = name;
        this.weight = weight;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("id - %d. Имя - %s. Вес - %d. \n", id, name, weight);
    }

}
