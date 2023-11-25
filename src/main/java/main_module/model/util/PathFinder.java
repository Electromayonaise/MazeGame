package main_module.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * PathFinder class for finding the shortest path between two matrix coordinates in a maze.
 */
public class PathFinder {
    /**
     * Constructs a PathFinder.
     */
    public PathFinder() {
    }

    /**
     * Finds the shortest path between two matrix coordinates in a maze represented as a graph.
     *
     * @param origin          the starting matrix coordinate
     * @param destination     the destination matrix coordinate
     * @param map             a 2D array representing the maze, where 0 represents open spaces and obstacles are non-zero values
     * @param adjacencyList   true if the graph representation is an adjacency list, false for an adjacency matrix
     * @param directed        true if the graph is directed, false otherwise
     * @return a list representing the shortest path between the origin and destination
     */
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
                    graph.addNode(new MatrixCor(i,j));
                }
            }
        }
        for(int i=0;i< map.length;i++){
            for(int j=0;j< map[0].length;j++){
                if(map[i][j]==0){
                    addEdges(i,j,graph);
                }
            }
        }

        return graph.getShortestPath(origin, destination);
    }

    /**
     * Adds edges to the graph for a given matrix coordinate and its neighbors.
     *
     * @param row   the row of the matrix coordinate
     * @param col   the column of the matrix coordinate
     * @param graph the graph to which edges are added
     */
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
