import java.lang.reflect.Array;
import java.util.*;




class Solution{
    public static int explored = 0;
    public static int produced = 0;

    public static ArrayList<GraphNode> CreateChildren(GraphNode curr, int level){
        ArrayList<GraphNode> children = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            GraphNode cwTemp = new GraphNode(GraphNode.cw(curr.cube, i), curr, level, i, 1);
            GraphNode ccwTemp = new GraphNode(GraphNode.ccw(curr.cube, i), curr, level , i, -1); 
            // System.out.println(cwTemp.facet + " " + ccwTemp.facet);  
            children.add(cwTemp);
            children.add(ccwTemp);    
        }

        return children;
    }
    
    public static void aStart(GraphNode start, Stack<GraphNode> ret){
        // HashSet<int[][]> seen = new HashSet<>();
        ArrayList<int[][]> seen = new ArrayList<>();
        PriorityQueue<GraphNode> queue = new PriorityQueue<>(
            (a,b) -> a.cost - b.cost
        );
        queue.add(start);
        int level = 1;
        int repeat = 0;

        while(!queue.isEmpty()){
            
            GraphNode n = queue.poll();
            seen.add(n.cube);
            explored++;

            System.out.println("cost = "+n.cost);
            System.out.println(Arrays.deepToString(n.cube));
            System.out.println("expanded "+ queue.size());
            System.out.println(level);
       
            if(GraphNode.heuristic(n.cube) == 0){
                ret.push(n);
                while(n.parent!= null){
                    n = n.parent;
                    ret.push(n);
                }
                System.out.println(level);
                System.out.println(repeat);
                return;
            }

            for(GraphNode entry : CreateChildren(n, level)){
                boolean isSeen = false;
                for(int[][] cube : seen){
                    if(Arrays.deepEquals(cube, entry.cube)){
                        isSeen = true;
                        break;
                    }
                }
                if(isSeen == false){
                    queue.add(entry);  
                    produced++;
                }
            }
            level++;
        }
        return;
    }
    
    public static void Summary(Stack<GraphNode> ret){
        System.out.println("Starting Cube Colours:");
        for(int i = 0; i <= 5; i++){
            for(int j = 0; j <=3 ; j++){
                System.out.print(ret.lastElement().cube[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Ending Cube Colours");
        System.out.println(Arrays.deepToString(ret.firstElement().cube));
        System.out.println("Produced Nodes: "+produced);
        System.out.println("Expanded Nodes: "+explored);
        int answerDepth = ret.size() -1;
        System.out.println("Answer Depth: "+ answerDepth);
        System.out.println("Max Memory: "+produced);
        System.out.println("\nSolution");
        while(!ret.isEmpty()){
            GraphNode curr = ret.pop();
            int facet = curr.facet+1;
            if(curr.turn == 1){
                System.out.println("turn facet#"+ facet + " clockwise");
            }
            else if(curr.turn == -1){
                System.out.println("turn facet#"+ facet  +" anticlockwise");
            }
            else{
                // System.out.println("Cube Solved");
            }
        }
    }
    
    
    
    public static void main(String arg[]){

        // int[][] cube = {{4,4,2,2},{1,3,5,6},{1,1,5,5},{6,3,6,3},{4,2,2,4},{3,1,6,5}}; //assignment
        int[][] cube = {{2,1,2,1},{3,3,6,6},{1,4,2,5},{6,6,3,3},{5,4,5,4},{4,1,5,2}}; // cw 3 cw5 cw 1
        // int[][] cube = {{2,4,2,5},{3,3,6,6},{1,4,2,4},{3,6,3,6},{5,1,5,2},{4,1,5,1}}; // cw 3 cw5 cw 1 cw 4
        // int[][] cube = {{4,4,1,1},{1,2,1,2},{3,3,3,3},{4,5,4,5},{5,5,2,2},{6,6,6,6}}; //cw face 6
        // int[][] cube = {{6,1,6,1},{2,2,2,2},{1,3,1,3},{4,4,4,4},{3,5,3,5},{5,6,5,6}}; //cw face 2
        // int[][] cube = {{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4},{5,5,5,5},{6,6,6,6}}; //base
        GraphNode start = new GraphNode(cube,null,0,-1,0);
        // System.out.println(Arrays.deepToString(start.cube));
        // GraphNode.CreateChildren(start,0);
        // for(GraphNode arr : start.children){
        //     System.out.println(Arrays.deepToString(arr.cube));
        // }
       

        // System.out.println(Arrays.deepToString(GraphNode.cloneArray(cube)));;
        // System.out.println(GraphNode.heuristic(cube));

        Stack<GraphNode> ret = new Stack<>();
        long startTime = System.nanoTime();
        aStart(start, ret);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000000;  
        System.out.println("A* runtime:" + duration);
        Summary(ret);

        return;
    }
}
