
public class EdgeWeightedGraph {
	int v;
	Bag<Edge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V) {
		this.v = V;
		adj = new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Edge>();
		}
	}

	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}

	public int V() {
		return this.v;
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
		for (int i = 0; i < 4; i++) {
			System.out.print("Vertex " + i + " -> ");
			for (Edge e : ewg.adj[i]) {
				System.out.print(e.weight + " ");
			}
			System.out.println();
		}
	}
}
