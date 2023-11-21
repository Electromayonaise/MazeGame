package main_module.model.util;
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
        List<T> shortestPath = new ArrayList<>();
        return bfs(origin, destination);
    }

    private List<T> bfs(T origin, T destination) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(origin);
        visited.add(origin);
        T current;
        // Map where the key is a node in the path and the value is the node it comes from
        Map<T, T> mapOfPrevs = new HashMap<>();
        boolean pathWasFound = false;
        List<T> path = new ArrayList<>();

        while (!queue.isEmpty() && !pathWasFound) {
            current = queue.poll();
            int currentIndex = map.get(current);

            // Iterate over neighbors using the adjacency matrix
            for (int i = 0; i < dynamicMatrix.size(); i++) {
                if (dynamicMatrix.get(currentIndex).get(i) > 0) {
                    T neighbor = getKeyByValue(map, i);

                    if (!visited.contains(neighbor)) {
                        // We reached the neighbor through the current node
                        mapOfPrevs.put(neighbor, current);
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            if (visited.contains(destination)) {
                pathWasFound = true;
            }
        }

        if (pathWasFound) {
            System.out.println("EUREKA");
            path = buildPath(origin, destination, mapOfPrevs);
        }

        return path;
    }

    private List<T> buildPath(T origin, T destination, Map<T, T> mapOfPrevs) {
        List<T> list = new ArrayList<>();

        T current = destination;
        while (current != origin) {
            list.add(current);
            current = mapOfPrevs.get(current);
        }
        Collections.reverse(list);
        return list;
    }

    private T getKeyByValue(Map<T, Integer> map, int value) {
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }



}





