package model;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    public PathFinder() {
    }

    public List<MatrixCor> getShortestPath(MatrixCor origin, MatrixCor destination, int map[][], boolean adjacencyList, boolean directed) {
        List<MatrixCor> path = new ArrayList<>();
        Graph<MatrixCor> graph;
        if (adjacencyList) {
            graph = new AdjacencyListGraph<>(false, directed);
        } else {
            graph = new AdjacencyMatrixGraph<>(false, directed);
        }
        //construya el grafo con base al mapa
        //y luego calcule el camino m´as corto
        return graph.getShortestPath(origin, destination);
    }
}
