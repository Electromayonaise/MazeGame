package main_module.model.util;

import java.util.List;

/**
 * Represents a generic graph interface.
 *
 * @param <T> The type of nodes in the graph.
 */
public interface Graph<T> {

    /**
     * Adds a node to the graph.
     *
     * @param node The node to be added.
     * @return True if the node is added, false otherwise.
     */
    boolean addNode(T node);

    /**
     * Adds an edge between two nodes with an optional weight.
     *
     * @param node1  The first node.
     * @param node2  The second node.
     * @param weight The weight of the edge.
     * @return True if the edge is added, false otherwise.
     */
    boolean addEdge(T node1, T node2, int weight);

    /**
     * Gets the weight of the edge between two nodes.
     *
     * @param node1 The first node.
     * @param node2 The second node.
     * @return The weight of the edge, or -1 for non-existent or unconnected nodes.
     */
    int getWeight(T node1, T node2);

    /**
     * Checks if node1 influences node2 directly.
     *
     * @param node1 The first node.
     * @param node2 The second node.
     * @return True if node1 influences node2 directly, false otherwise.
     */
    boolean nodeInfluenceDirectly(T node1, T node2);

    /**
     * Checks if the graph is weighted.
     *
     * @return True if the graph is weighted, false otherwise.
     */
    boolean isWeighted();

    /**
     * Checks if the graph is directed.
     *
     * @return True if the graph is directed, false otherwise.
     */
    boolean isDirected();

    /**
     * Gets the neighbors of a given node.
     *
     * @param node The node.
     * @return The list of neighbors of the node.
     */
    List<T> getNeighbors(T node);

    /**
     * Finds the shortest path between two nodes.
     *
     * @param initial      The starting node.
     * @param destination The destination node.
     * @return The shortest path as a list of nodes.
     */
    List<T> getShortestPath(T initial, T destination);
}

