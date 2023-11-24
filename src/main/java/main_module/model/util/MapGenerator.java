package main_module.model.util;

import java.util.Arrays;
import java.util.List;

/**
 * The {@code MapGenerator} class is responsible for generating game maps using a maze generation algorithm. It supports
 * the generation of maps with specified dimensions, including options for creating adjacency lists and directed graphs.
 */
public class MapGenerator {
    /**
     * The maze generator used to create the maze structure.
     */
    private MazeGenerator mazeGenerator;

    /**
     * Constructs a {@code MapGenerator} and initializes the maze generator.
     */
    public MapGenerator() {
        mazeGenerator = new MazeGenerator();
    }

    /**
     * Generates a map with the specified dimensions, adjacency list option, and directed graph option.
     *
     * @param row          The number of rows in the map.
     * @param col          The number of columns in the map.
     * @param adjacencyList Indicates whether to use an adjacency list for maze generation.
     * @param directed     Indicates whether to generate a directed maze.
     * @return A 2D array representing the generated map.
     */
    public int[][] generateMap(int row, int col, boolean adjacencyList, boolean directed) {
        int[][] map = new int[row][col];
        for (int[] mapRow : map) {
            Arrays.fill(mapRow, 1);
        }
        boolean[][] visited = new boolean[row / 2 + 1][col / 2 + 1];
        Graph<MatrixCor> graph = mazeGenerator.generateMaze(row / 2 + 1, col / 2 + 1, adjacencyList, directed);
        MatrixCor initial = new MatrixCor(0, 0);

        map = dfs(graph, initial, visited, map);

        int[][] mapWithBorders = new int[row + 2][col + 2];
        for (int i = 0; i < row + 2; i++) {
            //left and right borders
            mapWithBorders[i][col + 2 - 1] = 1;
            mapWithBorders[i][0] = 1;
        }
        for (int i = 0; i < col + 2; i++) {
            //up down
            mapWithBorders[0][i] = 1;
            mapWithBorders[row + 2 - 1][i] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                mapWithBorders[i + 1][j + 1] = map[i][j];
            }
        }
        return mapWithBorders;
    }

    /**
     * Performs depth-first search (DFS) on the graph to update the map based on visited nodes.
     *
     * @param graph   The graph representing the maze structure.
     * @param current The current position in the maze.
     * @param visited 2D array indicating visited nodes in the maze.
     * @param map     The current state of the map being generated.
     * @return The updated map after DFS traversal.
     */
    public int[][] dfs(Graph<MatrixCor> graph, MatrixCor current, boolean[][] visited, int[][] map) {
        visited[current.getRow()][current.getCol()] = true;
        map[current.getRow() * 2][current.getCol() * 2] = 0;
        List<MatrixCor> neighbors = graph.getNeighbors(current);
        for (MatrixCor neighbor : neighbors) {
            int midRowInMap = (neighbor.getRow() * 2 + current.getRow() * 2) / 2;
            int midColInMap = (neighbor.getCol() * 2 + current.getCol() * 2) / 2;
            map[midRowInMap][midColInMap] = 0;
            if (!visited[neighbor.getRow()][neighbor.getCol()]) {
                map = dfs(graph, neighbor, visited, map);
            }
        }
        return map;
    }
}

