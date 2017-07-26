public class Node<AnyType> {
	Node next;
	AnyType data;
	public Node(AnyType data, Node next) {
		this.next = next;
		this.data = data;
	}
}