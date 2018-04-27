import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MyCompression {
	
	public static TreeMap<Character, Integer> getFrequencies(BufferedReader input) {		
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
	
	public BufferedReader compressFile(BufferedReader input) {
		BufferedReader compressed = input;
		return compressed;
	}
	
	public BufferedReader decompressFile(BufferedReader input) {
		BufferedReader decompressed = input;
		return decompressed;
	}
	
	public static void main(String[] args) {
		// Read in the file
		BufferedReader input = new BufferedReader(new FileReader(path + "TestCase.txt"));
		// Generate frequency table
		TreeMap<Character, Integer> charFreq = getFrequencies(input);
		// Generate frequency leaves from frequency map
		
		// Add frequency leaves to priority queue
		
		// Generate a tree using frequency leaves
				
		// Generate a TreeMap dictionary with characters as keys and 1/0 strings as outputs
		
		// Generate compressed file, cross-referencing input file with dictionary
		
		// Save compressed file to FilenameCompressed.txt
		
		// Generate decompressed file, cross-referencing input file with dictionary
		
		// Save decompressed file to FilenameDecompressed.txt'
		
	}
}
