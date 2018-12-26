package com.tree.rbtree;

public class Node {
	
	public static boolean BLACK = true;
	public static boolean RED = false;
	
	public Node(int v2) {
		this.v = v2;
	}

	public int v;
	
	public boolean color = BLACK;
	
	public Node parent;
	
	public Node right;
	
	public Node left;
	
	public int compare(int cv) {
		return this.v > cv ? 1 : (this.v == cv ? 0 : -1);
	}
	
	public String getV() {
		return "["+v+","+(color ? "B":"R")+"]";
	}
}
