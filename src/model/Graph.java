package model;

import java.util.List;

public interface Graph<T> {
    boolean addNode(T node);

    boolean addEdge(T node1, T node2, int weight);

    int getWeight(T node1, T node2);

    boolean nodeInfluenceDirectly(T node1, T node2);

    boolean isWeighted();

    boolean isDirected();

    List<T> getNeighbors(T node);

    List<T> getShortestPath(T initial, T destination);
}