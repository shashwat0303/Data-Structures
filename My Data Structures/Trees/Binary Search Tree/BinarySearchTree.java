/**
 * 
 * @author Shashwat Koranne
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	
	private Node root;
	int count = 0;
	
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
	
		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
		
		public String toString() {
			String str = "[" + key + ", " + val + "]";
			return str;
		}
	}
	
	public BinarySearchTree() {
		this.root = null;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	private Node put(Node n, Key key, Value val) {
		Node newNode = new Node(key, val);
		if (n == null) {
			n = newNode;
			return n;
		}
		int compare = key.compareTo(n.key);
		if (compare == 0) {
			n = newNode;
			return n;
		} else if (compare < 0) {
			n.left = put(n.left, key, val);
		} else if (compare > 0) {
			n.right = put(n.right, key, val);
		}
		return n;
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
					return;
				} else {
					curr = curr.right;
				}
			} else if (compare < 0) {
				if (curr.left == null) {
					curr.left = newNode;
					return;
				} else {
					curr = curr.left;
				}
			}
		}
	}
	
	/*public boolean search(Key key) {
		root = search(root, key);
		if (root != null) {
			return true;
		} else {
			return false;
		}
	}
	
	private Node search(Node n, Key key) {
		if (n == null) {
			return null;
		}
		int compare = key.compareTo(n.key);
		if (compare == 0) {
			return n;
		} else if (compare < 0) {
			search(n.left, key);
		} else if (compare > 0) {
			search(n.right, key);
		}
		return null;
	}*/
	
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
		BinarySearchTree<Integer, Double> bst = new BinarySearchTree<Integer, Double>();
		bst.put(5, 0.5);
		bst.put(3, 0.3);
		bst.put(4, 0.4);
		bst.put(2, 0.2);
		bst.put(8, 0.8);
		bst.put(6, 0.6);
		bst.traverse();
		System.out.println(bst.find(3));
		bst.delete(5);
		System.out.println(bst.root);
		bst.traverse();
		//System.out.println(bst.get(4));
	}
}
