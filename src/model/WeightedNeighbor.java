package model;

/**
 * The {@code WeightedNeighbor} class represents a neighbor in a weighted graph with a specified node and weight.
 *
 * @param <T> The type of the node.
 */
public class WeightedNeighbor<T> {
    private T node;     // The node in the graph
    private int weight;  // The weight of the edge to the neighbor node

    /**
     * Constructs a weighted neighbor with the specified node and weight.
     *
     * @param node   The neighbor node.
     * @param weight The weight of the edge to the neighbor node.
     */
    public WeightedNeighbor(T node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    /**
     * Gets the neighbor node.
     *
     * @return The neighbor node.
     */
    public T getNode() {
        return node;
    }

    /**
     * Gets the weight of the edge to the neighbor node.
     *
     * @return The weight of the edge.
     */
    public int getWeight() {
        return weight;
    }
}

