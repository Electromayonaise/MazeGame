package main_module.tests;

import main_module.model.util.AdjacencyListGraph;
import main_module.model.util.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListTestsNotDirected {
    private Graph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyListGraph<>(false, false);
    }

    @Test
    void addNode() {
        assertTrue(graph.addNode(1));
        assertFalse(graph.addNode(1));
    }

    @Test
    void addEdge() {
        graph.addNode(1);
        graph.addNode(2);

        assertTrue(graph.addEdge(1, 2, 1));
        assertFalse(graph.addEdge(1, 3, 1));
    }

    @Test
    void nodeInfluenceDirectly() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2,1);

        assertTrue(graph.nodeInfluenceDirectly(1, 2));
        assertTrue(graph.nodeInfluenceDirectly(2, 1));
    }

    @Test
    void testToString() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 1);

        String expected = "Adjacency List:\n1 -> { (2) }\n2 -> { (1) }\n";
        assertEquals(expected, graph.toString());
    }
}
