package model;

import java.util.*;

public class AdjacencyMatrixGraph<T> implements Graph<T> {
    private ArrayList<ArrayList<Integer>> dynamicMatrix;
    private LinkedHashMap<T, Integer> map;
    private boolean weighted;

    private boolean directed;

    public AdjacencyMatrixGraph(boolean weighted, boolean directed) {
        dynamicMatrix = new ArrayList<>();
        map = new LinkedHashMap<>();
        this.weighted = weighted;
        this.directed = directed;
    }

    @Override
    public boolean addNode(T node) {
        boolean flag = false;
        if (!map.containsKey(node)) {
            int assignedRow = dynamicMatrix.size();
            map.put(node, assignedRow);
            addOneColumnAndRow();
            flag = true;
        }
        return flag;
    }

    private void addOneColumnAndRow() {
        int initialCapacity = dynamicMatrix.size();
        dynamicMatrix.add(new ArrayList<>(initialCapacity));

        for (int i = 0; i < initialCapacity; i++) {
            dynamicMatrix.get(dynamicMatrix.size() - 1).add(0);
        }

        for (ArrayList<Integer> subList : dynamicMatrix) {
            subList.add(0);
        }
    }

    @Override
    public boolean addEdge(T node1, T node2, int weight) {
        boolean flag = false;
        if (map.containsKey(node1) && map.containsKey(node2)) {
            int rowOfNode1 = map.get(node1);
            int columnOfNode2 = map.get(node2);

            if (weighted) {
                dynamicMatrix.get(rowOfNode1).set(columnOfNode2, weight);
            } else {
                int currentVal = dynamicMatrix.get(rowOfNode1).get(columnOfNode2);
                dynamicMatrix.get(rowOfNode1).set(columnOfNode2, ++currentVal);
            }

            flag = true;

            if (!directed) {
                int rowOfNode2 = map.get(node2);
                int columnOfNode1 = map.get(node1);

                if (weighted) {
                    dynamicMatrix.get(rowOfNode2).set(columnOfNode1, weight);
                } else {
                    int currentVal = dynamicMatrix.get(rowOfNode2).get(columnOfNode1);
                    dynamicMatrix.get(rowOfNode2).set(columnOfNode1, ++currentVal);
                }
            }
        }

        return flag;
    }

    @Override
    public boolean nodeInfluenceDirectly(T node1, T node2) {
        boolean flag = false;
        if (map.containsKey(node1) && map.containsKey(node2)) {
            int rowOfNode1 = map.get(node1);
            int columnOfNode2 = map.get(node2);

            if (weighted) {
                flag = dynamicMatrix.get(rowOfNode1).get(columnOfNode2) > 0;
            } else {
                int currentVal = dynamicMatrix.get(rowOfNode1).get(columnOfNode2);
                flag = currentVal > 0 || currentVal > 0;
            }
        }
        return flag;
    }

    @Override
    public int getWeight(T node1, T node2) {
        if (map.containsKey(node1) && map.containsKey(node2)) {
            int rowOfNode1 = map.get(node1);
            int columnOfNode2 = map.get(node2);
            return dynamicMatrix.get(rowOfNode1).get(columnOfNode2);
        }
        return -1; // Return -1 for non-existent or unconnected nodes.
    }

    @Override
    public List<T> getNeighbors(T node) {
        List<T> neighbors = new ArrayList<>();
        if (map.containsKey(node)) {
            int row = map.get(node);
            for (Map.Entry<T, Integer> entry : map.entrySet()) {
                int column = entry.getValue();
                int weight = dynamicMatrix.get(row).get(column);
                if (column != row && weight > 0) {
                    neighbors.add(entry.getKey());
                }
            }
        }
        return neighbors;
    }

    @Override
    public String toString() {
        int size = dynamicMatrix.size();
        StringBuilder msg = new StringBuilder("Adjacency Matrix:\n");

        // Print column headers
        msg.append("   ");
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            msg.append(entry.getKey()).append("  ");
        }
        msg.append("\n");

        for (int i = 0; i < size; i++) {
            int finalI = i;
            T rowNode = map.entrySet().stream()
                    .filter(e -> e.getValue() == finalI)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            msg.append(rowNode).append("  ");

            for (int j = 0; j < size; j++) {
                int weight = dynamicMatrix.get(i).get(j);
                msg.append(weight).append("  ");
            }

            msg.append("\n");
        }

        return msg.toString();
    }

    @Override
    public boolean isWeighted() {
        return weighted;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public List<T> getShortestPath(T origin, T destination) {

        floydWarshall();
        int originIndex = map.get(origin);
        int destinationIndex = map.get(destination);

        if (dynamicMatrix.get(originIndex).get(destinationIndex) == 0) {
            // No path exists
            return Collections.emptyList();
        }

        List<T> shortestPath = new ArrayList<>();
        shortestPath.add(origin);

        while (!origin.equals(destination)) {
            originIndex = map.get(origin);
            destinationIndex = map.get(destination);
            destination = getKeyByValue(map, dynamicMatrix.get(originIndex).get(destinationIndex));
            shortestPath.add(destination);
        }

        return shortestPath;
    }

    private T getKeyByValue(Map<T, Integer> map, int value) {
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void floydWarshall() {
        int size = dynamicMatrix.size();

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int directPath = dynamicMatrix.get(i).get(j);
                    int throughK = dynamicMatrix.get(i).get(k) + dynamicMatrix.get(k).get(j);
                    if (throughK < directPath) {
                        dynamicMatrix.get(i).set(j, throughK);
                    }
                }
            }
        }
    }



}


