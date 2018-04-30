import java.util.*;
import java.lang.*;
import java.io.*;

public class TreeComparator implements Comparator {
	
	@Override
	public int compare(Object tree1, Object tree2) {
		if (((BST<Integer, ValuedCharacter>) tree1).getKey() < ((BST<Integer, ValuedCharacter>) tree2).getKey()) {
			return -1;
		}
		
		else if (((BST<Integer, ValuedCharacter>) tree1).getKey() > ((BST<Integer, ValuedCharacter>) tree2).getKey()) {
			return 1;
		}
		
		return 0;
	}
	
	public static TreeMap<Character, String> generateBinaryMapFromFrequencies(TreeMap<Character, Integer> charFreq) {
		// Generate a prio queue from the given TreeMap, using frequencies as keys
		PriorityQueue<BST<Integer, ValuedCharacter>> theQueue = new PriorityQueue<BST<Integer, ValuedCharacter>>();
		for (Character key: charFreq.keySet()) {
			ValuedCharacter c = new ValuedCharacter(key, charFreq.get(key));
			BST<Integer, ValuedCharacter> tree = new BST<Integer, ValuedCharacter>(c.getFrequency(), c, null, null);
			theQueue.add(tree);
		}
		// Create the tree by iterating through the prio queue
		while (theQueue.size() > 1) {
			BST<Integer, ValuedCharacter> left = theQueue.remove();
			BST<Integer, ValuedCharacter> right = theQueue.remove();
			int intTotal = left.getKey() + right.getKey();
			ValuedCharacter dummyValChar = new ValuedCharacter('z', 0);
			BST<Integer, ValuedCharacter> dummy = new BST<Integer, ValuedCharacter>(intTotal, dummyValChar);
			dummy.setLeft(left);
			dummy.setRight(right);
			theQueue.add(dummy);
		}
		// Generate 1/0 numbers from the tree
		BST<Integer, ValuedCharacter> theTree = theQueue.remove();
		TreeMap<Character, String> binaryMap = new TreeMap<Character, String>();
		theTree.makeBinaryStrings(binaryMap, "");
		
		// Return the map
		return binaryMap;
	}
}
