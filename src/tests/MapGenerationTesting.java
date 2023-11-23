package tests;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MapGenerationTesting {

    @Test
    void generateSpecificMaze() {
        Graph<MatrixCor> graph = new AdjacencyListGraph<>(false, true);

        graph.addNode(new MatrixCor(0, 0));
        graph.addNode(new MatrixCor(0, 1));
        graph.addNode(new MatrixCor(0, 2));
        graph.addNode(new MatrixCor(0, 3));
        graph.addNode(new MatrixCor(0, 4));
        graph.addNode(new MatrixCor(0, 5));
        graph.addNode(new MatrixCor(0, 6));
        graph.addNode(new MatrixCor(0, 7));
        graph.addNode(new MatrixCor(0, 8));
        graph.addNode(new MatrixCor(0, 9));
        graph.addNode(new MatrixCor(0, 10));
        graph.addNode(new MatrixCor(1, 0));
        graph.addNode(new MatrixCor(1, 1));
        graph.addNode(new MatrixCor(1, 2));
        graph.addNode(new MatrixCor(1, 3));
        graph.addNode(new MatrixCor(1, 4));
        graph.addNode(new MatrixCor(1, 5));
        graph.addNode(new MatrixCor(1, 6));
        graph.addNode(new MatrixCor(1, 7));
        graph.addNode(new MatrixCor(1, 8));
        graph.addNode(new MatrixCor(1, 9));
        graph.addNode(new MatrixCor(1, 10));
        graph.addNode(new MatrixCor(2, 0));
        graph.addNode(new MatrixCor(2, 1));
        graph.addNode(new MatrixCor(2, 2));
        graph.addNode(new MatrixCor(2, 3));
        graph.addNode(new MatrixCor(2, 4));
        graph.addNode(new MatrixCor(2, 5));
        graph.addNode(new MatrixCor(2, 6));
        graph.addNode(new MatrixCor(2, 7));
        graph.addNode(new MatrixCor(2, 8));
        graph.addNode(new MatrixCor(2, 9));
        graph.addNode(new MatrixCor(2, 10));
        graph.addNode(new MatrixCor(3, 0));
        graph.addNode(new MatrixCor(3, 1));
        graph.addNode(new MatrixCor(3, 2));
        graph.addNode(new MatrixCor(3, 3));
        graph.addNode(new MatrixCor(3, 4));
        graph.addNode(new MatrixCor(3, 5));
        graph.addNode(new MatrixCor(3, 6));
        graph.addNode(new MatrixCor(3, 7));
        graph.addNode(new MatrixCor(3, 8));
        graph.addNode(new MatrixCor(3, 9));
        graph.addNode(new MatrixCor(3, 10));
        graph.addNode(new MatrixCor(4, 0));
        graph.addNode(new MatrixCor(4, 1));
        graph.addNode(new MatrixCor(4, 2));
        graph.addNode(new MatrixCor(4, 3));
        graph.addNode(new MatrixCor(4, 4));
        graph.addNode(new MatrixCor(4, 5));
        graph.addNode(new MatrixCor(4, 6));
        graph.addNode(new MatrixCor(4, 7));
        graph.addNode(new MatrixCor(4, 8));
        graph.addNode(new MatrixCor(4, 9));
        graph.addNode(new MatrixCor(4, 10));
        graph.addNode(new MatrixCor(5, 0));
        graph.addNode(new MatrixCor(5, 1));
        graph.addNode(new MatrixCor(5, 2));
        graph.addNode(new MatrixCor(5, 3));
        graph.addNode(new MatrixCor(5, 4));
        graph.addNode(new MatrixCor(5, 5));
        graph.addNode(new MatrixCor(5, 6));
        graph.addNode(new MatrixCor(5, 7));
        graph.addNode(new MatrixCor(5, 8));
        graph.addNode(new MatrixCor(5, 9));
        graph.addNode(new MatrixCor(5, 10));
        graph.addNode(new MatrixCor(6, 0));
        graph.addNode(new MatrixCor(6, 1));
        graph.addNode(new MatrixCor(6, 2));
        graph.addNode(new MatrixCor(6, 3));
        graph.addNode(new MatrixCor(6, 4));
        graph.addNode(new MatrixCor(6, 5));
        graph.addNode(new MatrixCor(6, 6));
        graph.addNode(new MatrixCor(6, 7));
        graph.addNode(new MatrixCor(6, 8));
        graph.addNode(new MatrixCor(6, 9));
        graph.addNode(new MatrixCor(6, 10));
        graph.addNode(new MatrixCor(7, 0));
        graph.addNode(new MatrixCor(7, 1));
        graph.addNode(new MatrixCor(7, 2));
        graph.addNode(new MatrixCor(7, 3));
        graph.addNode(new MatrixCor(7, 4));
        graph.addNode(new MatrixCor(7, 5));
        graph.addNode(new MatrixCor(7, 6));
        graph.addNode(new MatrixCor(7, 7));
        graph.addNode(new MatrixCor(7, 8));
        graph.addNode(new MatrixCor(7, 9));
        graph.addNode(new MatrixCor(7, 10));
        graph.addNode(new MatrixCor(8, 0));
        graph.addNode(new MatrixCor(8, 1));
        graph.addNode(new MatrixCor(8, 2));
        graph.addNode(new MatrixCor(8, 3));
        graph.addNode(new MatrixCor(8, 4));
        graph.addNode(new MatrixCor(8, 5));
        graph.addNode(new MatrixCor(8, 6));
        graph.addNode(new MatrixCor(8, 7));
        graph.addNode(new MatrixCor(8, 8));
        graph.addNode(new MatrixCor(8, 9));
        graph.addNode(new MatrixCor(8, 10));
        graph.addNode(new MatrixCor(9, 0));
        graph.addNode(new MatrixCor(9, 1));
        graph.addNode(new MatrixCor(9, 2));
        graph.addNode(new MatrixCor(9, 3));
        graph.addNode(new MatrixCor(9, 4));
        graph.addNode(new MatrixCor(9, 5));
        graph.addNode(new MatrixCor(9, 6));
        graph.addNode(new MatrixCor(9, 7));
        graph.addNode(new MatrixCor(9, 8));
        graph.addNode(new MatrixCor(9, 9));
        graph.addNode(new MatrixCor(9, 10));
        graph.addNode(new MatrixCor(10, 0));
        graph.addNode(new MatrixCor(10, 1));
        graph.addNode(new MatrixCor(10, 2));
        graph.addNode(new MatrixCor(10, 3));
        graph.addNode(new MatrixCor(10, 4));
        graph.addNode(new MatrixCor(10, 5));
        graph.addNode(new MatrixCor(10, 6));
        graph.addNode(new MatrixCor(10, 7));
        graph.addNode(new MatrixCor(10, 8));
        graph.addNode(new MatrixCor(10, 9));
        graph.addNode(new MatrixCor(10, 10));
        graph.addEdge(new MatrixCor(0, 0), new MatrixCor(1, 0), 1);
        graph.addEdge(new MatrixCor(1, 0), new MatrixCor(2, 0), 1);
        graph.addEdge(new MatrixCor(2, 0), new MatrixCor(2, 1), 1);
        graph.addEdge(new MatrixCor(2, 1), new MatrixCor(1, 1), 1);
        graph.addEdge(new MatrixCor(1, 1), new MatrixCor(0, 1), 1);
        graph.addEdge(new MatrixCor(0, 1), new MatrixCor(0, 2), 1);
        graph.addEdge(new MatrixCor(0, 2), new MatrixCor(1, 2), 1);
        graph.addEdge(new MatrixCor(1, 2), new MatrixCor(1, 3), 1);
        graph.addEdge(new MatrixCor(1, 3), new MatrixCor(0, 3), 1);
        graph.addEdge(new MatrixCor(0, 3), new MatrixCor(0, 4), 1);
        graph.addEdge(new MatrixCor(0, 4), new MatrixCor(0, 5), 1);
        graph.addEdge(new MatrixCor(0, 5), new MatrixCor(0, 6), 1);
        graph.addEdge(new MatrixCor(0, 6), new MatrixCor(1, 6), 1);
        graph.addEdge(new MatrixCor(1, 6), new MatrixCor(1, 7), 1);
        graph.addEdge(new MatrixCor(1, 7), new MatrixCor(1, 8), 1);
        graph.addEdge(new MatrixCor(1, 8), new MatrixCor(1, 9), 1);
        graph.addEdge(new MatrixCor(1, 9), new MatrixCor(0, 9), 1);
        graph.addEdge(new MatrixCor(0, 9), new MatrixCor(0, 8), 1);
        graph.addEdge(new MatrixCor(0, 8), new MatrixCor(0, 7), 1);
        graph.addEdge(new MatrixCor(0, 9), new MatrixCor(0, 10), 1);
        graph.addEdge(new MatrixCor(0, 10), new MatrixCor(1, 10), 1);
        graph.addEdge(new MatrixCor(1, 10), new MatrixCor(2, 10), 1);
        graph.addEdge(new MatrixCor(2, 10), new MatrixCor(3, 10), 1);
        graph.addEdge(new MatrixCor(3, 10), new MatrixCor(3, 9), 1);
        graph.addEdge(new MatrixCor(3, 9), new MatrixCor(3, 8), 1);
        graph.addEdge(new MatrixCor(3, 8), new MatrixCor(4, 8), 1);
        graph.addEdge(new MatrixCor(4, 8), new MatrixCor(4, 9), 1);
        graph.addEdge(new MatrixCor(4, 9), new MatrixCor(5, 9), 1);
        graph.addEdge(new MatrixCor(5, 9), new MatrixCor(5, 10), 1);
        graph.addEdge(new MatrixCor(5, 10), new MatrixCor(6, 10), 1);
        graph.addEdge(new MatrixCor(6, 10), new MatrixCor(6, 9), 1);
        graph.addEdge(new MatrixCor(6, 9), new MatrixCor(6, 8), 1);
        graph.addEdge(new MatrixCor(6, 8), new MatrixCor(6, 7), 1);
        graph.addEdge(new MatrixCor(6, 7), new MatrixCor(7, 7), 1);
        graph.addEdge(new MatrixCor(7, 7), new MatrixCor(7, 6), 1);
        graph.addEdge(new MatrixCor(7, 6), new MatrixCor(6, 6), 1);
        graph.addEdge(new MatrixCor(6, 6), new MatrixCor(5, 6), 1);
        graph.addEdge(new MatrixCor(5, 6), new MatrixCor(5, 5), 1);
        graph.addEdge(new MatrixCor(5, 5), new MatrixCor(6, 5), 1);
        graph.addEdge(new MatrixCor(6, 5), new MatrixCor(6, 4), 1);
        graph.addEdge(new MatrixCor(6, 4), new MatrixCor(7, 4), 1);
        graph.addEdge(new MatrixCor(7, 4), new MatrixCor(7, 3), 1);
        graph.addEdge(new MatrixCor(7, 3), new MatrixCor(6, 3), 1);
        graph.addEdge(new MatrixCor(6, 3), new MatrixCor(5, 3), 1);
        graph.addEdge(new MatrixCor(5, 3), new MatrixCor(5, 4), 1);
        graph.addEdge(new MatrixCor(5, 4), new MatrixCor(4, 4), 1);
        graph.addEdge(new MatrixCor(4, 4), new MatrixCor(4, 5), 1);
        graph.addEdge(new MatrixCor(4, 5), new MatrixCor(3, 5), 1);
        graph.addEdge(new MatrixCor(3, 5), new MatrixCor(3, 4), 1);
        graph.addEdge(new MatrixCor(3, 4), new MatrixCor(3, 3), 1);
        graph.addEdge(new MatrixCor(3, 3), new MatrixCor(4, 3), 1);
        graph.addEdge(new MatrixCor(4, 3), new MatrixCor(4, 2), 1);
        graph.addEdge(new MatrixCor(4, 2), new MatrixCor(4, 1), 1);
        graph.addEdge(new MatrixCor(4, 1), new MatrixCor(3, 1), 1);
        graph.addEdge(new MatrixCor(3, 1), new MatrixCor(3, 2), 1);
        graph.addEdge(new MatrixCor(3, 2), new MatrixCor(2, 2), 1);
        graph.addEdge(new MatrixCor(2, 2), new MatrixCor(2, 3), 1);
        graph.addEdge(new MatrixCor(2, 3), new MatrixCor(2, 4), 1);
        graph.addEdge(new MatrixCor(2, 4), new MatrixCor(1, 4), 1);
        graph.addEdge(new MatrixCor(1, 4), new MatrixCor(1, 5), 1);
        graph.addEdge(new MatrixCor(1, 5), new MatrixCor(2, 5), 1);
        graph.addEdge(new MatrixCor(2, 5), new MatrixCor(2, 6), 1);
        graph.addEdge(new MatrixCor(2, 6), new MatrixCor(3, 6), 1);
        graph.addEdge(new MatrixCor(3, 6), new MatrixCor(4, 6), 1);
        graph.addEdge(new MatrixCor(4, 6), new MatrixCor(4, 7), 1);
        graph.addEdge(new MatrixCor(4, 7), new MatrixCor(5, 7), 1);
        graph.addEdge(new MatrixCor(5, 7), new MatrixCor(5, 8), 1);
        graph.addEdge(new MatrixCor(4, 7), new MatrixCor(3, 7), 1);
        graph.addEdge(new MatrixCor(3, 7), new MatrixCor(2, 7), 1);
        graph.addEdge(new MatrixCor(2, 7), new MatrixCor(2, 8), 1);
        graph.addEdge(new MatrixCor(2, 8), new MatrixCor(2, 9), 1);
        graph.addEdge(new MatrixCor(3, 1), new MatrixCor(3, 0), 1);
        graph.addEdge(new MatrixCor(3, 0), new MatrixCor(4, 0), 1);
        graph.addEdge(new MatrixCor(4, 0), new MatrixCor(5, 0), 1);
        graph.addEdge(new MatrixCor(5, 0), new MatrixCor(5, 1), 1);
        graph.addEdge(new MatrixCor(5, 1), new MatrixCor(6, 1), 1);
        graph.addEdge(new MatrixCor(6, 1), new MatrixCor(6, 2), 1);
        graph.addEdge(new MatrixCor(6, 2), new MatrixCor(5, 2), 1);
        graph.addEdge(new MatrixCor(6, 2), new MatrixCor(7, 2), 1);
        graph.addEdge(new MatrixCor(7, 2), new MatrixCor(8, 2), 1);
        graph.addEdge(new MatrixCor(8, 2), new MatrixCor(8, 3), 1);
        graph.addEdge(new MatrixCor(8, 3), new MatrixCor(8, 4), 1);
        graph.addEdge(new MatrixCor(8, 4), new MatrixCor(9, 4), 1);
        graph.addEdge(new MatrixCor(9, 4), new MatrixCor(9, 3), 1);
        graph.addEdge(new MatrixCor(9, 3), new MatrixCor(9, 2), 1);
        graph.addEdge(new MatrixCor(9, 2), new MatrixCor(9, 1), 1);
        graph.addEdge(new MatrixCor(9, 1), new MatrixCor(10, 1), 1);
        graph.addEdge(new MatrixCor(10, 1), new MatrixCor(10, 0), 1);
        graph.addEdge(new MatrixCor(10, 0), new MatrixCor(9, 0), 1);
        graph.addEdge(new MatrixCor(9, 0), new MatrixCor(8, 0), 1);
        graph.addEdge(new MatrixCor(8, 0), new MatrixCor(7, 0), 1);
        graph.addEdge(new MatrixCor(7, 0), new MatrixCor(7, 1), 1);
        graph.addEdge(new MatrixCor(7, 1), new MatrixCor(8, 1), 1);
        graph.addEdge(new MatrixCor(7, 0), new MatrixCor(6, 0), 1);
        graph.addEdge(new MatrixCor(10, 1), new MatrixCor(10, 2), 1);
        graph.addEdge(new MatrixCor(10, 2), new MatrixCor(10, 3), 1);
        graph.addEdge(new MatrixCor(10, 3), new MatrixCor(10, 4), 1);
        graph.addEdge(new MatrixCor(10, 4), new MatrixCor(10, 5), 1);
        graph.addEdge(new MatrixCor(10, 5), new MatrixCor(10, 6), 1);
        graph.addEdge(new MatrixCor(10, 6), new MatrixCor(10, 7), 1);
        graph.addEdge(new MatrixCor(10, 7), new MatrixCor(9, 7), 1);
        graph.addEdge(new MatrixCor(9, 7), new MatrixCor(8, 7), 1);
        graph.addEdge(new MatrixCor(8, 7), new MatrixCor(8, 6), 1);
        graph.addEdge(new MatrixCor(8, 6), new MatrixCor(8, 5), 1);
        graph.addEdge(new MatrixCor(8, 5), new MatrixCor(7, 5), 1);
        graph.addEdge(new MatrixCor(8, 5), new MatrixCor(9, 5), 1);
        graph.addEdge(new MatrixCor(9, 5), new MatrixCor(9, 6), 1);
        graph.addEdge(new MatrixCor(8, 7), new MatrixCor(8, 8), 1);
        graph.addEdge(new MatrixCor(8, 8), new MatrixCor(8, 9), 1);
        graph.addEdge(new MatrixCor(8, 9), new MatrixCor(8, 10), 1);
        graph.addEdge(new MatrixCor(8, 10), new MatrixCor(9, 10), 1);
        graph.addEdge(new MatrixCor(9, 10), new MatrixCor(10, 10), 1);
        graph.addEdge(new MatrixCor(10, 10), new MatrixCor(10, 9), 1);
        graph.addEdge(new MatrixCor(10, 9), new MatrixCor(10, 8), 1);
        graph.addEdge(new MatrixCor(10, 8), new MatrixCor(9, 8), 1);
        graph.addEdge(new MatrixCor(9, 8), new MatrixCor(9, 9), 1);
        graph.addEdge(new MatrixCor(8, 10), new MatrixCor(7, 10), 1);
        graph.addEdge(new MatrixCor(7, 10), new MatrixCor(7, 9), 1);
        graph.addEdge(new MatrixCor(7, 9), new MatrixCor(7, 8), 1);
        graph.addEdge(new MatrixCor(5, 10), new MatrixCor(4, 10), 1);

        String expectedMap = "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]\n" +
                "[1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1]\n" +
                "[1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1]\n" +
                "[1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1]\n" +
                "[1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1]\n" +
                "[1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1]\n" +
                "[1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1]\n" +
                "[1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1]\n" +
                "[1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1]\n" +
                "[1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1]\n" +
                "[1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1]\n" +
                "[1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1]\n" +
                "[1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1]\n" +
                "[1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1]\n" +
                "[1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1]\n" +
                "[1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1]\n" +
                "[1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1]\n" +
                "[1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]\n" +
                "[1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1]\n" +
                "[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1]\n" +
                "[1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1]\n" +
                "[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1]\n" +
                "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]";

        MapGenerator mapGenerator = new MapGenerator();

        int[][] generatedMap = mapGenerator.generateMap(21, 21, false, true, graph);
        StringBuilder map = new StringBuilder();
        for( int [] row : generatedMap){
            map.append(Arrays.toString(row));
            if (row != generatedMap[generatedMap.length - 1]) {
                map.append("\n");
            }
        }
        assertEquals(expectedMap, map.toString());

    }

    @Test
    void generateMapWithAdjacencyList() {
        MapGenerator mapGenerator = new MapGenerator();
        int[][] generatedMap = mapGenerator.generateMap(5, 5, true, false, null);
        assertEquals(7, generatedMap.length);
        assertEquals(7, generatedMap[0].length);
    }

    @Test
    void generateMapWithDirectedGraph() {
        MapGenerator mapGenerator = new MapGenerator();
        int[][] generatedMap = mapGenerator.generateMap(5, 5, false, true, null);
        assertEquals(7, generatedMap.length);
        assertEquals(7, generatedMap[0].length);
    }

    @Test
    void generateMapWithAdjacencyListAndDirectedGraph() {
        MapGenerator mapGenerator = new MapGenerator();
        int[][] generatedMap = mapGenerator.generateMap(5, 5, true, true, null);
        assertEquals(7, generatedMap.length);
        assertEquals(7, generatedMap[0].length);
    }

    @Test
    void generateMapWithAdjacencyListAndNonDirectedGraph() {
        MapGenerator mapGenerator = new MapGenerator();
        int[][] generatedMap = mapGenerator.generateMap(5, 5, true, false, null);
        assertEquals(7, generatedMap.length);
        assertEquals(7, generatedMap[0].length);
    }

    @Test
    void generateMapWithAdjacencyMatrix() {
        MapGenerator mapGenerator = new MapGenerator();
        int[][] generatedMap = mapGenerator.generateMap(5, 5, false, false, null);
        assertEquals(7, generatedMap.length);
        assertEquals(7, generatedMap[0].length);
    }
    @Test
    void generateMapWithMatrixAndDirectedGraph() {
        MapGenerator mapGenerator = new MapGenerator();
        int[][] generatedMap = mapGenerator.generateMap(5, 5, false, true, null);
        assertEquals(7, generatedMap.length);
        assertEquals(7, generatedMap[0].length);
    }

    @Test
    void generateMapWithMatrixAndNonDirectedGraph() {
        MapGenerator mapGenerator = new MapGenerator();
        int[][] generatedMap = mapGenerator.generateMap(5, 5, false, false, null);
        assertEquals(7, generatedMap.length);
        assertEquals(7, generatedMap[0].length);
    }


}