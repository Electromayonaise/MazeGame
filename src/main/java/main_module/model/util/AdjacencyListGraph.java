package main_module.model.util;

import java.util.*;

/**
 * Represents an adjacency list graph that can be weighted and/or directed.
 *
 * @param <T> the type of elements stored in the graph
 */
public class AdjacencyListGraph<T> implements Graph<T> {
    private boolean weighted;

    private boolean directed;

    private HashMap<T, ArrayList<WeightedNeighbor<T>>> map;

    /**
     * Constructs an AdjacencyListGraph.
     *
     * @param weighted  true if the graph is weighted, false otherwise
     * @param directed true if the graph is directed, false otherwise
     */
    public AdjacencyListGraph(boolean weighted, boolean directed) {
        this.weighted = weighted;
        this.directed = directed;
        map = new HashMap<>();
    }

    /**
     * Adds a node to the graph.
     *
     * @param node the node to be added
     * @return true if the node is added, false if it already exists
     */
    public boolean addNode(T node) {
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<>());
            return true;
        }
        return false;
    }

    /**
     * Adds an edge between two nodes with a specified weight.
     *
     * @param node1  the first node
     * @param node2  the second node
     * @param weight the weight of the edge
     * @return true if the edge is added, false if nodes do not exist
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
     * @param node1 the first node
     * @param node2 the second node
     * @return the weight of the edge, or -1 if no connection
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
     * Checks if there is a direct influence between two nodes.
     *
     * @param node1 the first node
     * @param node2 the second node
     * @return true if there is a direct influence, false otherwise
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
     * @param node the node to get neighbors for
     * @return a list of neighbor nodes
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
     * Converts the graph to a string representation.
     *
     * @return a string representing the adjacency list of the graph
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
        Set<T>visited=new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(origin);
        visited.add(origin);
        T current;
        //la key es un nodo de la ruta y el value el nodo del que provengo
        Map<T, T> mapOfPrevs = new HashMap<>();
        boolean pathWasFound=false;
        List<T> path=new ArrayList<>();


        while (!queue.isEmpty() &&!pathWasFound) {
            current = queue.poll();
            List<T> neighbors = getNeighbors(current);
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    //llegamos al neighbor a traves del current
                    mapOfPrevs.put(neighbor, current);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
            if (visited.contains(destination)) {
                pathWasFound=true;
            }
        }
        if(pathWasFound){
            path=buildPath(origin,destination,mapOfPrevs);
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
        while (current !=origin){
            list.add(current);
            current=mapOfPrevs.get(current);
        }
        Collections.reverse(list);
        return list;
    }


}



