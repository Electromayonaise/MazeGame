package tests;

import model.AdjacencyListGraph;
import model.Graph;
import model.MatrixCor;
import model.PathFinder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ShortestPathTesting {

    @Test
    void testExpectedShortestPath() {

        PathFinder path = new PathFinder();

        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1} };

        String expectedShortestPath = "[row2 | col1, row3 | col1, row3 | col2, row3 | col3, row3 | col4, row3 | col5, row2 | col5, row1 | col5, row1 | col6, row1 | col7, row1 | col8, row1 | col9, row1 | col10, row1 | col11, row1 | col12, row1 | col13, row1 | col14, row1 | col15, row1 | col16, row1 | col17, row2 | col17, row3 | col17, row4 | col17, row5 | col17, row5 | col16, row5 | col15, row5 | col14, row5 | col13, row6 | col13, row7 | col13, row8 | col13, row9 | col13, row10 | col13, row11 | col13, row11 | col12, row11 | col11, row12 | col11, row13 | col11, row13 | col12, row13 | col13, row13 | col14, row13 | col15, row13 | col16, row13 | col17, row13 | col18, row13 | col19, row13 | col20, row13 | col21, row14 | col21, row15 | col21, row16 | col21, row17 | col21, row17 | col20, row17 | col19, row16 | col19, row15 | col19, row15 | col18, row15 | col17, row16 | col17, row17 | col17, row18 | col17, row19 | col17, row19 | col18, row19 | col19, row20 | col19, row21 | col19, row21 | col20, row21 | col21]";

        MatrixCor origin = new MatrixCor(1, 1);
        MatrixCor destination = new MatrixCor(21, 21);
        List<MatrixCor> shortestPath = path.getShortestPath( origin, destination, maze, false, true);
        assertEquals(expectedShortestPath, shortestPath.toString());
    }

    @Test
    void NoPath(){
        PathFinder path = new PathFinder();

        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1} };

        String expectedShortestPath = "[]";

        MatrixCor origin = new MatrixCor(1, 1);
        MatrixCor destination = new MatrixCor(8, 8);
        List<MatrixCor> shortestPath = path.getShortestPath( origin, destination, maze, true, true);
        assertEquals(expectedShortestPath, shortestPath.toString());
    }

    @Test
    void pathForDirectedAndAdjacencyList(){
        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1} };
        PathFinder path = new PathFinder();
        MatrixCor origin = new MatrixCor(1, 1);
        MatrixCor destination = new MatrixCor(7, 7);
        List<MatrixCor> shortestPath = path.getShortestPath( origin, destination, maze, true, true);
        assertEquals("[row1 | col2, row2 | col2, row2 | col3, row2 | col4, row3 | col4, row4 | col4, row4 | col5, row4 | col6, row4 | col7, row5 | col7, row6 | col7, row7 | col7]", shortestPath.toString());

    }

    @Test
    void pathForDirectedAndAdjacencyMatrix(){
        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1} };
        PathFinder path = new PathFinder();
        MatrixCor origin = new MatrixCor(1, 1);
        MatrixCor destination = new MatrixCor(7, 7);
        List<MatrixCor> shortestPath = path.getShortestPath( origin, destination, maze, false, true);
        assertEquals("[row1 | col2, row2 | col2, row2 | col3, row2 | col4, row3 | col4, row4 | col4, row4 | col5, row4 | col6, row4 | col7, row5 | col7, row6 | col7, row7 | col7]", shortestPath.toString());

    }

    @Test
    void pathForUndirectedAndAdjacencyList(){
        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1} };
        PathFinder path = new PathFinder();
        MatrixCor origin = new MatrixCor(1, 1);
        MatrixCor destination = new MatrixCor(7, 7);
        List<MatrixCor> shortestPath = path.getShortestPath( origin, destination, maze, true, false);
        assertEquals("[row1 | col2, row2 | col2, row2 | col3, row2 | col4, row3 | col4, row4 | col4, row4 | col5, row4 | col6, row4 | col7, row5 | col7, row6 | col7, row7 | col7]", shortestPath.toString());

    }

    @Test
    void pathForUndirectedAndAdjacencyMatrix(){
        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1} };
        PathFinder path = new PathFinder();
        MatrixCor origin = new MatrixCor(1, 1);
        MatrixCor destination = new MatrixCor(7, 7);
        List<MatrixCor> shortestPath = path.getShortestPath( origin, destination, maze, false, false);
        assertEquals("[row1 | col2, row2 | col2, row2 | col3, row2 | col4, row3 | col4, row4 | col4, row4 | col5, row4 | col6, row4 | col7, row5 | col7, row6 | col7, row7 | col7]", shortestPath.toString());

    }



}
