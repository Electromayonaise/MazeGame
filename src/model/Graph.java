package model;

public interface Graph <T>{
    boolean addNode(T node);
    boolean addEdge(T node1, T node2,boolean bidirectional);



}
