import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

class topologicalSort {
    public static void main(String[] args) {        
        Map<String, Vertex> G = new HashMap<String, Vertex>();
        
        List<Vertex> vertexList = createVertexList();
        
        for (Vertex v : vertexList) {
            G.put(v.getName(), v);
        }
                        
//        System.out.println(Arrays.toString(topologicalSort(G)));
    }
    
    public static List<Vertex> createVertexList() {
        List<Vertex> vertexList = new ArrayList<>();
        /*
         4
         Castle Sweetroll
         Sweetroll Guard
         Castle Dragon
         Dragon Treasure
         3
         Guard Castle
         Guard Dragon
         Dragon Romance
         1
         Dragon
         */
        Scanner scanner = new Scanner(System.in);
        //  u     , v
        Map<String, ArrayList<String>> player1 = new TreeMap<>();
        Map<String, ArrayList<String>> player2 = new TreeMap<>();
        ArrayList<String> sharedQuests = new ArrayList<>();
        
        int numVertices = scanner.nextInt();
        
        //player 1
        while (numVertices-- > 0) {
            String k = scanner.next().trim() + "-1";
            String v = scanner.next().trim() + "-1";
            
            Vertex a = new Vertex(k);
            Vertex b = new Vertex(v);
            
            if (getVertex(vertexList, a.getName()) != null) {
                getVertex(vertexList, a.getName()).addToList(b);
            } else {
                vertexList.add(a);
                a.addToList(b);
            }
            if (getVertex(vertexList, b.getName()) != null) {
                getVertex(vertexList, b.getName()).addFromList(a);
            } else {
                vertexList.add(b);
                b.addFromList(a);
            }
        }
        numVertices = scanner.nextInt();
        
        //player 2
        while (numVertices-- > 0) {
            String k = scanner.next().trim() + "-2";
            String v = scanner.next().trim() + "-2";
            
            Vertex a = new Vertex(k);
            Vertex b = new Vertex(v);
            
            if (getVertex(vertexList, a.getName()) != null) {
                getVertex(vertexList, a.getName()).addToList(b);
            } else {
                vertexList.add(a);
                a.addToList(b);
            }
            if (getVertex(vertexList, b.getName()) != null) {
                getVertex(vertexList, b.getName()).addFromList(a);
            } else {
                vertexList.add(b);
                b.addFromList(a);
            }
        }
        
        if (scanner.hasNextInt()) {
            int numSharedVertices = scanner.nextInt();
            
            while (numSharedVertices-- > 0) {
                String shared = scanner.next().trim();
                sharedQuests.add(shared);
            }
        }
        
        //link shared quests
        for (int i = 0; i < sharedQuests.size(); i++) {
            
        }
        
        for (Vertex v : vertexList) {
            System.out.println(v + "->" + v.getTo());
            System.out.println(v + "<-" + v.getFrom());
        }
        
        //player 1 events
//        Vertex castle_1 = new Vertex("Castle-1");
//        Vertex sweetroll_1 = new Vertex("Sweetroll-1");
//        Vertex guard_1 = new Vertex("Guard-1");
//        Vertex treasure_1 = new Vertex("Treasure-1");
//
//        //shared events
//        Vertex dragon = new Vertex("Dragon");
//
//        //player 2 events
//        Vertex guard_2 = new Vertex("Guard-2");
//        Vertex romance_2 = new Vertex("Romance-2");
//        Vertex castle_2 = new Vertex("Castle-2");
//
//        //add vertices to vertexList
//        vertexList.add(castle_1);
//        vertexList.add(sweetroll_1);
//        vertexList.add(guard_1);
//        vertexList.add(treasure_1);
//
//        vertexList.add(dragon);
//
//        vertexList.add(guard_2);
//        vertexList.add(romance_2);
//        vertexList.add(castle_2);
//
//        //player 1 u->v
//        castle_1.addToList(sweetroll_1);
//        castle_1.addToList(dragon);
//
//        sweetroll_1.addToList(guard_1);
//        sweetroll_1.addFromList(castle_1);
//
//        guard_1.addFromList(sweetroll_1);
//
//        dragon.addToList(treasure_1);
//        dragon.addFromList(castle_1);
//
//        treasure_1.addFromList(dragon);
//
//        //player 2 u->v
//        guard_2.addToList(castle_1);
//        guard_2.addToList(dragon);
//
//        castle_2.addFromList(guard_2);
//
//        dragon.addToList(romance_2);
//        dragon.addFromList(guard_2);
//
//        romance_2.addFromList(dragon);
        
        Collections.sort(vertexList, new VertexFromSizeComparator());
                
        return vertexList;
    }
    
    public static String[] topologicalSort(Map<String, Vertex> G) {
        int clock = G.size()-1;
        
        String[] arr = new String[G.size()];
    
        for (Vertex v : G.values()) {
            v.setStatus("new");
        }
        for (Vertex v : G.values()) {
            if (v.getStatus().equals("new")) {
                clock = topSortDFS(v, clock, arr);
            }
        }
        
        return arr;
    }
    
    public static int topSortDFS(Vertex v, int clock, String[] arr) {
        v.setStatus("active");
        
        for (Vertex w : v.getTo()) {
            if (w.getStatus().equals("new")) {
                clock = topSortDFS(w, clock, arr);
            } else if (w.getStatus().equals("active")) {
                System.out.println("fail gracefully");
                break;
            }
        }
        v.setStatus("finished");
        arr[clock] = v.getName();
        clock -= 1;
        return clock;
    }
    
    public static boolean containsVertex(List<Vertex> list, Vertex vertex) {
        for (Vertex v : list) {
            if (v.getName().equals(vertex.getName())) {
                    return true;
            }
        }
        return false;
    }
    
    public static Vertex getVertex(List<Vertex> list, String name) {
        for (Vertex v : list) {
            if (v.getName().equals(name)) {
                    return v;
            }
        }
        return null;
    }
}

class Vertex {
    private String name;
    private List<Vertex> to;
    private List<Vertex> from;
    private int pre;
    private int post;
    private boolean visited;
    private String status;
    
    public Vertex() {;}
    
    public Vertex(String name) {
        this.name = name;
        this.to = new ArrayList<Vertex>();
        this.from = new ArrayList<Vertex>();
        this.pre = 0;
        this.post = 0;
        this.visited = false;
        this.status = "new";
    }
    
    public List<Vertex> getTo() {
        return this.to;
    }
    public List<Vertex> getFrom() {
        return this.from;
    }
    public String getName() {
        return this.name;
    }
    public int getPre() {
        return this.pre;
    }
    public int getPost() {
        return this.post;
    }
    public boolean getVisited() {
        return this.visited;
    }
    public String getStatus() {
        return this.status;
    }
    public void addToList(Vertex vertex) {
        this.to.add(vertex);
    }
    public void addToList(List<Vertex> vertex) {
        this.to.addAll(vertex);
    }
    public void addFromList(Vertex vertex) {
        this.from.add(vertex);
    }
    public void addFromList(List<Vertex> vertex) {
        this.from.addAll(vertex);
    }
    public void setPre(int x) {
        this.pre = x;
    }
    public void setPost(int x) {
        this.post = x;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name ;
    }
}

class VertexFromSizeComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex o1, Vertex o2) {
        return o2.getTo().size() - o1.getTo().size();
    }

}
