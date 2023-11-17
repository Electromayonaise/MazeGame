package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapGenerator {
    private MazeGenerator mazeGenerator;

    public MapGenerator() {
        mazeGenerator = new MazeGenerator();
    }

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

        for (int i = 0; i < mapWithBorders.length; i++) {
            for (int j = 0; j < mapWithBorders[0].length; j++) {
                System.out.print(mapWithBorders[i][j] + " ");
            }
            System.out.println();
        }


        return map;
    }

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
