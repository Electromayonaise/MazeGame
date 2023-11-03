package tests;
import model.AdjacencyMatrixGraph;
import model.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AdjacencyMatrixTestDirectedAndWeighted {

    private Graph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyMatrixGraph<>(true, true);
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
        graph.addEdge(1, 2, 1);

        assertTrue(graph.nodeInfluenceDirectly(1, 2));
        assertFalse(graph.nodeInfluenceDirectly(2, 1));
    }

    @Test
    void testToString() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 1);

        String expected = "Adjacency Matrix:\n   1  2  \n1  0  1  \n2  0  0  \n";
        assertEquals(expected, graph.toString());
    }

}
