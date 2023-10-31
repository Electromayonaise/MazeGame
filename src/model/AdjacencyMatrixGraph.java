package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdjacencyMatrixGraph<T> implements Graph<T> {
    private ArrayList<ArrayList<Integer>> dynamicMatrix;

    //The map is used for knowing what is the
    // row associated to each node.
    private LinkedHashMap<T,Integer> map;

    private final int ADDITION=1;

    public AdjacencyMatrixGraph() {
        dynamicMatrix=new ArrayList<>();

        //el mapa debe conservar el orden en el que
        //se añadan los elementos, por eso usamos linkedhashmap
        map=new LinkedHashMap<>();
    }

    @Override
    public boolean addNode(T node) {
        boolean flag=false;
        if(!map.containsKey(node)){
            int assignedRow=dynamicMatrix.size();
            map.put(node,assignedRow);
            addOneColumnAndRow();
            flag=true;
        }


        return flag;
    }
    public void addOneColumnAndRow(){
        //we add a new row
        int initialCapacity=dynamicMatrix.size();

        //we add a new row

        dynamicMatrix.add(new ArrayList<>(initialCapacity));

        for (int i = 0; i < initialCapacity; i++) {
          //  we add zeroes to the last row
            dynamicMatrix.get(dynamicMatrix.size()-1).add(0);
        }

        for(ArrayList<Integer> subList:dynamicMatrix){
            //we add a new column of zeros
            subList.add(0);
        }

    }

    @Override
    public boolean addEdge(T node1, T node2, boolean bidirectional) {
        boolean flag=false;
        if(map.containsKey(node1)&&map.containsKey(node2)){
            int rowOfNode1=map.get(node1);
            int columnOfNode2=map.get(node2);
            int currentVal=dynamicMatrix.get(rowOfNode1).get(columnOfNode2);
            dynamicMatrix.get(rowOfNode1).set(columnOfNode2,++currentVal);
            flag=true;
        }

        return flag;
    }





    @Override
    public String toString() {
        String msg="";
        for(Map.Entry<T, Integer> iterator: map.entrySet()){
            msg+=iterator.getKey()+"    ";
            msg+= dynamicMatrix.get ( iterator.getValue());
            msg+="\n";
        }

        return msg;
    }
}
