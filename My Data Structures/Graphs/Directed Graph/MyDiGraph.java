/**
 * 
 * @author Shashwat Koranne
 * 
 * Following class is my implementation of Graph using the MyLinkedList object.
 * Adjacency list in this case is constructed using the array of MyLinkedList
 * objects.
 *
 */
public class MyDiGraph {
	/**
	 * Number of vertices of graph.
	 */
	private final int V;
	/**
	 * numOfEdges to keep the track of number of edges in the graph.
	 */
	private int numOfEdges = 0;
	/**
	 * Bag objects to keep the track of adjacent vertices.
	 */
	private Bag<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public MyDiGraph(int V) {
		this.V = V;
		adj = new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	
	public int V() {
		return this.V;
	}
	
	public int E() {
		return this.numOfEdges;
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		numOfEdges++;
	}
	
	public Iterable<Integer> adj(int V) {
		return adj[V];
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < this.V; i++) {
			str.append(Integer.toString(i) + " -> ");
			for (int j : adj[i]) {
				str.append(Integer.toString(j) + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
	public static int degree(MyDiGraph G, int V) {
		int degree = 0;
		for (int i : G.adj(V)) {
			degree++;
		}
		return degree;
	}
	
	public static int maxDegree(MyDiGraph G) {
		int maxDeg = 0;
		for (int i = 0; i < G.V(); i++) {
			if (degree(G, i) > maxDeg) {
				maxDeg = degree(G, i);
			} else {
				continue;
			}
		}
		return maxDeg;
	}
	
	public static int avgDegree(MyDiGraph G) {
		return 2 * G.E() / G.V();
	}
	
	public static int numOfSelfLoops(MyDiGraph G) {
		int numOfLoops = 0;
		for (int i = 0; i < G.V(); i++) {
			for (int j : G.adj(i)) {
				if (j == i) {
					numOfLoops++;
				} else {
					continue;
				}
			}
		}
		return numOfLoops;
	}
	
	public static void main(String[] args) {
		MyDiGraph g = new MyDiGraph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(3, 1);
		g.addEdge(3, 0);
		g.addEdge(0, 0);
		g.addEdge(1, 1);
		System.out.println(g.toString());
		System.out.println(numOfSelfLoops(g));
	}
}
