import java.util.*;

class topologicalSort {
    public static void main(String[] args) {        
        Map<String, Vertex> G = new HashMap<String, Vertex>();
        
//        try {
            List<Vertex> vertexList = createVertexList();
        
            
            for (Vertex v : vertexList) {
                G.put(v.getName(), v);
            }
            
            String[] arr = topologicalSort(G);
            
            for (Vertex v : G.values()) {
                if (!v.getStatus().equals("finished")) {
                    System.out.println("Unsolvable");
                    return;
                }
            }
            for (String s : arr) {
                System.out.println(s);
            }
            
//        }
//        catch (NullPointerException e) {
//            System.out.println("Unsolvable");
//        }
    }
    
    public static List<Vertex> createVertexList() {
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
        
        ArrayList<ArrayList<String>> g = new ArrayList<>();
        ArrayList<ArrayList<String>> g1 = new ArrayList<>();
        
        Map<String, String> player1 = new HashMap<>();
        Map<String, String> player2 = new HashMap<>();
        
        HashSet<String> sharedQuests = new HashSet<>();
        
        HashSet<String> vertexList = new HashSet<>();
        
        int num = scanner.nextInt();
        
        while (num-- > 0) {
            String u = scanner.next().trim();
            String v = scanner.next().trim();
            
            g.add(new ArrayList<>(Arrays.asList(u, v)));
            
            if (!player1.containsKey(u)) {
                player1.put(u, u);
            }
            if (!player1.containsKey(v)) {
                player1.put(v, v);
            }
        }
        
        num = scanner.nextInt();
        
        while (num-- > 0) {
            String u = scanner.next().trim();
            String v = scanner.next().trim();
            
            g1.add(new ArrayList<>(Arrays.asList(u, v)));

            if (!player2.containsKey(u)) {
                player2.put(u, u);
            }
            if (!player2.containsKey(v)) {
                player2.put(v, v);
            }
        }
        
        if (scanner.hasNextInt()) {
            num = scanner.nextInt();
            while (num-- > 0) {
                sharedQuests.add(scanner.next().trim());
            }
        }
        
        for (ArrayList<String> h : g) {
            if (! sharedQuests.contains(h.get(0))) {
                String str = player1.get(h.get(0));
                if (!str.contains("-1"))
                    player1.replace(h.get(0), str + "-1");
            }
            if (! sharedQuests.contains(h.get(1))) {
                String str = player1.get(h.get(1));
                if (!str.contains("-1"))
                    player1.replace(h.get(1), str + "-1");
            }
        }
        
        for (ArrayList<String> h : g1) {
            if (! sharedQuests.contains(h.get(0))) {
                String str = player2.get(h.get(0));
                if (!str.contains("-2"))
                    player2.replace(h.get(0), str + "-2");
            }
            if (! sharedQuests.contains(h.get(1))) {
                String str = player2.get(h.get(1));
                if (!str.contains("-2"))
                    player2.replace(h.get(1), str + "-2");
            }
        }
        
        for (String str : player1.values()) {
            vertexList.add(str);
        }
            
        for (String str : player2.values()) {
            vertexList.add(str);
        }
                
        TreeMap<String, Vertex> vertexList1 = new TreeMap<>();
        for (String str : player1.values()) {
            Vertex vertex = new Vertex(str);
            vertexList1.put(str, vertex);
        }
        
        TreeMap<String, Vertex> vertexList2 = new TreeMap<>();
        for (String str : player2.values()) {
            Vertex vertex = new Vertex(str);
            vertexList2.put(str, vertex);
        }
        
        TreeMap<String, Vertex> sharedVertices = new TreeMap<>();
        for (String h : sharedQuests) {
            Vertex vertex = new Vertex(h);
            sharedVertices.put(h, vertex);
        }
        
        for (ArrayList<String> s : g) {
            if (vertexList1.containsKey(s.get(0)+"-1") && vertexList1.containsKey(s.get(1)+"-1")) {
                
                vertexList1.get(s.get(0)+"-1")
                .addToList(vertexList1.get(s.get(1)+"-1"));
                
                vertexList1.get(s.get(1)+"-1")
                .addFromList(vertexList1.get(s.get(0)+"-1"));
            }
        }
        
        for (ArrayList<String> s : g1) {
            if (vertexList2.containsKey(s.get(0)+"-2") && vertexList2.containsKey(s.get(1)+"-2")) {
                
                vertexList2.get(s.get(0)+"-2")
                .addToList(vertexList2.get(s.get(1)+"-2"));
                
                vertexList2.get(s.get(1)+"-2")
                .addFromList(vertexList2.get(s.get(0)+"-2"));
            }
        }
        
        for (ArrayList<String> lst : g) {
            if (sharedVertices.containsKey(lst.get(0))) {
                sharedVertices.get(lst.get(0))
                .addToList(vertexList1.get(lst.get(1)+"-1"));
                
                if (vertexList1.containsKey(lst.get(0)+"-1")) {
                    vertexList1.get(lst.get(1)+"-1")
                   .addFromList(sharedVertices.get(lst.get(0)));
               }
                    
            }
            if (sharedVertices.containsKey(lst.get(1))) {
                sharedVertices.get(lst.get(1))
                .addFromList(vertexList1.get(lst.get(0)+"-1"));
                    
                if (vertexList1.containsKey(lst.get(1)+"-1")) {
                    vertexList1.get(lst.get(0)+"-1")
                    .addToList(sharedVertices.get(lst.get(1)));
                }
            }
        }
        
        for (ArrayList<String> lst : g1) {
            if (sharedVertices.containsKey(lst.get(0))) {
            
                sharedVertices.get(lst.get(0))
                .addToList(vertexList2.get(lst.get(1)+"-2"));
                    
                if (vertexList2.containsKey(lst.get(0)+"-2")) {
                    vertexList2.get(lst.get(1)+"-2")
                    .addFromList(sharedVertices.get(lst.get(0)));
                }
            }
            if (sharedVertices.containsKey(lst.get(1))) {
                sharedVertices.get(lst.get(1))
                .addFromList(vertexList2.get(lst.get(0)+"-2"));
                    
                    
                    if (vertexList2.containsKey(lst.get(1)+"-2")) {
                    vertexList2.get(lst.get(0)+"-2")
                    .addToList(sharedVertices.get(lst.get(1)));
                }
            }
        }
        
        ArrayList<Vertex> finalArray = new ArrayList<>();
        for (Vertex v : vertexList1.values()) {
            finalArray.add(v);
        }
        for (Vertex v : vertexList2.values()) {
            finalArray.add(v);
        }
        for (Vertex v : sharedVertices.values()) {
            finalArray.add(v);
        }

        return finalArray;
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

class Vertex implements Comparable<Vertex> {
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
    public void addToList(Set<Vertex> vertex) {
        this.to.addAll(vertex);
    }
    public void addFromList(Vertex vertex) {
        this.from.add(vertex);
    }
    public void addFromList(List<Vertex> vertex) {
        this.from.addAll(vertex);
    }
    public void addFromList(Set<Vertex> vertex) {
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
        return this.name;
    }
    
    @Override
    public int compareTo(Vertex v) {
        if (this.to.size() > v.getFrom().size()) {
            return 1;
        } else if (this.to.size() == v.getFrom().size()) {
            return 0;
        } else {
            return -1;
        }
    }
}

class VertexFromSizeComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex o1, Vertex o2) {
        return o2.getTo().size() - o1.getTo().size();
    }

}
