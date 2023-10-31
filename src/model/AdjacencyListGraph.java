package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdjacencyListGraph <T> implements Graph<T> {
    HashMap<T,ArrayList<T>> map;

    public AdjacencyListGraph() {
        map=new HashMap<>();
    }

    public boolean addNode(T node){
        boolean flag=false;
        if(!map.containsKey(node)){
            map.put(node,new ArrayList<>());
            flag=true;
        }
        return flag;
    }

    public boolean addEdge(T node1, T node2,boolean bidirectional){
        boolean flag=false;
        if(map.containsKey(node1)&& map.containsKey(node2)){
            ArrayList<T> neighbors=map.get(node1);
            neighbors.add(node2);
            if(bidirectional) {
                neighbors = map.get(node2);
                neighbors.add(node1);
            }
            flag=true;
        }

        return flag;
    }


    public boolean nodeInfluenceDirectly(T node1, T node2){
        boolean flag=false;
        if(map.containsKey(node1)){
            for(T neighbor: map.get(node1) ){
                if (neighbor.equals(node2)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    @Override
    public String toString(){

        String msg="";

        for(Map.Entry<T,ArrayList<T>> iterator: map.entrySet()){
            msg +=( (T) iterator.getKey() ) .toString()+"-> { ";

            ArrayList<T> neighbors = (ArrayList<T>) iterator.getValue();
            for(T x: neighbors){
                msg+=x+",";
            }
            msg+="}\n";
        }


        return msg;
    }









}


