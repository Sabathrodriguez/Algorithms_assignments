import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        //testing generate weights function
        for (int[] A : generateWeights(1, 10, 1, 10, 1)) {
            System.out.println(Arrays.toString(A));
        }
        //PASSED TEST: Works as expected, will return 2D array representing weighted undirected Graph


        
    }

    public static int[][] getMST(String algorithm, int startVertex, int[][] Graph) {
        //TODO: fill in
        return new int[1][1];
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
