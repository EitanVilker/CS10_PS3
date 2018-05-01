import java.util.*;
import java.lang.*;
import java.io.*;

public class TreeComparator implements Comparator<BST<Integer, Character>> {

	public int compare(BST<Integer, Character> tree1, BST<Integer, Character> tree2) {
        return Integer.compare(tree1.getKey(), tree2.getKey());
	}
}
