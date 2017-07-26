import java.util.Comparator;
import java.util.Iterator;

public class MinPQueue implements Comparator<Edge>, Iterable<Edge> {
	Node head;
	int size = 0;
	private class Node {
		Node next;
		Edge e;
		public Node(Edge e, Node next) {
			this.next = next;
			this.e = e;
		}
	}
	
	public MinPQueue() {
		head = null;
	}
	
	public void add(Edge e) {
		Node pointer = head;
		Node predecessor = null;
		if (head == null) {
			head = new Node(e, null);
			size++;
			return;
		}
		if (head.next != null) {
			pointer = pointer.next;
			predecessor = head;
		}
		while(pointer.next != null) {
			System.out.println("Its in");
			int compare = this.compare(e, pointer.e);
			if (compare == 1) {
				pointer = pointer.next;
				predecessor = predecessor.next;
			} else {
				Node newNode = new Node(e, pointer);
				predecessor.next = newNode;
				//newNode.next = pointer;
				size++;
				return;
			}
		}
	}
	
	public boolean isEmpty() {
		return this.head == null;
	}

	@Override
	public int compare(Edge e1, Edge e2) {
		if (e1.weight > e2.weight) {
			return 1;
		} else if (e1.weight < e2.weight) {
			return -1;
		}
		return 0;
	}
	
	@Override
	public Iterator<Edge> iterator() {
		return new pqIterator();
	}
	
	public class pqIterator implements Iterator<Edge> {
		Node pointer = head;
		Edge e;
		@Override
		public boolean hasNext() {
			return (pointer != null);
		}

		@Override
		public Edge next() {
			e = (Edge) pointer.e;
			pointer = pointer.next;
			return e;
		}
	}
	
	public static void main(String[] args) {
		MinPQueue pq = new MinPQueue();
		Edge e1 = new Edge(0, 1, 0.11);
		Edge e2 = new Edge(1, 2, 0.19);
		Edge e3 = new Edge(0, 3, 0.11);
		Edge e4 = new Edge(2, 3, 0.15);
		int cmp = pq.compare(e1, e2);
		System.out.println(cmp);
		pq.add(e1);
		pq.add(e2);
		pq.add(e3);
		pq.add(e4);
		System.out.println(pq.size);
		for (Edge e : pq) {
			System.out.println(e.weight);
		}
	}	
}