import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MyCompression {
	
	//public static final String filepath = "inputs/WarAndPeace.txt";
	
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
	
	public static void main(String[] args) { 		// Read in the file
		try {
		BufferedReader input = new BufferedReader(new FileReader("Inputs/USConstitution.txt"));
		// Generate frequency table
//		// Generate frequency leaves from frequency map		
//		// Add frequency leaves to priority queue
//		// Generate a tree using frequency leaves	
		TreeMap<Character, Integer> charFreq = getFrequencies(input);
//		// Generate a TreeMap dictionary with characters as keys and bit strings as outputs
		// Generate compressed file, cross-referencing input file with dictionary
		
		// Save compressed file to FilenameCompressed.txt
		
		// Generate decompressed file, cross-referencing input file with dictionary
		
		// Save decompressed file to FilenameDecompressed.txt'
		TreeMap<Character, String> binaryMap = TreeComparator.generateBinaryMapFromFrequencies(charFreq);
		BufferedBitReader bitInput = new BufferedBitReader("Inputs/USConstitution.txt");
		BufferedBitWriter bitOutput = new BufferedBitWriter("Outputs/USConstitutionCompressed.txt");
		while (bitInput.hasNext()) {
			  boolean bit = bitInput.readBit();
			  // Find bit sequence, identify corresponding code for character, and write to file
			  bitOutput.writeBit(bit);
			}
		input.close();
		
		}
		
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}