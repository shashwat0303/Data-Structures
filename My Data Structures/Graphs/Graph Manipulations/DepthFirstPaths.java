
public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	
	public DepthFirstPaths(MyDiGraph G, int S) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		dfs(G, S);
	}
	
	private void dfs(MyDiGraph G, int S) {
		marked[S] = true;
		for (int i : G.adj(S)) {
			if (!marked[i]) {
				dfs(G, i);
				edgeTo[i] = S;
			} else {
				continue;
			}
		}
	}
	
	public boolean marked(int w) {
		return marked[w];
	}
	
	public int edgeTo(int w) {
		return this.edgeTo[w];
	}
	
	public static void main(String[] args) {
		MyDiGraph g = new MyDiGraph(13);
		g.addEdge(0, 5);
		g.addEdge(4, 3);
		g.addEdge(0, 1);
		g.addEdge(9, 12);
		g.addEdge(6, 4);
		g.addEdge(5, 4);
		g.addEdge(0, 2);
		g.addEdge(11, 12);
		g.addEdge(9, 10);
		g.addEdge(0, 6);
		g.addEdge(7, 8);
		g.addEdge(9, 11);
		g.addEdge(5, 3);
		System.out.println(g.toString());
		DepthFirstPaths dfp = new DepthFirstPaths(g, 6);
		for (int i = 0; i < g.V(); i++) {
			System.out.println(i + " -> " + dfp.marked(i));
		}
	}
}
