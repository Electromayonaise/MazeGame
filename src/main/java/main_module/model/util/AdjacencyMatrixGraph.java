package main_module.model.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an adjacency matrix implementation of a graph.
 *
 * @param <T> The type of the nodes in the graph.
 */
public class AdjacencyMatrixGraph<T> implements Graph<T> {

    /**
     * Represents the dynamic matrix to store graph information.
     */
    private ArrayList<ArrayList<Integer>> dynamicMatrix;

    /**
     * Represents a map to store nodes and their corresponding matrix indices.
     */
    private LinkedHashMap<T, Integer> map;

    /**
     * Indicates whether the graph is weighted.
     */
    private boolean weighted;

    /**
     * Indicates whether the graph is directed.
     */
    private boolean directed;

    /**
     * Constructs an AdjacencyMatrixGraph with the specified characteristics.
     *
     * @param weighted  Indicates whether the graph is weighted.
     * @param directed Indicates whether the graph is directed.
     */
    public AdjacencyMatrixGraph(boolean weighted, boolean directed) {
        dynamicMatrix = new ArrayList<>();
        map = new LinkedHashMap<>();
        this.weighted = weighted;
        this.directed = directed;
    }

    /**
     * Adds a node to the graph.
     *
     * @param node The node to be added.
     * @return True if the node is added, false otherwise.
     */
    @Override
    public boolean addNode(T node) {
        boolean flag = false;
        if (!map.containsKey(node)) {
            int assignedRow = dynamicMatrix.size();
            map.put(node, assignedRow);
            addOneColumnAndRow();
            flag = true;
        }
        return flag;
    }

    /**
     * Adds one column and row to the dynamic matrix.
     */
    public void addOneColumnAndRow() {
        int initialCapacity = dynamicMatrix.size();
        dynamicMatrix.add(new ArrayList<>(initialCapacity));

        for (int i = 0; i < initialCapacity; i++) {
            dynamicMatrix.get(dynamicMatrix.size() - 1).add(0);
        }

        for (ArrayList<Integer> subList : dynamicMatrix) {
            subList.add(0);
        }
    }

    /**
     * Adds an edge between two nodes with an optional weight.
     *
     * @param node1  The first node.
     * @param node2  The second node.
     * @param weight The weight of the edge.
     * @return True if the edge is added, false otherwise.
     */
    @Override
    public boolean addEdge(T node1, T node2, int weight) {
        boolean flag = false;
        if (map.containsKey(node1) && map.containsKey(node2)) {
            int rowOfNode1 = map.get(node1);
            int columnOfNode2 = map.get(node2);

            if (weighted) {
                dynamicMatrix.get(rowOfNode1).set(columnOfNode2, weight);
            } else {
                int currentVal = dynamicMatrix.get(rowOfNode1).get(columnOfNode2);
                dynamicMatrix.get(rowOfNode1).set(columnOfNode2, ++currentVal);
            }

            flag = true;

            if (!directed) {
                int rowOfNode2 = map.get(node2);
                int columnOfNode1 = map.get(node1);

                if (weighted) {
                    dynamicMatrix.get(rowOfNode2).set(columnOfNode1, weight);
                } else {
                    int currentVal = dynamicMatrix.get(rowOfNode2).get(columnOfNode1);
                    dynamicMatrix.get(rowOfNode2).set(columnOfNode1, ++currentVal);
                }
            }
        }

        return flag;
    }

    /**
     * Checks if node1 influences node2 directly.
     *
     * @param node1 The first node.
     * @param node2 The second node.
     * @return True if node1 influences node2 directly, false otherwise.
     */
    @Override
    public boolean nodeInfluenceDirectly(T node1, T node2) {
        boolean flag = false;
        if (map.containsKey(node1) && map.containsKey(node2)) {
            int rowOfNode1 = map.get(node1);
            int columnOfNode2 = map.get(node2);

            if (weighted) {
                flag = dynamicMatrix.get(rowOfNode1).get(columnOfNode2) > 0;
            } else {
                int currentVal = dynamicMatrix.get(rowOfNode1).get(columnOfNode2);
                flag = currentVal > 0 || currentVal > 0;
            }
        }
        return flag;
    }

    /**
     * Gets the weight of the edge between two nodes.
     *
     * @param node1 The first node.
     * @param node2 The second node.
     * @return The weight of the edge, or -1 for non-existent or unconnected nodes.
     */
    @Override
    public int getWeight(T node1, T node2) {
        if (map.containsKey(node1) && map.containsKey(node2)) {
            int rowOfNode1 = map.get(node1);
            int columnOfNode2 = map.get(node2);
            return dynamicMatrix.get(rowOfNode1).get(columnOfNode2);
        }
        return -1;
    }

    /**
     * Gets the neighbors of a given node.
     *
     * @param node The node.
     * @return The list of neighbors of the node.
     */
    @Override
    public List<T> getNeighbors(T node) {
        List<T> neighbors = new ArrayList<>();
        if (map.containsKey(node)) {
            int row = map.get(node);
            for (Map.Entry<T, Integer> entry : map.entrySet()) {
                int column = entry.getValue();
                int weight = dynamicMatrix.get(row).get(column);
                if (column != row && weight > 0) {
                    neighbors.add(entry.getKey());
                }
            }
        }
        return neighbors;
    }

    /**
     * Returns a string representation of the adjacency matrix.
     *
     * @return The string representation of the adjacency matrix.
     */
    @Override
    public String toString() {
        int size = dynamicMatrix.size();
        StringBuilder msg = new StringBuilder("Adjacency Matrix:\n");

        // Print column headers
        msg.append("   ");
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            msg.append(entry.getKey()).append("  ");
        }
        msg.append("\n");

        for (int i = 0; i < size; i++) {
            int finalI = i;
            T rowNode = map.entrySet().stream()
                    .filter(e -> e.getValue() == finalI)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            msg.append(rowNode).append("  ");

            for (int j = 0; j < size; j++) {
                int weight = dynamicMatrix.get(i).get(j);
                msg.append(weight).append("  ");
            }

            msg.append("\n");
        }

        return msg.toString();
    }

    /**
     * Checks if the graph is weighted.
     *
     * @return True if the graph is weighted, false otherwise.
     */
    @Override
    public boolean isWeighted() {
        return weighted;
    }

    /**
     * Checks if the graph is directed.
     *
     * @return True if the graph is directed, false otherwise.
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * Finds the shortest path between two nodes.
     *
     * @param origin      The starting node.
     * @param destination The destination node.
     * @return The shortest path as a list of nodes.
     */
    @Override
    public List<T> getShortestPath(T origin, T destination) {
        ArrayList<T> shortestPath = new ArrayList<>();
        return null; // Implementation omitted for brevity
    }
}



