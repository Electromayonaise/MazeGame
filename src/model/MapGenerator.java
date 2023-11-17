package model;

import java.util.List;

public class MapGenerator {
    private MazeGenerator mazeGenerator;
    public MapGenerator() {
        mazeGenerator=new MazeGenerator();
    }
    public int[][] generateMap(int row, int col,boolean adjacencyList,boolean directed){
        int[][] map =new int[row][col];
        boolean[][] visited=new boolean[row/2+1][col/2+1];
        Graph<MatrixCor>graph= mazeGenerator.generateMaze(row/2 +1,col/2 +1,adjacencyList,directed);
        MatrixCor initial=new MatrixCor(0,0);
        map=dfs(graph,initial,visited,map);
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        return map;
    }
    public  int[][] dfs(Graph<MatrixCor> graph,MatrixCor current,boolean[][] visited, int[][]map){
        visited[current.getRow()][current.getCol()]=true;
        map[current.getRow()*2][current.getCol()*2]=1;
        List<MatrixCor> neighbors=graph.getNeighbors(current);
        for(MatrixCor neighbor: neighbors){
            int midRowInMap= (neighbor.getRow()*2+current.getRow()*2)/2;
            int midColInMap=(neighbor.getCol()*2+ current.getCol()*2)/2;
            map[midRowInMap][midColInMap]=1;
            if(!visited[neighbor.getRow()][neighbor.getCol()]){
                int [][] aux=dfs(graph,neighbor,visited,map);
                map=aux;
            }

        }
        return map;

    }
}
