
public class Edge implements Comparable<Edge> {
	int v;
	int w;
	public double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either() {
		return this.v;
	}
	
	public int other(int vertex) {
		if (this.v == vertex) {
			return this.w;
		} else {
			return this.v;
		}
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append(Integer.toString(v) + " -> " + Integer.toString(w));
		return str.toString();
	}

	@Override
	public int compareTo(Edge that) {
		if (this.weight < that.weight) {
			return -1;
		} else if (this.weight > that.weight) {
			return 1;
		}
		return 0;
	}

}
