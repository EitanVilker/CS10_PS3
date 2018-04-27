import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Count the frequency of all items in a document, return a list of character frequencies
 * @author alowverus
 *
 */
public class FrequencyMapper {
	public TreeMap<Character, Integer> getFrequencies(BufferedReader input) {		
		TreeMap<Character, Integer> charFreq = new TreeMap<Character, Integer>();
		boolean iAmDone = false;
		while (!iAmDone) {
			try {
				Character nextChar = (char)input.read();
				if (charFreq.get(nextChar) == null) {
					charFreq.put(nextChar, 1);
				}
				else {
					int thisFreq = charFreq.get(nextChar);
					charFreq.put(nextChar, thisFreq + 1);
				}
			}
			catch (IOException e) {
				iAmDone = true;
			}
		}
		return charFreq;
	}
}
