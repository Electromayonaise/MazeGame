package main_module.model.util;

import java.util.List;
/**
 * Represents a generic graph with nodes of type T.
 *
 * @param <T> the type of elements stored in the graph
 */
public interface Graph<T> {

    /**
     * Adds a node to the graph.
     *
     * @param node the node to be added
     * @return true if the node is added, false if it already exists
     */
    boolean addNode(T node);

    /**
     * Adds an edge between two nodes with a specified weight.
     *
     * @param node1  the first node
     * @param node2  the second node
     * @param weight the weight of the edge
     * @return true if the edge is added, false if nodes do not exist
     */
    boolean addEdge(T node1, T node2, int weight);

    /**
     * Gets the weight of the edge between two nodes.
     *
     * @param node1 the first node
     * @param node2 the second node
     * @return the weight of the edge, or a specified value for non-existent or unconnected nodes
     */
    int getWeight(T node1, T node2);

    /**
     * Checks if there is a direct influence between two nodes.
     *
     * @param node1 the first node
     * @param node2 the second node
     * @return true if there is a direct influence, false otherwise
     */
    boolean nodeInfluenceDirectly(T node1, T node2);

    /**
     * Checks if the graph is weighted.
     *
     * @return true if the graph is weighted, false otherwise
     */
    boolean isWeighted();

    /**
     * Checks if the graph is directed.
     *
     * @return true if the graph is directed, false otherwise
     */
    boolean isDirected();

    /**
     * Gets the neighbors of a given node.
     *
     * @param node the node to get neighbors for
     * @return a list of neighbor nodes
     */
    List<T> getNeighbors(T node);

    /**
     * Gets the shortest path between two nodes.
     *
     * @param initial      the starting node
     * @param destination the destination node
     * @return a list representing the shortest path
     */
    List<T> getShortestPath(T initial, T destination);
}
