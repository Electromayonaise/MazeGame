package main_module.model.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code PathFinder} class is responsible for finding the shortest path between two matrix coordinates in a graph.
 * It supports both adjacency matrix and adjacency list representations of the graph, as well as directed and undirected graph configurations.
 */
public class PathFinder {
    /**
     * Constructs a {@code PathFinder}.
     */
    public PathFinder() {
    }

    /**
     * Retrieves the shortest path between the specified origin and destination matrix coordinates in the given graph.
     *
     * @param origin      The starting matrix coordinate.
     * @param destination The destination matrix coordinate.
     * @param map         The 2D array representing the graph.
     * @param adjacencyList If {@code true}, the graph is represented using an adjacency list.
     *                     If {@code false}, it is represented using an adjacency matrix.
     * @param directed      If {@code true}, the graph is directed.
     *                      If {@code false}, it is undirected.
     * @return The list of matrix coordinates representing the shortest path from the origin to the destination.
     */
    public List<MatrixCor> getShortestPath(MatrixCor origin, MatrixCor destination, int map[][], boolean adjacencyList, boolean directed) {
        List<MatrixCor> path = new ArrayList<>();
        Graph<MatrixCor> graph;

        // Initialize the graph based on the parameters
        if (adjacencyList) {
            graph = new AdjacencyListGraph<>(false, directed);
        } else {
            graph = new AdjacencyMatrixGraph<>(false, directed);
        }

        // Build the graph based on the provided map
        // and then calculate the shortest path
        return graph.getShortestPath(origin, destination);
    }
}

