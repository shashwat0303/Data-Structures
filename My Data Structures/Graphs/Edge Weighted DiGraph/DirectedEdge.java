
public class DirectedEdge implements Comparable<DirectedEdge> {
	int v;
	int w;
	public double weight;

	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int from() {
		return this.v;
	}

	public int to(int vertex) {
		return this.w;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append(Integer.toString(v) + " -> " + Integer.toString(w));
		return str.toString();
	}

	@Override
	public int compareTo(DirectedEdge that) {
		if (this.weight < that.weight) {
			return -1;
		} else if (this.weight > that.weight) {
			return 1;
		}
		return 0;
	}

}
