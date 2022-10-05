import java.util.*;

public class GraphNode {
    int[][] cube;
    int cost;
    GraphNode parent;
    int facet;
    int turn;
    // public ArrayList<GraphNode> children;

    public GraphNode(int[][] input,GraphNode prev,int c,int face, int turnI){
        cube = input;
        parent = prev;
        cost = c + heuristic(input);
        facet = face;
        turn  = turnI;

        // children = new ArrayList<>();

    }

    // public GraphNode(GraphNode node){
    //     cube = node.cube;
    //     h = node.h;
    //     parent = node.parent;
    //     facet = node.facet;
    //     turn = node.turn;

    //     children = node.children;
    // }

    public static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }    

    // public static void CreateChildren(GraphNode curr, int level){
    //     for (int i = 1; i <= 6; i++){
    //         GraphNode cwTemp = new GraphNode(cw(curr.cube, i) ,curr, level ,i,1);
    //         curr.children.add(cwTemp);
    //         // GraphNode ccwTemp = new GraphNode(ccw(curr.cube, i),curr,c,i,-1);
    //         // curr.children.add(ccwTemp);
    //     }
    // }

    public static int heuristic (int[][] cube){
        int h = 0;

        for(int[] facet :cube){
            HashSet<Integer> temp = new HashSet<>();
            for(int tile : facet){
                temp.add(tile);
            }
            int colours = temp.size();
            if(colours == 4){
                h += 4;
            }
            else if(colours == 3){
                h += 2;
            }
            else if(colours == 2){
                h++;
            }
        }
        return h;
    }


    public static int[][] cw(int[][] cube, int facet){
        
        int [][] temp = GraphNode.cloneArray(cube);
        temp[facet][0] = cube[facet][2];
        temp[facet][1] = cube[facet][0];
        temp[facet][2] = cube[facet][3];
        temp[facet][3] = cube[facet][1];

        if(facet == 0){
            temp[1][0] = cube[2][0];
            temp[1][1] = cube[2][1];
            
            temp[2][0] = cube[3][0];
            temp[2][1] = cube[3][1];
            
            temp[3][0] = cube[5][3];
            temp[3][1] = cube[5][2];
            
            temp[5][2] = cube[1][1];
            temp[5][3] = cube[1][0];      
        }
        else if(facet == 1){

            temp[0][0] = cube[5][0];
            temp[0][2] = cube[5][2];

            temp[2][0] = cube[0][0];
            temp[2][2] = cube[0][2];
            
            temp[4][0] = cube[2][0];
            temp[4][2] = cube[2][2];
        
            temp[5][0] = cube[4][0];
            temp[5][2] = cube[4][2]; 
        }
        else if(facet == 2){

            temp[0][2] = cube[1][3];
            temp[0][3] = cube[1][1];
            
            temp[3][0] = cube[0][2];
            temp[3][2] = cube[0][3];
            
            temp[4][1] = cube[3][0];
            temp[4][0] = cube[3][2];
            
            temp[1][3] = cube[4][1];
            temp[1][1] = cube[4][0]; 
        }
        else if(facet == 3){
            temp[0][1] = cube[2][1];
            temp[0][3] = cube[2][3];
            
            temp[5][3] = cube[0][3];
            temp[5][1] = cube[0][1];
            
            temp[4][3] = cube[5][3];
            temp[4][1] = cube[5][1];
            
            temp[2][3] = cube[4][3];
            temp[2][1] = cube[4][1]; 
        }
        else if(facet == 4){

            temp[2][2] = cube[1][2];
            temp[2][3] = cube[1][3];
            
            temp[3][2] = cube[2][2];
            temp[3][3] = cube[2][3];
            
            temp[5][1] = cube[3][2];
            temp[5][0] = cube[3][3];
            
            temp[1][2] = cube[5][1];
            temp[1][3] = cube[5][0]; 
        }
        else if(facet == 5){
            
            temp[4][2] = cube[1][0];
            temp[4][3] = cube[1][2];
            
            temp[3][3] = cube[4][2];
            temp[3][1] = cube[4][3];
            
            temp[0][1] = cube[3][3];
            temp[0][0] = cube[3][1];
            
            temp[1][0] = cube[0][1];
            temp[1][2] = cube[0][0]; 
        }
        return temp;
    }

        public static int[][] ccw(int[][] cube, int facet){
        
        int [][] temp = GraphNode.cloneArray(cube);
        temp[facet][0] = cube[facet][1];
        temp[facet][1] = cube[facet][3];
        temp[facet][2] = cube[facet][0];
        temp[facet][3] = cube[facet][2];

        if(facet == 0){
            temp[3][0] = cube[2][0];
            temp[3][1] = cube[2][1];
            
            temp[5][3] = cube[3][0];
            temp[5][2] = cube[3][1];
            
            temp[1][0] = cube[5][3];
            temp[1][1] = cube[5][2];
            
            temp[2][1] = cube[1][1];
            temp[2][0] = cube[1][0];      
        }
        else if(facet == 1){

            temp[0][0] = cube[2][0];
            temp[0][2] = cube[2][2];
            
            temp[2][0] = cube[4][0];
            temp[2][2] = cube[4][2];
            
            temp[4][0] = cube[5][0];
            temp[4][2] = cube[5][2];
            
            temp[5][0] = cube[0][0];
            temp[5][2] = cube[0][2]; 
        }
        else if(facet == 2){

            temp[0][2] = cube[3][0];
            temp[0][3] = cube[3][2];
            
            temp[3][0] = cube[4][1];
            temp[3][2] = cube[4][0];
            
            temp[4][1] = cube[1][3];
            temp[4][0] = cube[1][1];
            
            temp[1][3] = cube[0][2];
            temp[1][1] = cube[0][3]; 
        }
        else if(facet == 3){
            temp[0][3] = cube[5][3];
            temp[0][1] = cube[5][1];
            
            temp[5][3] = cube[4][3];
            temp[5][1] = cube[4][1];
            
            temp[4][3] = cube[2][3];
            temp[4][1] = cube[2][1];
            
            temp[2][3] = cube[0][3];
            temp[2][1] = cube[0][1]; 
        }
        else if(facet == 4){

            temp[2][2] = cube[3][2];
            temp[2][3] = cube[3][3];

            temp[3][2] = cube[5][1];
            temp[3][3] = cube[5][0];
            
            temp[5][1] = cube[1][2];
            temp[5][0] = cube[1][3];

            temp[1][2] = cube[2][2];
            temp[1][3] = cube[2][3]; 
        }
        else if(facet == 5){
            
            temp[4][2] = cube[3][3];
            temp[4][3] = cube[3][1];

            temp[3][3] = cube[0][1];
            temp[3][1] = cube[0][0];
            
            temp[0][1] = cube[1][0];
            temp[0][0] = cube[1][2];

            temp[1][0] = cube[4][2];
            temp[1][2] = cube[4][3]; 
        }
        return temp;
    }
}
