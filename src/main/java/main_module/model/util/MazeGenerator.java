package main_module.model.util;

import java.util.*;

/**
 * MazeGenerator class that creates mazes represented as graphs using either adjacency lists or adjacency matrices.
 * The maze generation follows a depth-first search algorithm with a random choice of paths.
 */
public class MazeGenerator {
    private Random rand;

    /**
     * Constructs a MazeGenerator with a default random number generator.
     */
    public MazeGenerator() {
        rand = new Random();
    }

    // The ROW and COL - THEY HAVE TO BE ODD

    /**
     * Generates a maze represented as a graph.
     *
     * @param row           the number of rows in the maze (must be odd)
     * @param col           the number of columns in the maze (must be odd)
     * @param adjacencyList true if the graph representation should use an adjacency list, false for an adjacency matrix
     * @param directed      true if the graph should be directed, false otherwise
     * @return a graph representing the generated maze
     */
    public Graph<MatrixCor> generateMaze(int row, int col, boolean adjacencyList, boolean directed) {
        Graph<MatrixCor> graph;
        boolean[][] visited = new boolean[row][col];
        //initialize the graph according to the parameters
        if (adjacencyList) {
            graph = new AdjacencyListGraph<>(false, directed);
        } else {
            graph = new AdjacencyMatrixGraph<>(false, directed);
        }

        int[][] map = new int[row][col];
        //init the nodes
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
     * Gets the list of not visited neighbors for a given matrix coordinate.
     *
     * @param current the current matrix coordinate
     * @param visited a 2D array representing visited cells in the maze
     * @return a list of not visited neighbors
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
     * Chooses a not visited neighbor randomly from a list.
     *
     * @param list the list of not visited neighbors
     * @return a randomly chosen not visited neighbor
     */
    private MatrixCor chooseNotVisitedNeighborRandomly(List<MatrixCor> list) {
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * Gets the list of neighboring matrix coordinates for a given matrix coordinate.
     *
     * @param cor the current matrix coordinate
     * @param row the total number of rows in the maze
     * @param col the total number of columns in the maze
     * @return a list of neighboring matrix coordinates
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
     * Validates a matrix coordinate to ensure it is within the bounds of the maze.
     *
     * @param cor the matrix coordinate to validate
     * @param row the total number of rows in the maze
     * @param col the total number of columns in the maze
     * @return true if the matrix coordinate is valid, false otherwise
     */
    public boolean validateMatrixCor(MatrixCor cor, int row, int col) {
        return (0 <= cor.getRow() && cor.getRow() < row) && (0 <= cor.getCol() && cor.getCol() < col);
    }


}

