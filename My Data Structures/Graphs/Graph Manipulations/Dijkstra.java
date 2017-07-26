public class Dijkstra {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	public Dijkstra(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
		for (int i = 0; i < G.V(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		pq.insert(s, 0.0);

		while(!pq.isEmpty()) {
			int v = pq.delMin();
			for (DirectedEdge e : G.adj[v]) {
				relax(e);
			}
		}
	}

	private void relax(DirectedEdge e) {
		int v = e.from();
		int w = e.to(v);
		if (distTo[w] > distTo[v] + e.weight) {
			distTo[w] = distTo[v] + e.weight;
			edgeTo[w] = e;
			if (pq.contains(w)) {
				pq.decreaseKey(w, distTo[w]);
			} else {
				pq.insert(w, distTo[w]);
			}
		}
	}
}
