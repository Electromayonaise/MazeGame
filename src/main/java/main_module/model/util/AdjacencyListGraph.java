package main_module.model.util;

import java.util.*;


/**
 * The {@code AdjacencyListGraph} class represents a graph using an adjacency list. It supports both weighted and
 * unweighted edges, as well as directed and undirected graphs.
 *
 * @param <T> The type of the graph nodes.
 */
public class AdjacencyListGraph<T> implements Graph<T> {
    private boolean weighted;                   // Indicates whether the graph has weighted edges
    private boolean directed;                   // Indicates whether the graph is directed or undirected
    private HashMap<T, ArrayList<WeightedNeighbor<T>>> map;  // The adjacency list representing the graph

    /**
     * Constructs an adjacency list graph.
     *
     * @param weighted  {@code true} if the graph has weighted edges, {@code false} otherwise.
     * @param directed  {@code true} if the graph is directed, {@code false} for undirected.
     */
    public AdjacencyListGraph(boolean weighted, boolean directed) {
        this.weighted = weighted;
        this.directed = directed;
        this.map = new HashMap<>();
    }

    /**
     * Adds a node to the graph.
     *
     * @param node The node to be added.
     * @return {@code true} if the node was added successfully, {@code false} if the node already exists.
     */
    public boolean addNode(T node) {
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<>());
            return true;
        }
        return false;
    }

    /**
     * Adds an edge between two nodes with an optional weight.
     *
     * @param node1  The first node.
     * @param node2  The second node.
     * @param weight The weight of the edge.
     * @return {@code true} if the edge was added successfully, {@code false} if nodes are not present.
     */
    public boolean addEdge(T node1, T node2, int weight) {
        if (map.containsKey(node1) && map.containsKey(node2)) {
            ArrayList<WeightedNeighbor<T>> neighbors1 = map.get(node1);
            neighbors1.add(new WeightedNeighbor<>(node2, weight));
            if (!directed) {
                ArrayList<WeightedNeighbor<T>> neighbors2 = map.get(node2);
                neighbors2.add(new WeightedNeighbor<>(node1, weight));
            }
            return true;
        }
        return false;
    }

    /**
     * Gets the weight of the edge between two nodes.
     *
     * @param node1 The first node.
     * @param node2 The second node.
     * @return The weight of the edge or a negative value if there is no connection.
     */
    public int getWeight(T node1, T node2) {
        if (map.containsKey(node1)) {
            ArrayList<WeightedNeighbor<T>> neighbors = map.get(node1);
            for (WeightedNeighbor<T> neighbor : neighbors) {
                if (neighbor.getNode().equals(node2)) {
                    return neighbor.getWeight();
                }
            }
        }
        return -1; // Return a negative value to indicate no connection
    }

    /**
     * Checks if there is a direct influence from node1 to node2.
     *
     * @param node1 The source node.
     * @param node2 The destination node.
     * @return {@code true} if there is a direct influence, {@code false} otherwise.
     */
    @Override
    public boolean nodeInfluenceDirectly(T node1, T node2) {
        if (map.containsKey(node1)) {
            ArrayList<WeightedNeighbor<T>> neighbors = map.get(node1);
            for (WeightedNeighbor<T> neighbor : neighbors) {
                if (neighbor.getNode().equals(node2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the neighbors of a given node.
     *
     * @param node The node.
     * @return The list of neighboring nodes.
     */
    public List<T> getNeighbors(T node) {
        if (map.containsKey(node)) {
            ArrayList<WeightedNeighbor<T>> neighbors = map.get(node);
            List<T> neighborNodes = new ArrayList<>();
            for (WeightedNeighbor<T> neighbor : neighbors) {
                neighborNodes.add(neighbor.getNode());
            }
            return neighborNodes;
        }
        return Collections.emptyList();
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return The string representation of the graph.
     */
    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("Adjacency List:\n");
        for (Map.Entry<T, ArrayList<WeightedNeighbor<T>>> entry : map.entrySet()) {
            msg.append(entry.getKey()).append(" -> {");
            ArrayList<WeightedNeighbor<T>> neighbors = entry.getValue();
            for (int i = 0; i < neighbors.size(); i++) {
                msg.append(" (").append(neighbors.get(i).getNode());
                if (isWeighted()) {
                    msg.append(",").append(neighbors.get(i).getWeight());
                }
                msg.append(")");
                msg.append(i < neighbors.size() - 1 ? ", " : "");
            }
            msg.append(" }\n");
        }
        return msg.toString();
    }

    /**
     * Checks if the graph has weighted edges.
     *
     * @return {@code true} if the graph has weighted edges, {@code false} otherwise.
     */
    @Override
    public boolean isWeighted() {
        return weighted;
    }

    /**
     * Checks if the graph is directed.
     *
     * @return {@code true} if the graph is directed, {@code false} for undirected.
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * Finds the shortest path between two nodes using BFS.
     *
     * @param origin      The starting node.
     * @param destination The destination node.
     * @return The shortest path as a list of nodes.
     */
    @Override
    public List<T> getShortestPath(T origin, T destination) {
        if (origin.equals(destination)) {
            return Collections.singletonList(origin);
        }

        HashSet<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        Map<T, T> mapOfPrevs = new HashMap<>();

        queue.add(origin);
        visited.add(origin);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            for (T neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    mapOfPrevs.put(neighbor, current);
                    queue.add(neighbor);
                    visited.add(neighbor);

                    if (neighbor.equals(destination)) {
                        return buildPath(origin, destination, mapOfPrevs);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    /**
     * Performs Breadth-First Search (BFS) to find the shortest path between two nodes.
     *
     * @param origin      The starting node.
     * @param destination The destination node.
     * @param visited     A set to track visited nodes.
     * @return The shortest path as a list of nodes.
     */
    public List<T> bfs(T origin, T destination, HashSet<T> visited) {
        Queue<T> queue = new LinkedList<>();
        queue.add(origin);
        visited.add(origin);
        T current;
        Map<T, T> mapOfPrevs = new HashMap<>();

        while (!queue.isEmpty()) {
            current = queue.poll();
            List<T> neighbors = getNeighbors(current);
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    mapOfPrevs.put(neighbor, current);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
                if (visited.contains(destination)) {
                    break;
                }
            }
        }

        return buildPath(origin, destination, mapOfPrevs);
    }

    /**
     * Builds the path from the destination node to the origin node using a map of predecessors.
     *
     * @param origin      The origin node.
     * @param destination The destination node.
     * @param mapOfPrevs  A map of predecessors.
     * @return The path as a list of nodes.
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


}




