import java.util.TreeMap;
import java.io.BufferedReader;

public class TreeComparator {

	public TreeComparator(TreeMap<Character, Integer> charFreq) {
		for (Character key: charFreq.keySet()) {
			ValuedCharacter c = new ValuedCharacter(key, charFreq.get(key));
			BST<Character, Integer> tree = new BST<Character, Integer>();
		}
		
	}
}
