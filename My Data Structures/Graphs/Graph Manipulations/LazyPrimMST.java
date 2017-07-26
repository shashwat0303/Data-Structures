import java.util.PriorityQueue;

public class LazyPrimMST {
	private boolean[] marked;
	private MyQueue<Edge> mst;
	private PriorityQueue<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		marked = new boolean[G.V()];
		mst = new MyQueue<Edge>();
		pq = new PriorityQueue<Edge>();
		visit(G, 0);
		
		while(!pq.isEmpty()) {
			Edge e = pq.remove();
			int v = e.either();
			int w = e.other(v);
			if (marked[v] && marked[w]) {
				continue;
			}
			mst.enqueue(e);
			if (!marked[v]) {
				visit(G, v);
			}
			if (!marked[w]) {
				visit(G, w);
			}
		}
	}
	
	public void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj[v]) {
			if (!marked[e.other(v)]) {
				pq.add(e);
			}
		}
	}
	
	public static void main(String[] args) {
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(4);
		Edge e1 = new Edge(0, 1, 0.16);
		Edge e2 = new Edge(1, 2, 0.11);
		Edge e3 = new Edge(0, 3, 0.14);
		Edge e4 = new Edge(2, 3, 0.15);
		ewg.addEdge(e1);
		ewg.addEdge(e2);
		ewg.addEdge(e3);
		ewg.addEdge(e4);
		System.out.println(e2.toString());
		LazyPrimMST mst = new LazyPrimMST(ewg);
		while (!mst.mst.isEmpty()) {
			Edge e = mst.mst.dequeue();
			System.out.println(e.toString());
		}
	}
}
