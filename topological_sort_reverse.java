import java.util.*;

class topological_sort_reverse {
    public static void main(String[] args) {
        List<Vertex> vertexList = new ArrayList<>();
        //player 1 events
        Vertex castle_1 = new Vertex("Castle-1");
        Vertex sweetroll_1 = new Vertex("Sweetroll-1");
        Vertex guard_1 = new Vertex("Guard-1");
        Vertex treasure_1 = new Vertex("Treasure-1");

        //shared events
        Vertex dragon = new Vertex("Dragon");

        //player 2 events
        Vertex guard_2 = new Vertex("Guard-2");
        Vertex romance_2 = new Vertex("Romance-2");
        Vertex castle_2 = new Vertex("Castle-2");

        //add vertices to vertexList
        vertexList.add(castle_1);
        vertexList.add(sweetroll_1);
        vertexList.add(guard_1);
        vertexList.add(treasure_1);

        vertexList.add(dragon);

        vertexList.add(guard_2);
        vertexList.add(romance_2);
        vertexList.add(castle_2);

        //player 1 u->v
        castle_1.addToList(sweetroll_1);
        castle_1.addToList(dragon);

        sweetroll_1.addToList(guard_1);
        sweetroll_1.addFromList(castle_1);

        guard_1.addFromList(sweetroll_1);

        dragon.addToList(treasure_1);
        dragon.addFromList(castle_1);

        treasure_1.addFromList(dragon);

        //player 2 u->v
        guard_2.addToList(castle_1);
        guard_2.addToList(dragon);

        castle_2.addFromList(guard_2);

        dragon.addToList(romance_2);
        dragon.addFromList(guard_2);

        romance_2.addFromList(dragon);
        
        Map<String, Vertex> G = new HashMap<String, Vertex>();
        
        for (Vertex v : vertexList) {
            G.put(v.getName(), v);
        }
                        
        System.out.println(Arrays.toString(topologicalSort(G)));
        
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
    public void addFromList(Vertex vertex) {
        this.from.add(vertex);
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
    
    @Override
    public String toString() {
        return this.name;
    }
}
class VertexFromSizeComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex o1, Vertex o2) {
        return o2.getTo().size() - o1.getTo().size();
    }

}
