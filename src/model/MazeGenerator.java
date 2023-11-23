package model;

import java.util.*;

public class MazeGenerator {
    private Random rand;

    public MazeGenerator() {
        rand = new Random();
    }

    //LAS ROW Y LAS COL DEBEN SER IMPAR

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
                System.out.println("graph.addNode(new MatrixCor(" + i + ", " + j + "));");
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
                System.out.println("graph.addEdge(new MatrixCor(" + current.getRow() + ", " + current.getCol() + "), new MatrixCor(" + neighborToConnect.getRow() + ", " + neighborToConnect.getCol() + "), 1);");

                visited[neighborToConnect.getRow()][neighborToConnect.getCol()] = true;
                stack.push(neighborToConnect);
            }
        }

        return graph;
    }

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

    private MatrixCor chooseNotVisitedNeighborRandomly(List<MatrixCor> list) {
        return list.get(rand.nextInt(list.size()));
    }

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

    public boolean validateMatrixCor(MatrixCor cor, int row, int col) {
        return (0 <= cor.getRow() && cor.getRow() < row) && (0 <= cor.getCol() && cor.getCol() < col);
    }


}

