import java.util.TreeMap;
import java.io.BufferedReader;
import MyCompression.java.*;

public class TreeComparator {

	public TreeComparator(TreeMap<Character, Integer> charFreq) {
		for (Character key: charFreq.keySet()) {
			ValuedCharacter c = new ValuedCharacter(key, charFreq.get(key));
		}
		BST<String, Integer> tree = new BST<String, Integer>();
	}
}
