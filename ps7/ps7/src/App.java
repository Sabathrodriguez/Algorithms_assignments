import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

		//get input
		Scanner scanner = new Scanner(System.in);
		int seed = scanner.nextInt();
		int vertexCount = scanner.nextInt();
		int minWeight = scanner.nextInt();
        int maxWeight = scanner.nextInt();
		int connectivity = scanner.nextInt();
		String algorithm = scanner.next().trim();
		int startVertex = 0;
		if (algorithm.equals("Jarnik")) {
			System.out.println("hi");
			startVertex = Integer.parseInt(scanner.nextLine().trim());
		} 

		scanner.close();

		int[][] Graph = generateWeights(seed, vertexCount, minWeight, maxWeight, connectivity);

		// printWeights(Graph, 4, false);

		getMST(algorithm, startVertex, Graph);

    }

    public static List<Edge> getMST(String algorithm, int startVertex, int[][] Graph) {
		List<Edge> mst = new ArrayList<>();
		boolean[] marked = new boolean[Graph.length];
		int netWeight = 0;

		if (algorithm.equals("Jarnik")) {
			//Jarniks Algorithm
			netWeight = jarniksAlgo(marked, startVertex, Graph, mst);
		} else if (algorithm.equals("Kruskal")) {
			
		} else if (algorithm.equals("Boruvka")) {
			//Boruvkas Algorithm
			netWeight = boruvkasAlgo(mst, Graph.length, Graph);
		}
		System.out.println(netWeight);
		System.out.println(mst.size());
		for (Edge e : mst) {
			System.out.println(e);
		}
        return mst;
    }

	public static int boruvkasAlgo(List<Edge> mst, int vertexCount, int[][] Graph) {
		int netWight = 0;
		List<Edge> E = new ArrayList<>();
		int count = countAndLabel(mst, vertexCount, Graph, E);
		System.out.println("count: " + count);

		while (count > 1) {
			addAllSafeEdges(E, mst, count);
			count = countAndLabel(mst, vertexCount, Graph, E);
		}

		return netWight;
	}

	public static void addAllSafeEdges(List<Edge> E, List<Edge> F, int count) {
		Edge[] safe = new Edge[count];

		for (Edge e : E) {
			if (e.getStartComp() != e.getEndComp()) {
				if (safe[e.getStartComp()] == null || e.getWeight() < safe[e.getStartComp()].getWeight()) {
					safe[e.getStartComp()] = e;
				}
				if (safe[e.getEndComp()] == null || e.getWeight() < safe[e.getEndComp()].getWeight()) {
					safe[e.getEndComp()] = e;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			if (safe[i] != null) {
				F.add(safe[i]);
			}
		}
	}

	public static int countAndLabel(List<Edge> F, int vertexCount, int[][] Graph, List<Edge> E) {
		int count = 0;
		boolean[] marked = new boolean[vertexCount];

		for (int i = 0; i < vertexCount; i++) {
			//i is the vertex
			
			//is vertex i marked?
			if (!marked[i]) {
				//if it's not marked, call labelOne function
				count += 1;
				labelOne(i, count, E, marked, Graph);
			}
		}

		return count;
	}

	//labelOne function finds all vertices in that component
	public static void labelOne(int i, int count, List<Edge> E, boolean[] marked, int[][] Graph) {
		Stack<Integer> stack = new Stack<>();
		stack.push(i);

		while (!stack.isEmpty()) {
			int v = stack.pop();

			if (!marked[i]) {
				marked[i] = true;

				Edge newEdge = new Edge(i, v, Graph[i][v]);
				
				newEdge.setEndComp(count);
				newEdge.setStartComp(count);

				E.add(newEdge);

				for (int e = 0; e < Graph[i].length; e++) {
					if (Graph[i][e] > 0) {
						if (i != e) {
							stack.push(e);
						}
					}
				}
			}
		}
		for (Edge e : E)
			System.out.println("start endpoint comp: " + e.getStartComp() + ", end endpoint comp: " + e.getEndComp());
	}

	public static int jarniksAlgo(boolean[] marked, int startVertex, int[][] Graph, List<Edge> mst) {
		int vCount = 0;
		int netWeight = 0;

		for (int i = 0; i < marked.length; i++) {
			marked[i] = false;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		marked[startVertex] = true;
		for (int i = 0; i < Graph.length; i++) {
			if (i != startVertex) {
				if (Graph[startVertex][i] > 0) {
					Edge newEdge = new Edge(startVertex, i, Graph[startVertex][i]);
					pq.add(newEdge);
				}
			}
		}

		while (vCount < marked.length && !pq.isEmpty()) {
			Edge eFromPQ = pq.remove();

			if (! marked[eFromPQ.getStart()]) {
				marked[eFromPQ.getStart()] = true;
				vCount++;
				for (int j = 0; j < Graph.length; j++) {
					if (Graph[eFromPQ.getStart()][j] > 0) {
						if (j != eFromPQ.getStart()) {
							Edge newEdgeFrom = new Edge(eFromPQ.getStart(), j, Graph[eFromPQ.getStart()][j]);
							Edge newEdgeTo = new Edge(j, eFromPQ.getStart(),Graph[eFromPQ.getStart()][j]);

							pq.add(newEdgeFrom);
							pq.add(newEdgeTo);
						}
					}
				}
				netWeight += eFromPQ.getWeight();
				mst.add(eFromPQ);
			}
			if (! marked[eFromPQ.getEnd()]) {
				marked[eFromPQ.getEnd()] = true;
				vCount++;
				for (int j = 0; j < Graph.length; j++) {
					if (Graph[eFromPQ.getEnd()][j] > 0) {
						if (j != eFromPQ.getEnd()) {
							Edge newEdgeFrom = new Edge(eFromPQ.getEnd(), j, Graph[eFromPQ.getEnd()][j]);
							Edge newEdgeTo = new Edge(j, eFromPQ.getEnd(),Graph[eFromPQ.getEnd()][j]);

							pq.add(newEdgeFrom);
							pq.add(newEdgeTo);
						}
					}
				}
				netWeight += eFromPQ.getWeight();
				mst.add(eFromPQ);
			}
		}
		return netWeight;
	}

    /**
	 * Prints out an adjacency matrix.  Note that axis are labeled 'from' and 'to',
	 * but for undirected graphs this is irrelevant.
	 * 
	 * @param weights  an int[][] of weights
	 * @param spacing  how many spaces to print for each column
	 * @param showSymmetry graph is undirected -- should the entire matrix be shown?  (Go Neo, Go!)
	 */
	public static void printWeights (int[][] weights, int spacing, boolean showSymmetry)
	{		
		for (int to = -3; to < weights.length; to++)
		{
			if (to >= 0)
			{
				System.out.printf(to == weights.length/2 ? "to" : "  ");
				System.out.printf("%"+spacing+"d|", to);
			}
			else
				System.out.printf("   %"+spacing+"s", "");
			
			for (int from = 0; from < weights.length; from++)
				if (to >= 0 && (from < to || showSymmetry))
					System.out.printf("%"+spacing+"d", weights[from][to]);
				else if (to == -3)
					System.out.printf(from == weights.length/2 ? "from %s" : "%"+spacing+"s", "");
				else if (to == -2)
					System.out.printf("%"+spacing+"d", from);
				else if (to == -1)
					System.out.print("-----------".substring(0,spacing));
						
			System.out.println();
		}			
	}
	
	
	/**
	 * Generates a connected, undirected, weighted graph.  Note that
	 * the result is a symmetric adjacency matrix where each value
	 * represents an edge weight.  (An edge weight of 0 represents
	 * a non-edge.)
	 * 
	 * Note that the arrays are zero-based, so vertices are numbered
	 * [0..vertexCount).  
	 * 
	 * The connectivity parameter specifies how many times a random
	 * spanning tree should be added to the graph.  Note that a 
	 * value greater than 1 will probably result in a cycle, but it
	 * is not guaranteed (especially for tiny graphs)
	 * 
	 * For language independence, the random number generation is
	 * done using a linear feedback shift register with a cycle
	 * length of 2^31-1.  (Bits 27 and 30 are xor'd and fed back.)
	 * (Note:  2^31-1 is prime which is useful when generating
	 * pairs, triples, or other multi-valued sequences.  The
	 * pattern won't repeat until after 2^31-1 pairs, triples, etc.
	 * are generated.)
	 * 
	 * Finally, the runtime of this generation is O(v) in connectivity,
	 * or k*v*connectivity.
	 * 
	 * @param seed  any positive int
	 * @param vertexCount any int greater than 1  
	 * @param minWeight   any positive int
	 * @param maxWeight   any int greater than minWeight
	 * @param connectivity the overall connectedness of the graph, min 1
	 * @return the weighted adjacency matrix for the graph
	 */
	public static int[][] generateWeights (int seed, int vertexCount, int minWeight, int maxWeight, int connectivity)  // Non-zero seed, cap vertices at 100, weights at 10000 
	{
		int[][] weights = new int[vertexCount][vertexCount];
		
		for (int pass = 0; pass < connectivity; pass++)
		{
			List<Integer> connected = new ArrayList<Integer>();
			List<Integer> unused    = new ArrayList<Integer>();
			
			connected.add(0);
			for (int vertex = 1; vertex < vertexCount; vertex++)
				unused.add(vertex);
			
			while (unused.size() > 0)
			{
				seed = (((seed ^ (seed >> 3)) >> 12) & 0xffff) | ((seed & 0x7fff) << 16);
				int weight = seed % (maxWeight-minWeight+1) + minWeight;
				
				seed = (((seed ^ (seed >> 3)) >> 12) & 0xffff) | ((seed & 0x7fff) << 16);
				Integer fromVertex = connected.get(seed % connected.size());
				
				seed = (((seed ^ (seed >> 3)) >> 12) & 0xffff) | ((seed & 0x7fff) << 16);
				Integer toVertex   = unused.get(seed % unused.size());
				
				weights[fromVertex][toVertex] = weight;
				weights[toVertex][fromVertex] = weight;  // Undirected
				
				connected.add(toVertex);
				unused.remove(toVertex);  // Note -- overloaded, remove element Integer, not position int
			}			
		}
		
		return weights;
	}
}

class Edge implements Comparable<Edge> {
	private int start;
	private int end;
	private int weight;
	private int startComp;
	private int endComp;

	public Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public int getStartComp() {
		return this.startComp;
	}

	public int getEndComp() {
		return this.endComp;
	}

	public void setEndComp(int i) {
		this.endComp = i;
	}

	public void setStartComp(int i) {
		this.startComp = i;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	public int getWeight() {
		return this.weight;
	}

	@Override
	public int compareTo(Edge e) {

		if (e.getWeight() < this.getWeight()) {
			return 1;
		} else if (e.getWeight() > this.getWeight()) {
			return -1;
		} else if (Math.min(this.getEnd(), this.getStart()) > (Math.min(e.getEnd(), e.getStart()))) {
			return 1;
		} else if (Math.min(this.getEnd(), this.getStart()) < (Math.min(e.getEnd(), e.getStart()))) {
			return -1;
		} else if (Math.max(this.getEnd(), this.getStart()) > (Math.max(e.getEnd(), e.getStart()))) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object o1) {
		return this.compareTo((Edge) o1) == 0;
	}

	@Override
	public String toString() {
		if (this.getEnd() > this.getStart()) {
			return this.getStart() + " " + this.getEnd();
		} else {
			return this.getEnd() + " " + this.getStart();
		}
		
	}

}
