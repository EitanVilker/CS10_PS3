/**
 * GET HELP REGARDING COMPARABLES. CAN'T COMPARE ONE BST WITH ANOTHER.
 */

import java.io.*;
import java.util.*;

public class MyCompression {
	
	public static final String filename = "TestCase";
	
	//public static final String filepath = "inputs/WarAndPeace.txt";
	
	public static TreeMap<Character, Integer> getFrequencies(String input) {		
		TreeMap<Character, Integer> charFreq = new TreeMap<Character, Integer>();
		int length = input.length();
		for (int i = 0; i < length; i ++) {
			Character nextChar = input.charAt(i);
			if (charFreq.get(nextChar) == null) {
				charFreq.put(nextChar, 1);
			}
			else {
				int thisFreq = charFreq.get(nextChar);
				charFreq.put(nextChar, thisFreq + 1);
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
	
	public static String charReps(Character c, Integer n) {
		String s = "";
		for (int i = 0; i < n; i ++) {
			s += c;
		}
		return s;
	}
	
	public static void main(String[] args) { 		// Read in the file
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader("Inputs/" + filename + ".txt"));
			String fileString = "";
			boolean finished = false;
			while (!finished) {
				int next = inputFile.read();
				if (next == -1) {
					finished = true;
				}
				else {
					fileString += (char)(next);
				}
			}
			inputFile.close();

			// Generate frequency table
			TreeMap<Character, Integer> charFreq = getFrequencies(fileString);
			
			// Generate a priority queue from the given TreeMap, using frequencies as keys
			PriorityQueue<BST<Integer, Character>> theQueue = new PriorityQueue<BST<Integer, Character>>(new TreeComparator());
			for (Character key: charFreq.keySet()) {
				BST<Integer, Character> tree = new BST<Integer, Character>(charFreq.get(key), key, null, null);
				theQueue.add(tree);
			}
			// Create the tree by iterating through the prio queue
			while (theQueue.size() > 1) {
				BST<Integer, Character> left = theQueue.remove();
				BST<Integer, Character> right = theQueue.remove();
				int intTotal = left.getKey() + right.getKey();
				BST<Integer, Character> dummy = new BST<Integer, Character>(intTotal, 'z');
				dummy.setLeft(left);
				dummy.setRight(right);
				theQueue.add(dummy);
			}
			// Generate 1/0 numbers from the tree
			BST<Integer, Character> theTree = theQueue.remove();
			TreeMap<Character, String> binaryMap = new TreeMap<Character, String>();
			theTree.makeBinaryStrings(binaryMap, "");
			
			/**
			 * EVERYTHING UP TO HERE WORKS.
			 * THE BINARY MAP IS FUNCTIONAL.
			 * NOW WE NEED TO COMPRESS / DECOMPRESS.
			 */
			
			// Generate compressed file, cross-referencing input file with dictionary, save to FilenameCompressed.txt
			BufferedBitWriter bitOutput = new BufferedBitWriter("Outputs/" + filename + "Compressed.txt");
			
			// Generate decompressed file, cross-referencing input file with dictionary
			BufferedBitReader bitInput = new BufferedBitReader("Inputs/" + filename + ".txt");
			while (bitInput.hasNext()) {
				  boolean bit = bitInput.readBit();
				  // Find bit sequence, identify corresponding code for character, and write to file
				  bitOutput.writeBit(bit);
			}
			
			// Save decompressed file to FilenameDecompressed.txt'

		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
		}
	}
}