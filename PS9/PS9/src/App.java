import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {

    }
    public void solve(int[][] capacity, int[][] flow, int[][] residual, int[] path, int s, int t, int absV) {
        long absFlow = 0;
        while (path[path.length-1] != -1) { //while path is not empty 
            buildResidualGraph(capacity, residual, flow, absV);
            absFlow = findAugmentingPath(residual, path, s, t, absV, capacity);
            augmentFlow(flow, residual, path);
    }
        
        //TODO: flow has optimal flow, now create output
    }
    
    public void buildResidualGraph(int[][] capacity, int[][] residual, int[][] flow, int absV) {
        for (int u = 0; u < absV; u++) {
            for (int v = 0; v < absV; v++) {
                if (capacity[u][v] > 0) {
                    if (capacity[u][v]-flow[u][v] > 0) { //we can still fit more flow
                        residual[u][v] = capacity[u][v]-flow[u][v];
                    } else if (flow[u][v] > 0) { //our backflow
                    residual[v][u] = flow[u][v];
                    }
                }
            }
        }   
    }
    
    //distances = amount of flow through an edge
    //we would typically want to find the shortest path but here we want to find the longest path (path with the largest number, representing amount of flow)
    public void initSSSP(int startVertex, long[] distances, int[] pred) {
        distances[startVertex] = Long.MAX_VALUE;
        pred[startVertex] = -1;
    
        for (int i = 0; i < distances.length; i++) {
            if (i != startVertex) {
                distances[i] = 0;
                pred[i] = -1;
            }
        }
    }
    
    public long findAugmentingPath(int[][] residual, int[] path, int s, int t, int absV, int[][] capacity) {
        long pathFlow = 0;
        long[] distances = new long[absV];
        int[] pred = new int[absV];
        //find shortest to all path using dijkstras
        initSSSP(s, distances, pred);
    
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Collections.reverseOrder());
        Vertex startVertex = new Vertex(s, residual[s][s], residual[s][s], new ArrayList<>(Arrays.asList(s)));
        pq.add(startVertex);
            
        while (!pq.isEmpty()) {
            
        }
        //find path from t to s and assign to ‘path’
        /*
        Find max flow, but if 2 or more paths have the same flow then we use tiebreakers.
        tiebreakers:
        1.	Find the augmenting path with the fewest edges.
        2.	choose the one with the greatest feasible flow from s to t.  (The smallest residual capacity along the path defines this greatest feasible flow.)
        3.	choose the path that has the smallest vertex number in the last position in the path.
            find all paths and filter accordingly
        */
        return pathFlow;
    }

    public void augmentFlow(int[][] flow, int[][] residual, int[] path) {
        for (int e = 0; e < path.length-1; e++) {
            int u = path[e];
            int v = path[e+1];
            /*
            If the residual is opposite direction of flow direction
            then we subtract the flow
        	How can we tell if they’re opposite directions?
        	If flow from u->v is 0 (doesn’t exist), then we subtract it from v->u
            */
            //if they’re opposite directions
            if (flow[u][v] == 0) {
                flow[v][u] -= residual[u][v];
            } else {
            //if they’re the same direction
            //add flow from u->v from residual graph
            flow[u][v] += residual[u][v];
            }
        }
    }
}

class Vertex implements Comparable<Vertex>{
    int name;
    long flowTo;
    int edgesTo;
    long feasibleFlow;
    ArrayList<Integer> pathTo;

    public Vertex(int name, long flowTo, long feasibleFlow, ArrayList<Integer> pathTo) {
        this.name = name;
        this.flowTo = flowTo;
        this.edgesTo = 0;
        this.feasibleFlow = feasibleFlow;
        this.pathTo = pathTo;
    }

    @Override
    public boolean equals(Object o) {
        return compareTo((Vertex)o) == 0;
    }
    @Override
    public int compareTo(Vertex o) {
        // TODO Auto-generated method stub  
        
        if ( this.flowTo > o.flowTo) {
            return 1;
        } else if (this.flowTo < o.flowTo) {
            return -1;
        } else if (this.edgesTo < o.edgesTo) {
            return 1;
        } else if (this.edgesTo > o.edgesTo) {
            return -1;
        } else if (this.feasibleFlow < o.feasibleFlow) {
            return 1;
        } else if (this.feasibleFlow > o.feasibleFlow) {
            return -1;
        } 
        Stack<Integer> s1 = new Stack<>();
        for (int i = 0; i < pathTo.size(); i++) {
            s1.push(pathTo.get(i));
        }
        Stack<Integer> s2 = new Stack<>();
        for (int i = 0; i < o.pathTo.size(); i++) {
            s2.push(o.pathTo.get(i));
        }

        while (!s1.isEmpty() && !s2.isEmpty()) {
            int u = s1.pop();
            int v = s2.pop();

            if (u > v) {
                return 1;
            } else if (u < v) {
                return -1;
            }
        }

        return 0;
    }
}
