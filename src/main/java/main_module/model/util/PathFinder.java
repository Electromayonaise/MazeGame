package main_module.model.util;

import java.util.ArrayList;
import java.util.Arrays;
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
        for(int i=0;i< map.length;i++){
            for(int j=0;j< map[0].length;j++){
                if(map[i][j]==0){
                    //se añaden los nodos en los espacios en donde no hayan
                    //obstaculos
                    graph.addNode(new MatrixCor(i,j));
                }
            }
        }
        for(int i=0;i< map.length;i++){
            for(int j=0;j< map[0].length;j++){
                if(map[i][j]==0){
                    //se añaden las aristas
                    addEdges(i,j,graph);
                }
            }
        }


        //construya el grafo con base al mapa
        //y luego calcule el camino m´as corto
        return graph.getShortestPath(origin, destination);
    }
    public void addEdges(int row, int col, Graph<MatrixCor> graph) {
        MatrixCor current = new MatrixCor(row, col);
        MatrixCor left = new MatrixCor(row, col - 1);
        MatrixCor right = new MatrixCor(row, col + 1);
        MatrixCor up = new MatrixCor(row - 1, col);
        MatrixCor down = new MatrixCor(row + 1, col);
        List<MatrixCor> list = Arrays.asList(left, right, up, down);
        for (MatrixCor neighbor : list) {
            boolean flag = graph.addEdge(current, neighbor, 1);
            // No need for additional validation here; the method will not add the edge if the node doesn't exist.
        }
    }

}
