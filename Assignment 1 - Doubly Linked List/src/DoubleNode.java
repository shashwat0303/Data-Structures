/**
 * 
 * @author Shashwat Koranne
 *
 */
public class DoubleNode {
	
	DoubleNode prev;
	char character;
	DoubleNode next;
	
	public DoubleNode() {
		this.prev = null;
		this.next = null;
	}
	
	public DoubleNode(DoubleNode p, char c, DoubleNode n) {
		this.prev = p;
		this.character = c;
		this.next = n;
	}
	
	public DoubleNode getPrev() {
		return this.prev;
	}
	
	public void setPrev(DoubleNode prev) {
		this.prev = prev;
	}
	
	public DoubleNode getNext() {
		return next;
	}
	
	public void setNext(DoubleNode next) {
		this.next = next;
	}
	
	public char getC() {
		return this.character;
	}
	
	public void setC(char c) {
		this.character = c;
	}
	
	public String toString() {
		String str = this.character + " ";
		return str;
	}
	
}
