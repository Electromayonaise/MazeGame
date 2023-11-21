package ui;

import model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner reader;
    private Graph<Integer> graph1;

    public Main() {
        reader = new Scanner(System.in);
      //  chooseGraphType();
    }

    public static void main(String[] args) {
        Main main = new Main();
        test();
      //  main.menu();
       // System.out.println("Bye");
    }
    public static void test() {
        MapGenerator mapGenerator = new MapGenerator();
        PathFinder pathFinder = new PathFinder();

        // Generate a map
        int[][] map = mapGenerator.generateMap(21, 21, false, true);

        // Display the generated map (optional)
        System.out.println("Generated Map:");
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }

        // Define origin and destination nodes
        MatrixCor origin = new MatrixCor(1, 1);
        MatrixCor destination = new MatrixCor(21, 21);

        // Get the shortest path
        List<MatrixCor> path = pathFinder.getShortestPath(origin, destination, map, false, true);

        // Display the generated path (optional)
        System.out.println("Shortest Path:");
        System.out.println(path);
    }


    public void chooseGraphType() {
        int choice;
        do {
            System.out.println("Choose the graph implementation:");
            System.out.println("1. Adjacency List Graph");
            System.out.println("2. Adjacency Matrix Graph");
            choice = validateInt();
        } while (choice != 1 && choice != 2);

        boolean isWeighted = askForWeightedGraph();

        boolean isDirected = askForDirectedGraph();

        if (choice == 1) {
            graph1 = new AdjacencyListGraph<>(isWeighted, isDirected);
        } else {
            graph1 = new AdjacencyMatrixGraph<>(isWeighted, isDirected);
        }
    }

    public boolean askForWeightedGraph() {
        System.out.println("Is the graph weighted? (y/n)");
        String ans = reader.next();
        return ans.equalsIgnoreCase("y");
    }

    public boolean askForDirectedGraph() {
        System.out.println("Is the graph directed? (y/n)");
        String ans = reader.next();
        return ans.equalsIgnoreCase("y");
    }

    public void menu() {
        int ans;
        boolean flag = true;
        while (flag) {
            System.out.println("Type 1 to add a vertex");
            System.out.println("Type 2 to add an edge");
            System.out.println("Type 3 to print the graph");
            System.out.println("Type 0 to exit the program");
            ans = validateInt();
            switch (ans) {
                case 1 -> func1();
                case 2 -> func2();
                case 3 -> func3();
                case 0 -> flag = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    public void func1() {
        System.out.println("Type an integer that represents the node id");
        int id = validateInt();
        boolean flag = graph1.addNode(id);
        if (flag) {
            System.out.println("The node was added successfully");
        } else {
            System.out.println("There already exists a node with this id in the graph");
        }
    }

    public void func2() {
        boolean flag;
        System.out.println("Type the initial node of the edge");
        int node1 = validateInt();
        System.out.println("Type the final node of the edge");
        int node2 = validateInt();

        int weight = 1; // Default weight for unweighted graphs.
        if (graph1.isWeighted()) {
            System.out.println("Enter the weight of the edge:");
            weight = validateInt();
        }

        flag = graph1.addEdge(node1, node2, weight);

        if (flag) {
            System.out.println("The edge was added successfully");
        } else {
            System.out.println("The edge was not added. Maybe one of the nodes was not found");
        }
    }

    public void func3() {
        String msg = graph1.toString();
        System.out.println(msg);
    }

    public int validateInt() {
        int ans = 0;
        boolean cond;
        do {
            if (reader.hasNextInt()) {
                ans = reader.nextInt();
                cond = true;
            } else {
                System.out.println("Invalid value");
                cond = false;
                reader.nextLine();
            }

        } while (!cond);
        return ans;
    }
}

