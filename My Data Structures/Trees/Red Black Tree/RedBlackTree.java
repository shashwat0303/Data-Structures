
/**
 * 
 * @author Shashwat Koranne
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	int count = 0;
	
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		boolean color;
	
		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
		
		public String toString() {
			String str = "[" + key + ", " + val + "]";
			return str;
		}
	}
	
	public RedBlackTree() {
		this.root = null;
	}
	
	private boolean isRed(Node n) {
		if (n == null) {
			return false;
		}
		return n.color == RED;
	}
	
	private Node rotateLeft(Node falseParent) {
		assert(isRed(falseParent.right));
		Node trueParent = falseParent.right;
		falseParent.right = trueParent.left;
		trueParent.left = falseParent;
		trueParent.color = falseParent.color;
		falseParent.color = RED;
		return trueParent;
	}
	
	private Node rotateRight(Node falseParent) {
		assert(isRed(falseParent.left));
		Node trueParent = falseParent.left;
		falseParent.left = trueParent.right;
		trueParent.right = falseParent;
		trueParent.color = falseParent.color;
		falseParent.color = RED;
		return trueParent;
	}
	
	private void flipColors(Node n) {
		n.color = RED;
		n.left.color = BLACK;
		n.right.color = BLACK;
	}
	
	public void insert(Key key, Value val) {
		Node newNode = new Node(key, val);
		if (root == null) {
			root = newNode;
		}
		
		Node curr = root;
		
		while(true) {
			int compare = key.compareTo(curr.key);
			if (compare == 0) {
				return;
			} else if (compare > 0) {
				if (curr.right == null) {
					curr.right = newNode;
					newNode.color = RED;
					return;
				} else {
					curr = curr.right;
				}
			} else if (compare < 0) {
				if (curr.left == null) {
					curr.left = newNode;
					newNode.color = RED;
					return;
				} else {
					curr = curr.left;
				}
			}
		}
	}
	
	public boolean find(Key key) {
		if (root == null) {
			return false;
		}
		
		Node curr = root;
		
		while (true) {
			int compare = key.compareTo(curr.key);
			if (compare == 0) {
				return true;
			} else if (compare < 0) {
				if (curr.left == null) {
					return false;
				} else {
					curr = curr.left;
				}
			} else if (compare > 0) {
				if (curr.right == null) {
					return false;
				} else {
					curr = curr.right;
				}
			}
		}
	}
	
	public void delete(Key key) {
		if (root == null) {
			return;
		}
		
		Node parent = root;
		Node curr = root;
		
		boolean isLeftChild = true;
		
		while(true) {
			int compare = key.compareTo(curr.key);
			if (compare == 0) {
				if (curr.left == null && curr.right == null) {
					if (isLeftChild) {
						parent.left = null;
					} else {
						parent.right = null;
					}
					return;
				} else if (curr.left != null && curr.right == null) {
					if (isLeftChild) {
						parent.left = curr.left;
					} else {
						parent.right = curr.left;
					}
					return;
				} else if (curr.left == null && curr.right != null) {
					if (isLeftChild) {
						parent.left = curr.right;
					} else {
						parent.right = curr.right;
					}
					return;
				} else if (curr.left != null && curr.right != null) {
					Node newCurr = getSuccessor(curr);
					if (curr == root) {
						root = newCurr;
					} else if (isLeftChild) {
						parent.left = newCurr;
					} else {
						parent.right = newCurr;
					}
					newCurr.left = curr.left;
					return;
				}
			} else if (compare < 0) {
				if (curr.left == null) {
					return;
				} else {
					parent = curr;
					curr = curr.left;
					isLeftChild = true;
				}
			} else if (compare > 0) {
				if (curr.right == null) {
					return;
				} else {
					parent = curr;
					curr = curr.right;
					isLeftChild = false;
				}
			}
		}
	}
	
	public Node getSuccessor(Node curr) {
		Node start = curr.right;
		while(start.left != null) {
			start = start.left;
		}
		Node returnNode = start;
		start = null;
		return returnNode;
	}
	
	public Value get(Key key) {
		Node curr = root;
		while (true) {
			int compare = key.compareTo(curr.key);
			if (compare == 0) {
				return curr.val;
			} else if (compare < 0) {
				curr = curr.left;
			} else if (compare > 0) {
				curr = curr.right;
			}
		}
	}
	
	public void traverse() {
        traverse(root);
        System.out.println();
    }

    private void traverse(Node pointer) {
        if(pointer != null) {
            traverse(pointer.left);
            System.out.print(pointer);
            traverse(pointer.right);
        }
    }
	
	public static void main(String[] args) {
		RedBlackTree<Integer, Double> bst = new RedBlackTree<Integer, Double>();
		bst.insert(5, 0.5);
		bst.insert(3, 0.3);
		bst.insert(4, 0.4);
		bst.insert(2, 0.2);
		bst.insert(8, 0.8);
		bst.insert(6, 0.6);
		bst.traverse();
		System.out.println(bst.getSuccessor(bst.root.left));
		bst.delete(5);
		bst.traverse();
		System.out.println(bst.get(4));
	}
}
