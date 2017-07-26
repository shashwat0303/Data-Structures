
public class EdgeWeightedDigraph {
	int v;
	Bag<DirectedEdge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V) {
		this.v = V;
		adj = new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<DirectedEdge>();
		}
	}

	public void addEdge(DirectedEdge e) {
		int v = e.from();
		int w = e.to(v);
		adj[v].add(e);
		adj[w].add(e);
	}

	public int V() {
		return this.v;
	}

	public static void main(String[] args) {
		EdgeWeightedDigraph ewg = new EdgeWeightedDigraph(4);
		DirectedEdge e1 = new DirectedEdge(0, 1, 0.16);
		DirectedEdge e2 = new DirectedEdge(1, 2, 0.11);
		DirectedEdge e3 = new DirectedEdge(0, 3, 0.14);
		DirectedEdge e4 = new DirectedEdge(2, 3, 0.15);
		ewg.addEdge(e1);
		ewg.addEdge(e2);
		ewg.addEdge(e3);
		ewg.addEdge(e4);
		for (int i = 0; i < 4; i++) {
			System.out.print("Vertex " + i + " -> ");
			for (DirectedEdge e : ewg.adj[i]) {
				System.out.print(e.weight + " ");
			}
			System.out.println();
		}
	}
}
