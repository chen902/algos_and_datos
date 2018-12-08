import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;

/**
 * Binary search tree implementation
 * 
 * 
 */
public class SearchTree {

	private TreeNode root;

	/** initializes an empty tree */
	public SearchTree() {
		root = null;
	}

	/** checks if tree is empty */
	public boolean isEmpty() {
		return (root == null);
	}

	/** computes height of the tree */
	public int height() {
		return height(root);
	}

	private int height(TreeNode r) {
		if (r == null)
			return 0;
		else
			return 1 + Math.max(height(r.left), height(r.right));
	}

	/**
	 * inserts a value into the tree
	 */
	public boolean insert(int v) {
		if (root == null) {
			// tree was empty
			root = new TreeNode(v);
			return true; // value successfully inserted
		} else {
			// insert v into the non-empty tree with the given root
			return insertIter(root, v);
		}
	}

	/**
	 * insert a value v into tree with root r, iterative version
	 */
	private boolean insertIter(TreeNode r, int v) {
		while (true) {
			if (v < r.info) {
				if (r.left == null) {
					r.left = new TreeNode(v);
					return true;
				} else {
					r = r.left;
				}
			} else if (v > r.info) {
				if (r.right == null) {
					r.right = new TreeNode(v);
					return true;
				} else {
					r = r.right;
				}
			} else
				// if (v == r.info)
				return false; // Wert war bereits vorhanden
		}
	}

	/**
	 * search for value v in the tree
	 * 
	 * @return node that contains value v, or null, if v is not contained in the
	 *         tree
	 */
	public TreeNode searchNode(int v) {
		return searchNodeRek(root, v);
	}

	public TreeNode searchNodeRek(TreeNode r, int v) {
		if (r == null)
			return null;
		else {
			if (v < r.info)
				return searchNodeRek(r.left, v); // continue search in left subtree
			else if (v > r.info)
				return searchNodeRek(r.right, v); // continue search in right subtree
			else
				// v == r.value // value found
				return r;
		}
	}

	/**
	 * print the tree in 'graphical' form to the console
	 */
	public void print() {
		if (root != null) {
			print(root.left, "    ", true);
			System.out.println("+---" + root.info);
			print(root.right, "    ", false);
		} else {
			System.out.println("(empty tree)");
		}
	}

	/** auxilliary method to print the tree in graphical form */
	private void print(TreeNode r, String prefix, boolean links) {
		if (r != null) {
			if (links)
				print(r.left, prefix + "    ", links);
			else
				print(r.left, prefix + "|   ", !links);

			System.out.println(prefix + "+---" + r.info);

			if (links)
				print(r.right, prefix + "|   ", !links);
			else
				print(r.right, prefix + "    ", links);

		}
	}

	/** Compute the sum of all values. */
	public int sum() {
		//empty tree
		if(root == null)
			return 0;
		else
			//compute sum recursively using helper function
			return sumRek(root);
	}

	public int sumRek(TreeNode r)
	{
		//no more nodes in subtree
		if(r == null)
			return 0;
		else
			//compute sum recursively
			return(r.info + sumRek(r.left) + sumRek(r.right));
	}

	/** computes the number of leaves */
	public int numOfLeaves() {
		//empty tree
		if(root == null)
			return 0;
		else
			//compute nr of leaves recursively using helper function
			return numOfLeavesRek(root);
	}

	public int numOfLeavesRek(TreeNode r)
	{
		//no more nodes in subtree
		if(r == null)
			return 0;
		//leaf reached
		else if(r.left == null && r.right == null)
			return 1;
		else
			//compute number of leaves recursively
			return(numOfLeavesRek(r.left) + numOfLeavesRek(r.right));
	}

	/**
	 * removes the node with the minimal value
	 * 
	 * @return value of the node that is removed
	 */
	public int extractMin() {
		//empty tree
		if(root == null)
			throw new RuntimeException("Can't extract min from empty tree!");
		else
			//search for minimum recursively using helper function
			return extractMinRek(root,null);
	}

	public int extractMinRek(TreeNode r, TreeNode parent)
	{
		//search successful, minimum node reached
		if(r.left == null)
		{
			int temp = r.info;

			//case root is minimum (no left children)
			if(r.info == root.info)
			{
				//case root has no children -> root gets deleted -> empty tree
				if(r.right == null)
					root = null;
				else
					//case root has right child -> set child as new root
					root = root.right;
			}
			//case minimum has no children -> simple deletion
			else if(r.right == null)
				parent.left = null;
			else
				//case minimum has right subtree -> subtree appended to parent
				parent.left = r.right;

			return temp;
		}
		else
			//continue search recursively, with current node as new parent
			return extractMinRek(r.left,r);
	}

	/**
	 * builds a sorted list of all values
	 */
	public ArrayList<Integer> toList()
	{
		ArrayList<Integer> arr = new ArrayList<Integer>();

		//case empty list
		if(root == null)
			return arr;
		else
		{
			//populate list recursively using inorder traversal helper function
			toListRek(root, arr);

			return arr;
		}
	}

	public void toListRek(TreeNode r, ArrayList<Integer> arr)
	{
		//previous node was leaf -> return up recursion stack to leaf node
		if(r == null)
			return;

		//traverse left side
		toListRek(r.left,arr);

		//add node value to array
		arr.add(r.info);

		//traverse right side
		toListRek(r.right,arr);
	}

	/** checks if both trees contain the same set of values */
	public boolean equals(SearchTree other) {
		//build sorted lists using toList()
		ArrayList<Integer> arrCurr = new ArrayList<Integer>(toList());
		ArrayList<Integer> arrOther = new ArrayList<Integer>(other.toList());

		//case list lengths differ -> can't be equal
		if(arrCurr.size() != arrOther.size())
			return false;
		else
		{
			//check for every value in first list if it's contained in second list using binary search (since both lists are sorted)
			for(int value : arrCurr)
				if(!binaryContains(value,arrOther)) return false;
		}

		//all values in arrCurr found in arrOther -> lists identical
		return true;
	}

	//binary search, iteratively implemented
	public boolean binaryContains(int toFind, ArrayList<Integer> arr)
	{
		int low = 0;
		int high = arr.size()-1;
		int mid;

		while(low <= high)
		{
			mid = low + (high - low) / 2;

			//sought value smaller than current mid point -> search in smaller half
			if(arr.get(mid) < toFind)
				low = mid + 1;

			//sought value larger than current mid point -> search larger half
			else if(arr.get(mid) > toFind) 
				high = mid - 1;

			//value = current midpoint -> successful search
			else
				return true;
		}

		//unsuccessful search
		return false;
	}
}
