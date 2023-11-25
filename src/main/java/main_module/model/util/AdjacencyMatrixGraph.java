package main_module.model.util;
import java.util.*;

/**
 * Represents an adjacency matrix graph that can be weighted and/or directed.
 *
 * @param <T> the type of elements stored in the graph
 */
public class AdjacencyMatrixGraph<T> implements Graph<T> {
    private ArrayList<ArrayList<Integer>> dynamicMatrix;
    private LinkedHashMap<T, Integer> map;
    private boolean weighted;

    private boolean directed;

    /**
     * Constructs an AdjacencyMatrixGraph.
     *
     * @param weighted  true if the graph is weighted, false otherwise
     * @param directed true if the graph is directed, false otherwise
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
     * @param node the node to be added
     * @return true if the node is added, false if it already exists
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
     * Adds one column and one row to the dynamic matrix.
     * The values in the new row and column are initialized to 0.
     */
    private void addOneColumnAndRow() {
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
     * Adds an edge between two nodes with a specified weight.
     *
     * @param node1  the first node
     * @param node2  the second node
     * @param weight the weight of the edge
     * @return true if the edge is added, false if nodes do not exist
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
     * Checks if there is a direct influence between two nodes.
     *
     * @param node1 the first node
     * @param node2 the second node
     * @return true if there is a direct influence, false otherwise
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
     * @param node1 the first node
     * @param node2 the second node
     * @return the weight of the edge, or -1 if no connection
     */
    @Override
    public int getWeight(T node1, T node2) {
        if (map.containsKey(node1) && map.containsKey(node2)) {
            int rowOfNode1 = map.get(node1);
            int columnOfNode2 = map.get(node2);
            return dynamicMatrix.get(rowOfNode1).get(columnOfNode2);
        }
        return -1; // Return -1 for non-existent or unconnected nodes.
    }

    /**
     * Gets the neighbors of a given node.
     *
     * @param node the node to get neighbors for
     * @return a list of neighbor nodes
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
     * Converts the graph to a string representation.
     *
     * @return a string representing the adjacency matrix of the graph
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
     * @return true if the graph is weighted, false otherwise
     */
    @Override
    public boolean isWeighted() {
        return weighted;
    }

    /**
     * Checks if the graph is directed.
     *
     * @return true if the graph is directed, false otherwise
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * Gets the shortest path between two nodes using breadth-first search.
     *
     * @param origin      the starting node
     * @param destination the destination node
     * @return a list representing the shortest path
     */
    @Override
    public List<T> getShortestPath(T origin, T destination) {
        List<T> shortestPath = new ArrayList<>();
        return bfs(origin, destination);
    }

    /**
     * Breadth-first search algorithm to find the shortest path between two nodes.
     *
     * @param origin      the starting node
     * @param destination the destination node
     * @return a list representing the shortest path
     */
    private List<T> bfs(T origin, T destination) {
        if(origin.equals(destination)){
            return new ArrayList<>();
        }
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(origin);
        visited.add(origin);
        T current;
        // Map where the key is a node in the path and the value is the node it comes from
        Map<T, T> mapOfPrevs = new HashMap<>();
        boolean pathWasFound = false;
        List<T> path = new ArrayList<>();

        while (!queue.isEmpty() && !pathWasFound) {
            current = queue.poll();
            int currentIndex = map.get(current);

            // Iterate over neighbors using the adjacency matrix
            for (int i = 0; i < dynamicMatrix.size(); i++) {
                if (dynamicMatrix.get(currentIndex).get(i) > 0) {
                    T neighbor = getKeyByValue(map, i);

                    if (!visited.contains(neighbor)) {
                        // We reached the neighbor through the current node
                        mapOfPrevs.put(neighbor, current);
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            if (visited.contains(destination)) {
                pathWasFound = true;
            }
        }

        if (pathWasFound) {
            path = buildPath(origin, destination, mapOfPrevs);
        }

        return path;
    }

    /**
     * Builds the path from the destination node to the origin node using a map of predecessors.
     *
     * @param origin      the starting node
     * @param destination the destination node
     * @param mapOfPrevs  a map of predecessors
     * @return a list representing the path from origin to destination
     */
    private List<T> buildPath(T origin, T destination, Map<T, T> mapOfPrevs) {
        List<T> list = new ArrayList<>();

        T current = destination;
        while (current != origin) {
            list.add(current);
            current = mapOfPrevs.get(current);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * Gets the key associated with a given value in a map.
     *
     * @param map   the map to search
     * @param value the value to search for
     * @return the key associated with the given value, or null if not found
     */
    private T getKeyByValue(Map<T, Integer> map, int value) {
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }



}





