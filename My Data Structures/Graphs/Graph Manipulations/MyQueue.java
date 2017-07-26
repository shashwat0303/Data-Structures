import java.util.Iterator;

public class MyQueue<Item> implements Iterable<Item> {
	Node head;
	
	class Node {
		Node next;
		Item item;
		public Node(Item item, Node next) {
			this.next = next;
			this.item = item;
		}
	}
	
	public MyQueue() {
		this.head = null;
	}
	
	public void enqueue(Item item) {
		if (this.head == null) {
			this.head = new Node(item, null);
			return;
		}
		Node pointer = head;
		while(pointer.next != null) {
			pointer = pointer.next;
		}
		pointer.next = new Node(item, null);
	}
	
	public Item dequeue() {
		Item item = this.head.item;
		head = head.next;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return (Iterator<Item>) new QueueIterator<Item>();
	}
	
	@SuppressWarnings("hiding")
	private class QueueIterator<Item> implements Iterator<Item> {
		Node pointer = head;
		Item item;
		@Override
		public boolean hasNext() {
			return (pointer != null);
		}

		@SuppressWarnings("unchecked")
		@Override
		public Item next() {
			item = (Item) pointer.item;
			pointer = pointer.next;
			return item;
		}
	}
	
	public boolean isEmpty() {
		return (this.head == null);
	}

	public static void main(String[] args) {
		MyQueue<Integer> q = new MyQueue<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	}
}
