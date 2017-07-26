import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
	public Node head;
	private int size = 0;
	public class Node {
		Node next;
		Item item;
		public Node (Item item, Node next) {
			this.next = next;
			this.item = item;
		}
	}

	public Bag() {
		this.head = null;
	}

	public void add(Item item) {
		if (this.head == null) {
			head = new Node(item, null);
			this.size++;
			return;
		}
		Node pointer = head;
		while(pointer.next != null) {
			if (pointer.item == item) {
				return;
			}
			pointer = pointer.next;
		}
		if (pointer.item == item) {
			return;
		}
		pointer.next = new Node(item, null);
		this.size++;
		return;
	}

	public boolean isEmpty() {
		return (this.head == null);
	}

	public int size() {
		return this.size;
	}

	public boolean contains(Item item) {
		Node pointer = head;
		if (pointer == null) {
			return false;
		}
		while (pointer.next != null) {
			if (pointer.item == item) {
				return true;
			} else {
				pointer = pointer.next;
				continue;
			}
		}
		return false;
	}

	@Override
	public Iterator<Item> iterator() {
		return new BagIterator<Item>();
	}

	public class BagIterator<Item> implements Iterator<Item> {
		Node pointer = head;
		Item item;
		@Override
		public boolean hasNext() {
			return (pointer != null);
		}

		@Override
		@SuppressWarnings("unchecked")
		public Item next() {
			item = (Item) pointer.item;
			pointer = pointer.next;
			return item;
		}

		@Override
		public void remove() {
			return;
		}
	}

	public static void main(String[] args) {
		Bag<Integer> bag = new Bag<Integer>();
		bag.add(1);
		bag.add(3);
		bag.add(5);
		bag.add(7);
		bag.add(9);
		for (int i : bag) {
			System.out.println(i);
		}
	}
}
