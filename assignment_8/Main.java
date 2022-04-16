import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) {
        /*
        //test ISSP        0     1     2    3
        long[] testDist = {9l, 10l,   0l, 99l};
        int[] testPred = {9,   10,   124,   9};
        long[][] weights = { //v     0        1        2        3       //u
                               {     0,  200000,  300000,  200000},     //0 
                               {200000,       0,  500000,  200000},     //1
                               {500000,  200000,       0,  500000},     //2
                               {300000,  300000,  200000,       0}      //3
                            };
        int testStart = 1;
        main.ISSSP(testStart, testDist, testPred);
        */
        Main main = new Main();

        //TODO
        int absV = -1;
        int numConnections = -1;
        int start = -1;
        int end = -1;

        long[][] weights = main.createGraph(absV, numConnections);
        long[] dist = new long[absV];
        int[] pred = new int[absV];
        main.dijkstrasMod(start, dist, pred, weights, end, absV);

        //get path 
        int current = end;
        ArrayList<String> tempList = new ArrayList<>();
        while (current != start) {
            tempList.add(main.translate(weights[pred[current]][current]));
            current = pred[current];
        }

        Collections.reverse(tempList);

        System.out.print(tempList.size() + " ");
        for (String str : tempList) {
            System.out.print(str + " ");
        }
    }

    public long[][] createGraph(int absV, int numConnections) {
        //TODO
        return new long[][] {{}};
    }

    public String translate(long weight) {
        if (weight == 200000) {
            return "right";
        } else if (weight == 300000) {
            return "straight";
        } else if (weight == 500000) {
            return "left";
        }
        return "";
    }
    public void dijkstrasMod(int start, long[] dist, int[] pred, long[][] w, int t, int absV) {
        ISSSP(start, dist, pred);

        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add((long)start);

        while (!pq.isEmpty()) {
            long z = pq.poll();
            if (z%200000 < 100000) {
                long temp = z%200000;                
                for (int i = 0; i < absV; i++) {                    
                    if (w[(int)temp][(int)i] > 0) {
                        if (dist[(int)i] > dist[(int)temp] + w[(int)temp][(int)i]) { 
                            long oldDist = dist[(int)i];
                            dist[(int)i] = dist[(int)temp] + w[(int)temp][(int)i];
                            pred[(int)i] = (int)temp;

                            if (pq.contains(i + oldDist)) {
                                pq.remove(i + oldDist);
                                pq.add(i + dist[(int)i]);
                            } else {
                                pq.add(i + dist[(int)i]);
                            }
                        }
                    }
                }
            } else if (z%300000 < 100000) {
                long temp = z%300000;                
                for (int i = 0; i < absV; i++) {                    
                    if (w[(int)temp][(int)i] > 0) {
                        if (dist[(int)i] > dist[(int)temp] + w[(int)temp][(int)i]) { 
                            long oldDist = dist[(int)i];
                            dist[(int)i] = dist[(int)temp] + w[(int)temp][(int)i];
                            pred[(int)i] = (int)temp;

                            if (pq.contains(i + oldDist)) {
                                pq.remove(i + oldDist);
                                pq.add(i + dist[(int)i]);
                            } else {
                                pq.add(i + dist[(int)i]);
                            }
                        }
                    }
                }
            } else if (z%500000 < 100000) {
                long temp = z%500000;                
                for (int i = 0; i < absV; i++) {                    
                    if (w[(int)temp][(int)i] > 0) {
                        if (dist[(int)i] > dist[(int)temp] + w[(int)temp][(int)i]) { 
                            long oldDist = dist[(int)i];
                            dist[(int)i] = dist[(int)temp] + w[(int)temp][(int)i];
                            pred[(int)i] = (int)temp;

                            if (pq.contains(i + oldDist)) {
                                pq.remove(i + oldDist);
                                pq.add(i + dist[(int)i]);
                            } else {
                                pq.add(i + dist[(int)i]);
                            }
                        }
                    }
                }
            }

        }
    }

    public void ISSSP(int start, long[] dist, int[] pred) {
        dist[start] = 0;
        pred[start] = -1;

        for (int i = 0; i < dist.length; i++) {
            if (i != start) {
                dist[i] = Long.MAX_VALUE;
                pred[i] = -1;
            } 
        }   
    }
}