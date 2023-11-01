package model;

import java.util.*;

public class AdjacencyListGraph<T> implements Graph<T> {
    private boolean weighted;

    private boolean directed;

    private HashMap<T, ArrayList<WeightedNeighbor<T>>> map;

    public AdjacencyListGraph(boolean weighted, boolean directed) {
        this.weighted = weighted;
        this.directed = directed;
        map = new HashMap<>();
    }

    public boolean addNode(T node) {
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<>());
            return true;
        }
        return false;
    }

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


    @Override
    public boolean isWeighted() {
        return weighted;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }
}




