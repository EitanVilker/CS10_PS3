
import java.io.*;
import java.util.*;

public class MyCompression {
	
	public static final String filename = "TestCase";
	
	public BufferedReader compressFile(BufferedReader input) {
		BufferedReader compressed = input;
		return compressed;
	}
	
	public BufferedReader decompressFile(BufferedReader input) {
		BufferedReader decompressed = input;
		return decompressed;
	}
	
	public static String charReps(Character c, Integer n) {
		String s = "";
		for (int i = 0; i < n; i ++) {
			s += c;
		}
		return s;
	}
	
	public static void main(String[] args) { 
		try {
			// Read in the file, generate frequency table
			BufferedReader inputFile = new BufferedReader(new FileReader("Inputs/" + filename + ".txt"));
			HashMap<Character, Integer> charFreq = new HashMap<Character, Integer>();
			int q = 0;
			int c  = inputFile.read();
			while (c!= -1) {
				char nextChar = (char)c;
				if (charFreq.get(nextChar) == null) {
					charFreq.put(nextChar, 1);
				}
				else {
					int thisFreq = charFreq.get(nextChar);
					charFreq.put(nextChar, thisFreq + 1);
				}
				
				c = inputFile.read();

				q ++;
				if (q % 10000 == 0) {
					System.out.println(q);
				}
			}
			
			inputFile.close();			
			
			// Generate a priority queue from the given HashMap, using frequencies as keys
			PriorityQueue<BST<Integer, Character>> theQueue = new PriorityQueue<BST<Integer, Character>>(new TreeComparator());
			for (Character key: charFreq.keySet()) {
				BST<Integer, Character> tree = new BST<Integer, Character>(charFreq.get(key), key, null, null);
				theQueue.add(tree);
			}
			// Create the tree by iterating through the priority queue
			while (theQueue.size() > 1) {
				BST<Integer, Character> left = theQueue.remove();
				BST<Integer, Character> right = theQueue.remove();
				int intTotal = left.getKey() + right.getKey();
				BST<Integer, Character> dummy = new BST<Integer, Character>(intTotal, 'z', left, right);
				theQueue.add(dummy);
			}
			// Generate 1/0 numbers from the tree
			BST<Integer, Character> theTree = theQueue.remove();
			HashMap<Character, String> binaryMap = new HashMap<Character, String>();
			theTree.makeBinaryStrings(binaryMap, "");
						
			// Generate compressed file as a series of bits, cross-referencing input file with dictionary, save to FilenameCompressed.txt
			// Generate save bits into FilenameCompressedText.txt for reference and testing.
			BufferedBitWriter compressedOutput = new BufferedBitWriter("Outputs/" + filename + "Compressed.txt");
			BufferedReader secondInputFile = new BufferedReader(new FileReader("Inputs/" + filename + ".txt"));
			int d = secondInputFile.read();
			while (d != -1) {
				String binaryCharString = binaryMap.get((char)d);
				for (int k = 0; k < binaryCharString.length(); k++) {
					Character currentBitChar = binaryCharString.charAt(k);
					if (currentBitChar == '1') {
						compressedOutput.writeBit(true);
					}
					else {
						compressedOutput.writeBit(false);
					}
					d = secondInputFile.read();
				}				
			}
			compressedOutput.close();
			secondInputFile.close();

			// Generate decompressed file, cross-referencing input file with dictionary
			BufferedBitReader compressedInput = new BufferedBitReader("Outputs/" + filename + "Compressed.txt");
			BufferedWriter decompressedOutput = new BufferedWriter(new FileWriter("Outputs/" + filename + "Decompressed.txt"));
			BST<Integer, Character> currentTree = theTree;
			while (compressedInput.hasNext()) {
				if (currentTree.isLeaf()) {
	  			decompressedOutput.write(currentTree.getValue());
	  			currentTree = theTree;
  				}
  				else {
				  	boolean bit = compressedInput.readBit();
				  	if (!bit) {
					  	currentTree = currentTree.getRight();
				  	}
				  	else {
				  		currentTree = currentTree.getLeft();
	  				}
	  			}	  
			}
  			decompressedOutput.write(currentTree.getValue());
			compressedInput.close();
			decompressedOutput.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println("WALRUS FOUND");
		}
	}
}