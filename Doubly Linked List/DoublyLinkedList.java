/**
 *
 * @author Shashwat Koranne
 *
 */
public class DoublyLinkedList {
	/**
	 * Node to keep the track of head of the Linked List
	 */
	DoubleNode head;
	/**
	 * Node to keep the track of tail of the Linked List
	 */
	DoubleNode tail;
	
	/**
	 * Constructor that initializes the linked list with head and tail
	 * being null
	 */
	DoublyLinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * Method to check if the Linked List is empty.
	 * @return true if Linked List is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * Method to insert a character at the end.
	 * @param c is the character to be added
	 */
	public void addCharAtEnd(char c) {
		if (this.head == null) {
			this.tail = new DoubleNode(null, c, null);
			this.head = tail;
		} else {
			DoubleNode newTail = new DoubleNode(tail, c, null);
			this.tail.next = newTail;
			this.tail = newTail;
		}
	}
	
	/**
	 * Method to insert a character at the front.
	 * @param c is the character to be added
	 */
	public void addCharAtFront(char c) {
		if (this.head == null) {
			this.head = new DoubleNode(null, c, null);
			this.tail = head;
		} else {
			DoubleNode newHead = new DoubleNode(null, c, head);
			this.head.prev = newHead;
			this.head = newHead;
		}
	}
	
	/**
	 * Method to remove a characted from the front.
	 * @return the character that's removed form the front
	 */
	public char removeCharFromFront() {
		if (head == null) {
			throw new NullPointerException("List is Empty!");
		}
		char toRemove = head.character;
		head = head.next;
		return toRemove;
	}
	
	/**
	 * Method to remove character in the end.
	 * @return the characted that's removed
	 */
	public char removeCharAtEnd() {
		if (tail == null) {
			return '-';
		}
		char toRemove = tail.character;
		tail = tail.prev;
		return toRemove;
	}
	
	/**
	 * Method to count the number of nodes present in the Linked List
	 * @return the number of nodes in the Linked List
	 */
	public int countNodes() {
		if (this.isEmpty()) {
			return 0;
		} else {
			int count = 1;
			DoubleNode pointer = head;
			while(pointer.next != null) {
				pointer = pointer.next;
				count++;
			}
			return count;
		}
	}
	
	/**
	 * Method to delete a specific character.
	 * @param c character to be deleted
	 * @return true if the character is found and is deleted
	 */
	public boolean deleteChar(char c) {
		DoubleNode pointer = head;
		while(pointer.character != c) {
			pointer = pointer.next;
			if (pointer == null) {
				return false;
			}
		}
		
		if (pointer.equals(head)) {
			head = head.next;
		}
		if (pointer.equals(tail)) {
			tail = tail.prev;
		}
		if (pointer.prev != null) {
			pointer.prev.next = pointer.next;
		}
		if (pointer.next != null) {
			pointer.next.prev = pointer.prev;
		}
		return true;
	}
	
	/**
	 * Method returns the contents of the Linked List.
	 */
	public String toString() {
		if (this.isEmpty()) {
			return null;
		}
		StringBuilder str = new StringBuilder("");
		DoubleNode pointer = head;
		while(pointer.next != null) {
			str.append(Character.toString(pointer.character));
			str.append(" ");
			pointer = pointer.next;
		}
		str.append(pointer.character);
		return str.toString();
	}
	
	/**
	 * Method reverses the given Linked List.
	 */
	public void reverse() {
		DoubleNode temp = head;
		head = tail;
		tail = temp;
		DoubleNode p = head;

		while(p!=null) {
			temp = p.next;
		    p.next = p.prev;
		    p.prev = temp;
		    p = p.next;
		}
	}
	
	public static void main(String a[]) {
		DoublyLinkedList list = new DoublyLinkedList();
		list.addCharAtEnd('H');
		list.addCharAtEnd('e');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('o');
		System.out.println(list.toString());
		System.out.println("Deleting l");
		list.deleteChar('l');
		System.out.println(list);
		System.out.println("Deleting H");
		list.deleteChar('H');
		System.out.println(list);
		System.out.println("Deleting o");
		list.deleteChar('o');
		System.out.println(list);
		System.out.println("Deleting e");
		list.deleteChar('e');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		//System.out.println(list);
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		System.out.println(list);
		
		System.out.println(list.countNodes());
		
		System.out.println("Popping everything");
		while(!list.isEmpty()){
			System.out.println(list.removeCharFromFront());
		}
		
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		
		System.out.println("Popping everything from the end");
		System.out.println(list.countNodes());
		
		list.addCharAtEnd('o');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('e');
		list.addCharAtEnd('H');
		System.out.println(list);
		list.reverse();
		System.out.println(list);
		
		list.reverse();
		System.out.println(list);
           
		DoublyLinkedList list2 = new DoublyLinkedList();
        list2.addCharAtEnd('O');
		list2.addCharAtEnd('K');
        list2.reverse();
		System.out.println(list2);
        System.out.println(list2.countNodes());
	}
}
