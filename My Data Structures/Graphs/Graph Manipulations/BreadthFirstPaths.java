
public class BreadthFirstPaths {
	private int[] edgeTo;
	private boolean[] marked;
	
	public BreadthFirstPaths(MyDiGraph G, int s) {
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		bfs(G, s);
	}
	
	private void bfs(MyDiGraph G, int s) {
		MyQueue<Integer> q = new MyQueue<Integer>();
		q.enqueue(s);
		this.marked[s] = true;
		
		while(!q.isEmpty()) {
			int v = q.dequeue();
			for (int i : G.adj(v)) {
				if (!marked[i]) {
					this.edgeTo[i] = v;
					this.marked[i] = true;
					q.enqueue(i);
				} else {
					continue;
				}
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
		MyDiGraph g = new MyDiGraph(5);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(1, 0);
		g.addEdge(3, 3);
		g.addEdge(4, 2);
		g.addEdge(4, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		BreadthFirstPaths bfp = new BreadthFirstPaths(g, 1);
		for (int i = 0; i < g.V(); i++) {
			System.out.println(i + " -> " + bfp.marked(i));
		}
	}
}
