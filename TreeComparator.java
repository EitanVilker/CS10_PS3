import java.util.*;
import java.lang.*;
import java.io.*;

public class TreeComparator implements Comparator<BST<Integer, Character>> {

	public int compare(BST<Integer, Character> tree1, BST<Integer, Character> tree2) {
        // return Integer.compare(tree1.getKey(), tree2.getKey());
		int key1 = tree1.getKey();
		int key2 = tree2.getKey();

		if (key1 < key2) {
			return -1;
		}
		else if (key1 > key2) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
