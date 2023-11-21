package main_module.model.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * The {@code MazeGenerator} class is responsible for generating a maze graph using either an adjacency matrix or an
 * adjacency list. It supports both directed and undirected graph configurations.
 */
public class MazeGenerator {
    private Random rand;

    /**
     * Constructs a {@code MazeGenerator} and initializes the random number generator.
     */
    public MazeGenerator() {
        rand = new Random();
    }

    /**
     * Generates a maze graph with the specified number of rows and columns.
     *
     * @param row           The number of rows in the maze. Must be an odd number.
     * @param col           The number of columns in the maze. Must be an odd number.
     * @param adjacencyList If {@code true}, the maze graph will be represented using an adjacency list.
     *                     If {@code false}, it will be represented using an adjacency matrix.
     * @param directed      If {@code true}, the maze graph will be directed.
     *                     If {@code false}, it will be undirected.
     * @return The generated maze graph.
     */
    public Graph<MatrixCor> generateMaze(int row, int col, boolean adjacencyList, boolean directed) {
        Graph<MatrixCor> graph;
        boolean[][] visited = new boolean[row][col];

        // Initialize the graph according to the parameters
        if (adjacencyList) {
            graph = new AdjacencyListGraph<>(false, directed);
        } else {
            graph = new AdjacencyMatrixGraph<>(false, directed);
        }

        int[][] map = new int[row][col];

        // Initialize the nodes
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                graph.addNode(new MatrixCor(i, j));
            }
        }

        MatrixCor initialCell = new MatrixCor(0, 0);
        visited[initialCell.getRow()][initialCell.getCol()] = true;
        Stack<MatrixCor> stack = new Stack<>();
        stack.push(initialCell);

        while (!stack.empty()) {
            MatrixCor current = stack.pop();
            List<MatrixCor> notVisitedNeighbors = getNotVisitedNeighbors(current, visited);

            if (!notVisitedNeighbors.isEmpty()) {
                stack.push(current);
                MatrixCor neighborToConnect = chooseNotVisitedNeighborRandomly(notVisitedNeighbors);
                graph.addEdge(current, neighborToConnect, 1);
                visited[neighborToConnect.getRow()][neighborToConnect.getCol()] = true;
                stack.push(neighborToConnect);
            }
        }

        return graph;
    }

    /**
     * Retrieves the list of neighbors that have not been visited yet for a given matrix coordinate.
     *
     * @param current The matrix coordinate for which to find neighbors.
     * @param visited The 2D array indicating visited status for each matrix coordinate.
     * @return The list of not visited neighbors.
     */
    private List<MatrixCor> getNotVisitedNeighbors(MatrixCor current, boolean[][] visited) {
        int row = visited.length;
        int col = visited[0].length;
        List<MatrixCor> neighbors = getNeighbors(current, row, col);
        List<MatrixCor> notVisitedNeighbors = new ArrayList<>();

        for (MatrixCor neighborMatrixCor : neighbors) {
            if (!visited[neighborMatrixCor.getRow()][neighborMatrixCor.getCol()]) {
                notVisitedNeighbors.add(neighborMatrixCor);
            }
        }

        return notVisitedNeighbors;
    }

    /**
     * Chooses a not visited neighbor randomly from the given list.
     *
     * @param list The list of not visited neighbors.
     * @return A randomly chosen not visited neighbor.
     */
    private MatrixCor chooseNotVisitedNeighborRandomly(List<MatrixCor> list) {
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * Retrieves the list of neighbors for a given matrix coordinate.
     *
     * @param cor The matrix coordinate for which to find neighbors.
     * @param row The total number of rows in the matrix.
     * @param col The total number of columns in the matrix.
     * @return The list of neighbors.
     */
    private List<MatrixCor> getNeighbors(MatrixCor cor, int row, int col) {
        List<MatrixCor> list = new ArrayList<>();
        MatrixCor left = new MatrixCor(cor.getRow(), cor.getCol() - 1);
        MatrixCor right = new MatrixCor(cor.getRow(), cor.getCol() + 1);
        MatrixCor up = new MatrixCor(cor.getRow() - 1, cor.getCol());
        MatrixCor down = new MatrixCor(cor.getRow() + 1, cor.getCol());

        if (validateMatrixCor(left, row, col)) {
            list.add(left);
        }

        if (validateMatrixCor(right, row, col)) {
            list.add(right);
        }

        if (validateMatrixCor(up, row, col)) {
            list.add(up);
        }

        if (validateMatrixCor(down, row, col)) {
            list.add(down);
        }

        return list;
    }

    /**
     * Validates whether the given matrix coordinate is within the bounds of the matrix.
     *
     * @param cor The matrix coordinate to validate.
     * @param row The total number of rows in the matrix.
     * @param col The total number of columns in the matrix.
     * @return {@code true} if the matrix coordinate is valid, {@code false} otherwise.
     */
    public boolean validateMatrixCor(MatrixCor cor, int row, int col) {
        return (0 <= cor.getRow() && cor.getRow() < row) && (0 <= cor.getCol() && cor.getCol() < col);
    }
}

