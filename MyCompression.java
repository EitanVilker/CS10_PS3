import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class MyCompression {
	
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
	
	public BufferedReader compressFile(BufferedReader input) {
		BufferedReader compressed = input;
		return compressed;
	}
	
	public BufferedReader decompressFile(BufferedReader input) {
		BufferedReader decompressed = input;
		return decompressed;
	}
	
	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new FileReader("SA3_PicProcessing/baker-400-300.jpg"));
	}
}
