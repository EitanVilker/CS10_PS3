import java.util.*;
import java.lang.*;
import java.io.*;

public class TreeComparator implements Comparator {
	
	public int compare(BST<Character, Integer> tree1, BST<Character, Integer> tree2) {
		if (tree1.find(Character key) < tree2.find(Character key)) {
			return -1;
		}
		
		else if (tree1.find(Character key) > tree2.find(Character key)) {
			return 1;
		}
		
		return 0;
	}
	
	public TreeComparator(TreeMap<Character, Integer> charFreq) {
		PriorityQueue<BST<Character, Integer>> theQueue = new PriorityQueue<BST<Character, Integer>>();
		for (Character key: charFreq.keySet()) {
			ValuedCharacter c = new ValuedCharacter(key, charFreq.get(key));
			BST<Integer, ValuedCharacter> tree = new BST<Integer, ValuedCharacter>(c.getFrequency(), c, null, null);
			theQueue.add(tree);
		}
		
	}
}
