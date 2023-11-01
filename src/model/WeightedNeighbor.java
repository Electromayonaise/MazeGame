package model;

public class WeightedNeighbor<T> {
    private T node;
    private int weight;

    public WeightedNeighbor(T node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public T getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }
}
