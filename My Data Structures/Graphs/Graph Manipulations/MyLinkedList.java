
public class MyLinkedList<AnyType> {
	Node head;
	int size = 0;
	
	public MyLinkedList() {
		this.head = null;
	}
	
	public void insert(AnyType data) {
		if (head == null) {
			head = new Node(data, head);
			this.size++;
			return;
		}
		Node pointer = head;
		while(pointer.next != null) {
			pointer = pointer.next;
		}
		pointer.next = new Node(data, null);
		this.size++;
		return;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder("");
		Node pointer = head;
		while (pointer.next != null) {
			str.append(pointer.data + ", ");
			pointer = pointer.next;
		}
		str.append(pointer.data);
		return str.toString();
	}
	
	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		list.insert(3);
		list.insert(2);
		System.out.println(list.getSize());
	}
}
 