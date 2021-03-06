import java.security.InvalidKeyException;
import java.util.*;

/**
 * Generic binary search tree
 * Modified for use with compression algorithm
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, Fall 2016, min
 * @author Eitan Vilker, Aidan Low
 */

public class BST<K extends Comparable<K>,V> {
	private K key;
	private V value;
	private BST<K,V> left, right;

	/**
	 * Constructs leaf node -- left and right are null
	 */
	public BST(K key, V value) {
		this.key = key; this.value = value;
	}

	/**
	 * Constructs inner node
	 */
	public BST(K key, V value, BST<K,V> left, BST<K,V> right) {
		this.key = key; this.value = value;
		this.left = left; this.right = right;
	}

	/**
	 * Is it a leaf node?
	 */
	public boolean isLeaf() {
		return left == null && right == null;
	}

	/**
	 * Does it have a left child?
	 */
	public boolean hasLeft() {
		return left != null;
	}

	/**
	 * Does it have a right child?
	 */
	public boolean hasRight() {
		return right != null;
	}

	/**
	 * Returns the value associated with the search key, or throws an exception if not in BST
	 */
	public V find(K search) throws InvalidKeyException {
		System.out.println(key); // to illustrate
		int compare = search.compareTo(key);
		if (compare == 0) return value;
		if (compare < 0 && hasLeft()) return left.find(search);
		if (compare > 0 && hasRight()) return right.find(search);
		throw new InvalidKeyException(search.toString());
	}

	/**
	 * Smallest key in the tree, recursive version
	 */
	public K min() {
		if (left != null) return left.min();
		return key;
	}

	/**
	 * Smallest key in the tree, iterative version
	 */
	public K minIter() {
		BST<K,V> curr = this;
		while (curr.left != null) curr = curr.left;
		return curr.key;
	}

	/**
	 * Inserts the key & value into the tree (replacing old key if equal)
	 */
	public void insert(K key, V value) {
		int compare = key.compareTo(this.key);
		if (compare == 0) {
			// replace
			this.value = value;
		}
		else if (compare < 0) {
			// insert on left (new leaf if no left)
			if (hasLeft()) left.insert(key, value);
			else left = new BST<K,V>(key, value);
		}
		else if (compare > 0) {
			// insert on right (new leaf if no right)
			if (hasRight()) right.insert(key, value);
			else right = new BST<K,V>(key, value);
		}
	}

	/**
	 * Deletes the key and returns the modified tree, which might not be the same object as the original tree
	 * Thus must afterwards just use the returned one
	 */
	public BST<K,V> delete(K search) throws InvalidKeyException {
		int compare = search.compareTo(key);
		if (compare == 0) {
			// Easy cases: 0 or 1 child -- return other
			if (!hasLeft()) return right;
			if (!hasRight()) return left;
			// If both children are there, delete and substitute the successor (smallest on right)
			// Find it
			BST<K,V> successor = right;
			while (successor.hasLeft()) successor = successor.left;
			// Delete it
			right = right.delete(successor.key);
			// And take its key & value
			this.key = successor.key;
			this.value = successor.value;
			return this;
		}
		else if (compare < 0 && hasLeft()) {
			left = left.delete(search);
			return this; 
		}
		else if (compare > 0 && hasRight()) {
			right = right.delete(search);
			return this;
		}
		throw new InvalidKeyException(search.toString());
	}

	/**
	 * Parenthesized representation:
	 * <tree> = "(" <tree> ["," <tree>] ")" <key> ":" <value>
	 *        | <key> ":" <value>
	 */
	public String toString() {
		if (isLeaf()) return key+":"+value;
		String s = "(";
		if (hasLeft()) s += left;
		else s += "_";
		s += ",";
		if (hasRight()) s += right;
		else s += "_";
		return s + ")" + key+":"+value;
	}

	/**
	 * Very simplistic BST parser in a parenthesized representation
	 * <tree> = "(" <tree> ["," <tree>] ")" <key> ":" <value>
	 *        | <key> ":" <value>
	 * Assumes that the tree actually has the BST property!!!
	 * No effort at all to handle malformed trees
	 */
	public static BST<String,String> parseBST(String s) {
		return parseBST(new StringTokenizer(s, "(,)", true));
	}

	/**
	 * Does the real work of parsing, now given a tokenizer for the string
	 */
	public static BST<String,String> parseBST(StringTokenizer st) {
		String token = st.nextToken();
		if (token.equals("(")) {
			// Inner node
			BST<String,String> left = parseBST(st);
			BST<String,String> right = null;
			String comma = st.nextToken();
			if (comma.equals(",")) {
				right = parseBST(st);	
				String close = st.nextToken();
			}
			String label = st.nextToken();
			String[] pieces = label.split(":");
			return new BST<String,String>(pieces[0], pieces[1], left, right);
		}
		else {
			// Leaf
			String[] pieces = token.split(":");
			return new BST<String,String>(pieces[0], pieces[1]);
		}
	}
	
	/**
	 * Getters & Setters for Left, Right, Key, Value
	 */
	public BST<K,V> getLeft() {return left;}
	public BST<K,V> getRight() {return right;}
	public K getKey() {return key;}
	public V getValue() {return value;}
	public void setLeft(BST<K, V> left) {this.left = left;}
	public void setRight(BST<K, V> right) {this.right = right;}
	public void setKey(K key) {this.key = key;}
	public void setValue(V value) {this.value = value;}
	
	/**
	 * Embellish map with key binary pairs
	 */
	public void makeBinaryStrings(HashMap<Character, String> map, String s) {
		if (hasLeft()) {
			left.makeBinaryStrings(map, s + "1");
		}
		if (hasRight()) {
			right.makeBinaryStrings(map, s + "0");
		}
		if (isLeaf()) {
			map.put((char)value, s);
		}
	}
	
	/**
	 * Return size of tree
	 */
	public int size() {
		int mySize = 1;
		if (hasLeft()) {
			mySize += left.size();
		}
		if (hasRight()) {
			mySize += right.size();
		}
		System.out.println(hasLeft());
		return mySize;
	}
	
	public String getValues() {
		String myValue = value.toString();
		if (hasLeft()) {
			myValue += ", " + left.getValues();
		}
		if (hasRight()) {
			myValue += ", " + right.getValues();
		}
		System.out.println(hasLeft());
		return myValue;
	}
}
