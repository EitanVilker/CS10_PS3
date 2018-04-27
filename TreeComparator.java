import java.util.*;
import java.lang.*;
import java.io.*;

public class TreeComparator implements Comparator {

	public TreeComparator(TreeMap<Character, Integer> charFreq) {
		PriorityQueue<BST<Character, Integer>> theQueue = new PriorityQueue<BST<Character, Integer>>();
		for (Character key: charFreq.keySet()) {
			ValuedCharacter c = new ValuedCharacter(key, charFreq.get(key));
			BST<Character, Integer> tree = new BST<Character, Integer>();
			tree.insert(key, charFreq.get(key));
			theQueue.add(tree);
		}
		
	}
}
